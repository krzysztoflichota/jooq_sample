/*
 * This file is generated by jOOQ.
 */
package com.klichota.jooqdemo.boundary.perstitence.tables;


import com.klichota.jooqdemo.boundary.perstitence.Indexes;
import com.klichota.jooqdemo.boundary.perstitence.Keys;
import com.klichota.jooqdemo.boundary.perstitence.Public;
import com.klichota.jooqdemo.boundary.perstitence.tables.records.ParameterRecord;

import java.math.BigDecimal;
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
public class Parameter extends TableImpl<ParameterRecord> {

    private static final long serialVersionUID = -1009927606;

    /**
     * The reference instance of <code>public.parameter</code>
     */
    public static final Parameter PARAMETER = new Parameter();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ParameterRecord> getRecordType() {
        return ParameterRecord.class;
    }

    /**
     * The column <code>public.parameter.id</code>.
     */
    public final TableField<ParameterRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.parameter.code</code>.
     */
    public final TableField<ParameterRecord, String> CODE = createField("code", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.parameter.int_value</code>.
     */
    public final TableField<ParameterRecord, Long> INT_VALUE = createField("int_value", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.parameter.decimal_value</code>.
     */
    public final TableField<ParameterRecord, BigDecimal> DECIMAL_VALUE = createField("decimal_value", org.jooq.impl.SQLDataType.NUMERIC, this, "");

    /**
     * Create a <code>public.parameter</code> table reference
     */
    public Parameter() {
        this(DSL.name("parameter"), null);
    }

    /**
     * Create an aliased <code>public.parameter</code> table reference
     */
    public Parameter(String alias) {
        this(DSL.name(alias), PARAMETER);
    }

    /**
     * Create an aliased <code>public.parameter</code> table reference
     */
    public Parameter(Name alias) {
        this(alias, PARAMETER);
    }

    private Parameter(Name alias, Table<ParameterRecord> aliased) {
        this(alias, aliased, null);
    }

    private Parameter(Name alias, Table<ParameterRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Parameter(Table<O> child, ForeignKey<O, ParameterRecord> key) {
        super(child, key, PARAMETER);
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
        return Arrays.<Index>asList(Indexes.PK_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ParameterRecord> getPrimaryKey() {
        return Keys.PK_PARAMETER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ParameterRecord>> getKeys() {
        return Arrays.<UniqueKey<ParameterRecord>>asList(Keys.PK_PARAMETER);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parameter as(String alias) {
        return new Parameter(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parameter as(Name alias) {
        return new Parameter(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Parameter rename(String name) {
        return new Parameter(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Parameter rename(Name name) {
        return new Parameter(name, null);
    }
}
