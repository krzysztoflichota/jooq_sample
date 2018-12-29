package com.klichota.jooqdemo.boundary.web

import com.klichota.jooqdemo.domain.loan.ApplyForLoanExtensionService
import spock.lang.Specification

class LoanControllerSpec extends Specification {

    ApplyForLoanExtensionService service
    LoanController loanController

    def setup() {
        service = Mock()
        loanController = new LoanController(service)
    }

    def "should apply for loan extension"() {
        when:
        loanController.applyForLoanExtension(Consts.ID)

        then:
        1 * service.apply(Consts.ID)
    }

    interface Consts {
        def ID = 5L
    }
}
