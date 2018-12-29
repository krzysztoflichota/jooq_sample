package com.klichota.jooqdemo

import com.klichota.jooqdemo.domain.param.DecimalParam
import com.klichota.jooqdemo.domain.param.IntegerParam;
import com.klichota.jooqdemo.domain.param.ParamEngine;
import spock.lang.Specification;


class ParamEngineSpecification extends Specification {

    ParamEngine engine

    def setup() {
        engine = Mock()
    }

    def mockParam(String code, long value) {
        engine.getInt(code) >> new IntegerParam(code, value)
    }

    def mockParam(String code, BigDecimal value) {
        engine.getDec(code) >> new DecimalParam(code, value)
    }
}
