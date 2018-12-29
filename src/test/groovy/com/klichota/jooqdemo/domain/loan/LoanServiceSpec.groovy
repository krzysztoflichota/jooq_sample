package com.klichota.jooqdemo.domain.loan

import com.klichota.jooqdemo.boundary.perstitence.Sequences
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.LoanDao
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Loan
import com.klichota.jooqdemo.domain.datetime.DateTimeService
import org.jooq.DSLContext
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class LoanServiceSpec extends Specification {

    LoanDao dao
    DateTimeService dateTimeService
    DSLContext context
    LoanService service

    def setup() {
        dao = Mock()
        dateTimeService = Mock()
        context = Mock()
        service = new LoanService(dao, dateTimeService, context,)
    }

    def "should save loan"() {
        given:
        def loan = new Loan()
        def creationDateTime = Timestamp.valueOf(LocalDateTime.parse("2018-12-29T20:15:30"))
        dateTimeService.getCurrentTimestamp() >> creationDateTime
        context.nextval(Sequences.LOAN_SEQ) >> Consts.ID

        when:
        def savedLoan = service.save(loan)

        then:
        savedLoan.with {
            id == Consts.ID
            createdAt == creationDateTime
        }
    }

    def "should get loan"() {
        when:
        def result = service.fetch(Consts.ID)

        then:
        result.isPresent()
        1 * dao.fetchOneById(Consts.ID) >> new Loan()
    }

    def "should update loan"() {
        given:
        def loan = new Loan([id: Consts.ID])

        when:
        def result = service.update(loan)

        then:
        1 * dao.update(_)
    }

    interface Consts {
        def ID = 5L
    }
}
