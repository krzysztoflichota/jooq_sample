package com.klichota.jooqdemo.boundary.web

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import com.klichota.jooqdemo.domain.loan.ApplyLoanService
import spock.lang.Specification

class ProposalControllerSpec extends Specification {

    ApplyLoanService service
    ProposalController controller

    def setup() {
        service = Mock()
        controller = new ProposalController(service)
    }

    def "should apply for loan"() {
        given:
        def proposal = new Proposal()

        when:
        controller.applyForLoan(proposal)

        then:
        1 * service.apply(proposal)
    }
}
