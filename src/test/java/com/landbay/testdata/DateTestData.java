package com.landbay.testdata;

import java.time.LocalDate;

public class DateTestData {

    public static final String YESTERDAY = "2018-10-21";
    public static final String ONE_MONTH = "2018-11-21";

    public static LocalDate yesterday() {
        return LocalDate.of(2018, 10, 21);
    }

    public static LocalDate oneMonth() {
        return LocalDate.of(2018, 11, 21);
    }
}
