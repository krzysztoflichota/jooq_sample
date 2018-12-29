package com.klichota.jooqdemo.boundary.web;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.LoanExtension;
import com.klichota.jooqdemo.domain.loan.ApplyForLoanExtensionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
class LoanController {

    private final ApplyForLoanExtensionService applyForLoanExtensionService;

    LoanController(ApplyForLoanExtensionService applyForLoanExtensionService) {
        this.applyForLoanExtensionService = applyForLoanExtensionService;
    }

    @PostMapping("/loans/{loanId}/extensions")
    @ResponseStatus(HttpStatus.CREATED)
    LoanExtension applyForLoanExtension(@PathVariable long loanId) {
        return applyForLoanExtensionService.apply(loanId);
    }
}
