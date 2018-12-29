package com.klichota.jooqdemo

import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@Sql('classpath:/mock-data.sql')
class ApplyForLoanExtensionScenario extends IntegrationTestTemplate {

    def "should get loan extension"() {
        when:
        def response = mvc.perform(
                post('/loans/{loanId}/extensions', Consts.LOAN_ID)
        )

        then:
        response
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath('$.loanId').value(Consts.LOAN_ID))
                .andExpect(jsonPath('$.extendedTo').value('2018-11-27'))
    }

    interface Consts {
        def LOAN_ID = 1000
    }
}
