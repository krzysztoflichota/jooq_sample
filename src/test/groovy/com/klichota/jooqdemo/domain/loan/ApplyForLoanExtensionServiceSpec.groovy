package com.klichota.jooqdemo.domain.loan

import com.klichota.jooqdemo.ParamEngineSpecification
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Loan
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.LoanExtension
import com.klichota.jooqdemo.domain.datetime.DateTimeService

import java.sql.Date
import java.time.LocalDate

class ApplyForLoanExtensionServiceSpec extends ParamEngineSpecification {

    LoanService loanService
    LoanExtensionService loanExtensionService
    DateTimeService dateTimeService
    ApplyForLoanExtensionService service

    def setup() {
        loanService = Mock()
        loanExtensionService = Mock()
        dateTimeService = Mock()
        service = new ApplyForLoanExtensionService(loanService, loanExtensionService, engine, dateTimeService)
    }

    def "should throw when applied for not existing loan"() {
        given:
        loanService.fetch(Consts.ID) >> Optional.empty()

        when:
        service.apply(Consts.ID)

        then:
        thrown IllegalArgumentException
    }

    def "should extend loan date to"() {
        given:
        def loan = new Loan([id: Consts.ID, dateTo: Date.valueOf(LocalDate.parse("2018-12-19"))])
        loanService.fetch(Consts.ID) >> Optional.of(loan)
        loanExtensionService.save(_ as LoanExtension) >> {LoanExtension extension -> extension}
        loanExtensionService.fetchExtensions(Consts.ID) >> Collections.emptyList()
        mockParam(LoanParam.DAYS_TO_EXTEND.name(), 10)

        when:
        def result = service.apply(Consts.ID)

        then:
        result.with {
            loanId == Consts.ID
            extendedTo == Date.valueOf(LocalDate.parse("2018-12-29"))
        }
    }

    def "should extend recent extension date to"() {
        given:
        def loan = new Loan([id: Consts.ID, dateTo: Date.valueOf(LocalDate.parse("2018-12-09"))])
        loanService.fetch(Consts.ID) >> Optional.of(loan)
        loanExtensionService.save(_ as LoanExtension) >> {LoanExtension extension -> extension}
        loanExtensionService.fetchExtensions(Consts.ID) >> [new LoanExtension([extendedTo: Date.valueOf(LocalDate.parse("2018-12-19"))])]
        mockParam(LoanParam.DAYS_TO_EXTEND.name(), 10)

        when:
        def result = service.apply(Consts.ID)

        then:
        result.with {
            loanId == Consts.ID
            extendedTo == Date.valueOf(LocalDate.parse("2018-12-29"))
        }
    }

    interface Consts {
        def ID = 5L
    }
}
