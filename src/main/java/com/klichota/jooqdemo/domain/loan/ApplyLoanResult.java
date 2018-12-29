package com.klichota.jooqdemo.domain.loan;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;
import com.klichota.jooqdemo.domain.proposal.validation.ValidationSummary;
import lombok.Data;


@Data
public class ApplyLoanResult {

    private final ValidationSummary validationSummary;
    private final Proposal proposal;
    private final Long loanId;
}
