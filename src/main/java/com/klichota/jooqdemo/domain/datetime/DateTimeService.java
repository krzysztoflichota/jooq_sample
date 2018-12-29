package com.klichota.jooqdemo.domain.datetime;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;


@Service
public class DateTimeService {

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(getCurrentDateTime());
    }
}
