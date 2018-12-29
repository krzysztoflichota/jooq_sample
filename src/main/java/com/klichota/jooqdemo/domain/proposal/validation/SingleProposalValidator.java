package com.klichota.jooqdemo.domain.proposal.validation;

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal;


public interface SingleProposalValidator {

    SingleValidationResult validate(Proposal proposal);
}
