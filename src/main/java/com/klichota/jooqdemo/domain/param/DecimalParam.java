package com.klichota.jooqdemo.domain.param;

import java.math.BigDecimal;


public class DecimalParam extends AbstractParam<BigDecimal> {

    protected final BigDecimal value;

    public DecimalParam(String code, BigDecimal value) {
        super(code);
        this.value = value;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }
}
