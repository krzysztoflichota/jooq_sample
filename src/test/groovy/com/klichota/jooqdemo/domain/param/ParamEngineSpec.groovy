package com.klichota.jooqdemo.domain.param

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Parameter
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.ParameterDao
import spock.lang.Specification

import static com.klichota.jooqdemo.boundary.perstitence.tables.Parameter.PARAMETER

class ParamEngineSpec extends Specification {

    ParameterDao dao
    ParamEngine engine

    def setup() {
        dao = Mock()
        engine = new ParamEngine(dao)
    }

    def "should return integer parameter"() {
        given:
        def code = 'code3'
        def value = 5L
        dao.fetchOne(PARAMETER.CODE, code) >> new Parameter(1L, code, value, null)

        when:
        def result = engine.getInt(code)

        then:
        result.code == code
        result.value == value
    }

    def "should return decimal parameter"() {
        given:
        def code = 'code3'
        def value = 0.5
        dao.fetchOne(PARAMETER.CODE, code) >> new Parameter(1L, code, null, value)

        when:
        def result = engine.getDec(code)

        then:
        result.code == code
        result.value == value
    }
}
