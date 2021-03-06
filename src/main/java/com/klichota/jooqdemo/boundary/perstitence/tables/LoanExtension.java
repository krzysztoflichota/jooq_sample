/*
 * This file is generated by jOOQ.
 */
package com.klichota.jooqdemo.boundary.perstitence.tables;


import com.klichota.jooqdemo.boundary.perstitence.Indexes;
import com.klichota.jooqdemo.boundary.perstitence.Keys;
import com.klichota.jooqdemo.boundary.perstitence.Public;
import com.klichota.jooqdemo.boundary.perstitence.tables.records.LoanExtensionRecord;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class LoanExtension extends TableImpl<LoanExtensionRecord> {

    private static final long serialVersionUID = -941872132;

    /**
     * The reference instance of <code>public.loan_extension</code>
     */
    public static final LoanExtension LOAN_EXTENSION = new LoanExtension();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LoanExtensionRecord> getRecordType() {
        return LoanExtensionRecord.class;
    }

    /**
     * The column <code>public.loan_extension.id</code>.
     */
    public final TableField<LoanExtensionRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.loan_extension.extended_to</code>.
     */
    public final TableField<LoanExtensionRecord, Date> EXTENDED_TO = createField("extended_to", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>public.loan_extension.created_at</code>.
     */
    public final TableField<LoanExtensionRecord, Timestamp> CREATED_AT = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>public.loan_extension.loan_id</code>.
     */
    public final TableField<LoanExtensionRecord, Long> LOAN_ID = createField("loan_id", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>public.loan_extension</code> table reference
     */
    public LoanExtension() {
        this(DSL.name("loan_extension"), null);
    }

    /**
     * Create an aliased <code>public.loan_extension</code> table reference
     */
    public LoanExtension(String alias) {
        this(DSL.name(alias), LOAN_EXTENSION);
    }

    /**
     * Create an aliased <code>public.loan_extension</code> table reference
     */
    public LoanExtension(Name alias) {
        this(alias, LOAN_EXTENSION);
    }

    private LoanExtension(Name alias, Table<LoanExtensionRecord> aliased) {
        this(alias, aliased, null);
    }

    private LoanExtension(Name alias, Table<LoanExtensionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> LoanExtension(Table<O> child, ForeignKey<O, LoanExtensionRecord> key) {
        super(child, key, LOAN_EXTENSION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PK_LOAN_EXTENSION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<LoanExtensionRecord> getPrimaryKey() {
        return Keys.PK_LOAN_EXTENSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<LoanExtensionRecord>> getKeys() {
        return Arrays.<UniqueKey<LoanExtensionRecord>>asList(Keys.PK_LOAN_EXTENSION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<LoanExtensionRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<LoanExtensionRecord, ?>>asList(Keys.LOAN_EXTENSION__FK_LOAN_EXTENSION_LOAN);
    }

    public Loan loan() {
        return new Loan(this, Keys.LOAN_EXTENSION__FK_LOAN_EXTENSION_LOAN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoanExtension as(String alias) {
        return new LoanExtension(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LoanExtension as(Name alias) {
        return new LoanExtension(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public LoanExtension rename(String name) {
        return new LoanExtension(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public LoanExtension rename(Name name) {
        return new LoanExtension(name, null);
    }
}
