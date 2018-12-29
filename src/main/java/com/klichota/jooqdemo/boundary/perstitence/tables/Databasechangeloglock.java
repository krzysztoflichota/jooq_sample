/*
 * This file is generated by jOOQ.
 */
package com.klichota.jooqdemo.boundary.perstitence.tables;


import com.klichota.jooqdemo.boundary.perstitence.Indexes;
import com.klichota.jooqdemo.boundary.perstitence.Keys;
import com.klichota.jooqdemo.boundary.perstitence.Public;
import com.klichota.jooqdemo.boundary.perstitence.tables.records.DatabasechangeloglockRecord;

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
public class Databasechangeloglock extends TableImpl<DatabasechangeloglockRecord> {

    private static final long serialVersionUID = 655019442;

    /**
     * The reference instance of <code>public.databasechangeloglock</code>
     */
    public static final Databasechangeloglock DATABASECHANGELOGLOCK = new Databasechangeloglock();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DatabasechangeloglockRecord> getRecordType() {
        return DatabasechangeloglockRecord.class;
    }

    /**
     * The column <code>public.databasechangeloglock.id</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.databasechangeloglock.locked</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Boolean> LOCKED = createField("locked", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>public.databasechangeloglock.lockgranted</code>.
     */
    public final TableField<DatabasechangeloglockRecord, Timestamp> LOCKGRANTED = createField("lockgranted", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * The column <code>public.databasechangeloglock.lockedby</code>.
     */
    public final TableField<DatabasechangeloglockRecord, String> LOCKEDBY = createField("lockedby", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>public.databasechangeloglock</code> table reference
     */
    public Databasechangeloglock() {
        this(DSL.name("databasechangeloglock"), null);
    }

    /**
     * Create an aliased <code>public.databasechangeloglock</code> table reference
     */
    public Databasechangeloglock(String alias) {
        this(DSL.name(alias), DATABASECHANGELOGLOCK);
    }

    /**
     * Create an aliased <code>public.databasechangeloglock</code> table reference
     */
    public Databasechangeloglock(Name alias) {
        this(alias, DATABASECHANGELOGLOCK);
    }

    private Databasechangeloglock(Name alias, Table<DatabasechangeloglockRecord> aliased) {
        this(alias, aliased, null);
    }

    private Databasechangeloglock(Name alias, Table<DatabasechangeloglockRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Databasechangeloglock(Table<O> child, ForeignKey<O, DatabasechangeloglockRecord> key) {
        super(child, key, DATABASECHANGELOGLOCK);
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
        return Arrays.<Index>asList(Indexes.DATABASECHANGELOGLOCK_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DatabasechangeloglockRecord> getPrimaryKey() {
        return Keys.DATABASECHANGELOGLOCK_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DatabasechangeloglockRecord>> getKeys() {
        return Arrays.<UniqueKey<DatabasechangeloglockRecord>>asList(Keys.DATABASECHANGELOGLOCK_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Databasechangeloglock as(String alias) {
        return new Databasechangeloglock(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Databasechangeloglock as(Name alias) {
        return new Databasechangeloglock(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangeloglock rename(String name) {
        return new Databasechangeloglock(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Databasechangeloglock rename(Name name) {
        return new Databasechangeloglock(name, null);
    }
}
