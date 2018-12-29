/*
 * This file is generated by jOOQ.
 */
package com.klichota.jooqdemo.boundary.perstitence;


import com.klichota.jooqdemo.boundary.perstitence.tables.Databasechangelog;
import com.klichota.jooqdemo.boundary.perstitence.tables.Databasechangeloglock;
import com.klichota.jooqdemo.boundary.perstitence.tables.Loan;
import com.klichota.jooqdemo.boundary.perstitence.tables.LoanExtension;
import com.klichota.jooqdemo.boundary.perstitence.tables.Parameter;
import com.klichota.jooqdemo.boundary.perstitence.tables.Proposal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1238406487;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.databasechangelog</code>.
     */
    public final Databasechangelog DATABASECHANGELOG = com.klichota.jooqdemo.boundary.perstitence.tables.Databasechangelog.DATABASECHANGELOG;

    /**
     * The table <code>public.databasechangeloglock</code>.
     */
    public final Databasechangeloglock DATABASECHANGELOGLOCK = com.klichota.jooqdemo.boundary.perstitence.tables.Databasechangeloglock.DATABASECHANGELOGLOCK;

    /**
     * The table <code>public.loan</code>.
     */
    public final Loan LOAN = com.klichota.jooqdemo.boundary.perstitence.tables.Loan.LOAN;

    /**
     * The table <code>public.loan_extension</code>.
     */
    public final LoanExtension LOAN_EXTENSION = com.klichota.jooqdemo.boundary.perstitence.tables.LoanExtension.LOAN_EXTENSION;

    /**
     * The table <code>public.parameter</code>.
     */
    public final Parameter PARAMETER = com.klichota.jooqdemo.boundary.perstitence.tables.Parameter.PARAMETER;

    /**
     * The table <code>public.proposal</code>.
     */
    public final Proposal PROPOSAL = com.klichota.jooqdemo.boundary.perstitence.tables.Proposal.PROPOSAL;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        List result = new ArrayList();
        result.addAll(getSequences0());
        return result;
    }

    private final List<Sequence<?>> getSequences0() {
        return Arrays.<Sequence<?>>asList(
            Sequences.LOAN_EXTENSION_SEQ,
            Sequences.LOAN_SEQ,
            Sequences.PARAMETER_SEQ,
            Sequences.PROPOSAL_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Databasechangelog.DATABASECHANGELOG,
            Databasechangeloglock.DATABASECHANGELOGLOCK,
            Loan.LOAN,
            LoanExtension.LOAN_EXTENSION,
            Parameter.PARAMETER,
            Proposal.PROPOSAL);
    }
}
