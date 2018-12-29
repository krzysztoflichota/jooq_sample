package com.klichota.jooqdemo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import spock.lang.Specification

@SpringBootTest
class JooqDemoApplicationContextTest extends Specification {

    @Autowired
    Environment env

    def "context should load"() {
        expect:
        env != null
    }
}

