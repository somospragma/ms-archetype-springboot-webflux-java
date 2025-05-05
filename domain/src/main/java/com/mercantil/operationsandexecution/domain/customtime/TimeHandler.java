package com.mercantil.operationsandexecution.domain.customtime;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.regex.Pattern;

@UtilityClass
public class TimeHandler {
    public static final String UTC_5 = "UTC-5";
    private static final String DATE_OFFSET = "-05:00";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String REGEX_DATE_FORMAT =
            "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}[+-]\\d{2}:\\d{2}$";

    private static final Pattern pattern = Pattern.compile(REGEX_DATE_FORMAT);

    static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern(DATE_FORMAT)
            .toFormatter();

    public static String currentDate() {
        var date = LocalDateTime.now(ZoneOffset.of(DATE_OFFSET));
        return date.format(dateTimeFormatter);
    }

    public static boolean isValid(String dateStr) {
        return pattern.matcher(dateStr).matches();
    }

    public static String formatDate(LocalDateTime date) {
        return date.format(dateTimeFormatter);
    }
}
