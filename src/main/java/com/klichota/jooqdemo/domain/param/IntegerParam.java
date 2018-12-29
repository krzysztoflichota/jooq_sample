package com.klichota.jooqdemo.domain.param;


public class IntegerParam extends AbstractParam<Long> {

    protected final long value;

    public IntegerParam(String code, long value) {
        super(code);
        this.value = value;
    }


    @Override
    public Long getValue() {
        return value;
    }
}
