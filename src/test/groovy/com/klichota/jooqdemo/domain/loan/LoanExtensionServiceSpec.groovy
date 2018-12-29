package com.klichota.jooqdemo.domain.loan

import com.klichota.jooqdemo.boundary.perstitence.Sequences
import com.klichota.jooqdemo.boundary.perstitence.tables.daos.LoanExtensionDao
import com.klichota.jooqdemo.boundary.perstitence.tables.pojos.LoanExtension
import com.klichota.jooqdemo.domain.datetime.DateTimeService
import org.jooq.DSLContext
import spock.lang.Specification

import java.sql.Timestamp
import java.time.LocalDateTime

class LoanExtensionServiceSpec extends Specification {

    LoanExtensionDao dao
    DateTimeService dateTimeService
    DSLContext context
    LoanExtensionService service

    def setup() {
        dao = Mock()
        dateTimeService = Mock()
        context = Mock()
        service = new LoanExtensionService(dao, dateTimeService, context)
    }

    def "should save loan extension"() {
        given:
        def extension = new LoanExtension()
        def creationDateTime = Timestamp.valueOf(LocalDateTime.parse("2018-12-29T20:15:30"))
        dateTimeService.getCurrentTimestamp() >> creationDateTime
        context.nextval(Sequences.LOAN_EXTENSION_SEQ) >> Consts.ID

        when:
        def savedExtension = service.save(extension)

        then:
        savedExtension.with {
            id == Consts.ID
            createdAt == creationDateTime
        }
    }

    def "should fetch loan extensions"() {
        when:
        service.fetchExtensions(Consts.ID)

        then:
        1 * dao.fetchByLoanId(Consts.ID)
    }

    interface Consts {
        def ID = 5L
    }
}
