package com.klichota.jooqdemo.infrastructure.persistence;

import com.klichota.jooqdemo.boundary.perstitence.tables.daos.LoanDao;
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.LoanExtensionDao;
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.ParameterDao;
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.ProposalDao;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
class PersistenceConfig {

    @Bean
    ParameterDao createParameterDao(DSLContext ctx) {
        log.info("Successfully created Parameter DAO");
        return new ParameterDao(ctx.configuration());
    }

    @Bean
    ProposalDao createProposalDao(DSLContext ctx) {
        log.info("Successfully created Proposal DAO");
        return new ProposalDao(ctx.configuration());
    }

    @Bean
    LoanDao createLoanDao(DSLContext ctx) {
        log.info("Successfully created Loan DAO");
        return new LoanDao(ctx.configuration());
    }

    @Bean
    LoanExtensionDao createLoanExtensionDao(DSLContext ctx) {
        log.info("Successfully created Loan Extension DAO");
        return new LoanExtensionDao(ctx.configuration());
    }
}
