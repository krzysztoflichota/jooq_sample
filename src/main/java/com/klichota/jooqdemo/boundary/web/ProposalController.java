package com.klichota.jooqdemo.boundary.web;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.loan.ApplyLoanResult;
import com.klichota.jooqdemo.domain.loan.ApplyLoanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
class ProposalController {

    private final ApplyLoanService applyLoanService;

    ProposalController(ApplyLoanService applyLoanService) {
        this.applyLoanService = applyLoanService;
    }

    @PostMapping("/proposals")
    @ResponseStatus(HttpStatus.CREATED)
    ApplyLoanResult applyForLoan(@RequestBody Proposal proposal) {
        return applyLoanService.apply(proposal);
    }
}
