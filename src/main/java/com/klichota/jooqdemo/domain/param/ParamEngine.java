package com.klichota.jooqdemo.domain.param;

import static com.klichota.jooqdemo.boundary.perstitence.tables.Parameter.PARAMETER;
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.ParameterDao;
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Parameter;
import org.springframework.stereotype.Service;


@Service
public class ParamEngine {

    private final ParameterDao dao;

    ParamEngine(ParameterDao dao) {
        this.dao = dao;
    }

    public IntegerParam getInt(String code) {
        Parameter parameter = dao.fetchOne(PARAMETER.CODE, code);
        return new IntegerParam(code, parameter.getIntValue());
    }

    public DecimalParam getDec(String code) {
        Parameter parameter = dao.fetchOne(PARAMETER.CODE, code);
        return new DecimalParam(code, parameter.getDecimalValue());
    }
}
