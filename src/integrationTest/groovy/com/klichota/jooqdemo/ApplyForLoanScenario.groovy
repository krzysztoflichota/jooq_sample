package com.klichota.jooqdemo

import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.Proposal
import org.springframework.http.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class ApplyForLoanScenario extends IntegrationTestTemplate {

    def "should get loan"() {
        given:
        def proposal = new Proposal([amount: Consts.AMOUNT, term: Consts.TERM])

        when:
        def response = mvc.perform(
                post('/proposals')
                .content(mapper.writeValueAsString(proposal))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )

        then:
        response
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath('$.loanId').isNotEmpty())
                .andExpect(jsonPath('$.proposal.id').isNotEmpty())
                .andExpect(jsonPath('$.proposal.term').value(Consts.TERM))
                .andExpect(jsonPath('$.proposal.amount').value(Consts.AMOUNT))
    }

    interface Consts {
        def AMOUNT = 3000.0
        def TERM = 60
    }
}
