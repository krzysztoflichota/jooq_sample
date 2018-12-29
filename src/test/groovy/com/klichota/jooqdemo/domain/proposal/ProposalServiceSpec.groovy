package com.klichota.jooqdemo.domain.proposal

import com.klichota.jooqdemo.boundary.perstitence.Sequences
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.ProposalDao
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import com.klichota.jooqdemo.domain.datetime.DateTimeService
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.tools.jdbc.MockConnection
import org.jooq.tools.jdbc.MockResult
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class ProposalServiceSpec extends Specification {

    ProposalDao dao
    DateTimeService dateTimeService
    DSLContext context
    ProposalService service

    def setup() {
        dao = Mock()
        dateTimeService = Mock()
        context = Mock()
        service = new ProposalService(dao, dateTimeService, context)
    }

    def "should save proposal"() {
        given:
        def proposal = new Proposal([amount: Consts.AMOUNT, term: Consts.TERM])
        def creationDateTime = Timestamp.valueOf(LocalDateTime.parse("2018-12-29T20:15:30"))
        dateTimeService.getCurrentTimestamp() >> creationDateTime
        context.nextval(Sequences.PROPOSAL_SEQ) >> Consts.ID

        when:
        def savedProposal = service.save(proposal, ProposalStatus.ACCEPTED)

        then:
        savedProposal.with {
            id == Consts.ID
            amount == Consts.AMOUNT
            term == Consts.TERM
            createdAt == creationDateTime
            status == ProposalStatus.ACCEPTED.name()
        }
    }

    interface Consts {
        def AMOUNT = 2000.0
        def TERM = 40L
        def ID = 5L
    }
}
