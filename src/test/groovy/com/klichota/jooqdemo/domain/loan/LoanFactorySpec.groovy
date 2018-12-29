package com.klichota.jooqdemo.domain.loan

import com.klichota.jooqdemo.ParamEngineSpecification
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import com.klichota.jooqdemo.domain.datetime.DateTimeService

import java.sql.Timestamp
import java.time.LocalDateTime

class LoanFactorySpec extends ParamEngineSpecification {

    DateTimeService dateTimeService
    LoanFactory factory

    def setup() {
        dateTimeService = Mock()
        factory = new LoanFactory(dateTimeService, engine)
    }

    def "should create loan from proposal"() {
        given:
        def dateTime = LocalDateTime.parse("2018-12-29T20:15:30")
        def creationTimestamp = Timestamp.valueOf(dateTime)
        def proposal = new Proposal([
                id: Consts.PROPOSAL_ID,
                term: Consts.TERM,
                amount: Consts.AMOUNT,
                createdAt: creationTimestamp
        ])
        dateTimeService.getCurrentDate() >> dateTime.toLocalDate()
        mockParam(LoanParam.PRINCIPAL_MULTIPLIER.name(), Consts.COST_MULTIPLIER)

        when:
        def loan = factory.create(proposal)

        then:
        loan.with {
            status == LoanStatus.ACTIVE.name()
            dateFrom == new Date(2018, 11, 30)
            dateTo == new Date(2019, 1, 7)
            amount == Consts.AMOUNT
            proposalId == Consts.PROPOSAL_ID
            cost == Consts.AMOUNT * Consts.COST_MULTIPLIER
        }
    }

    interface Consts {
        def PROPOSAL_ID = 5L
        def TERM = 40
        def AMOUNT = 4000.0
        def COST_MULTIPLIER = 0.1
    }
}
