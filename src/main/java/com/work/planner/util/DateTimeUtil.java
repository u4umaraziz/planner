package com.work.planner.util;

import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final String dateFormat = "dd/MM/yyyy";
    public static String formatLocalTime(final LocalTime localTime) {
        try {
            if (localTime != null) {
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                return formatter.format(localTime);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format");
        }
    }

    public static LocalDate formatDate(final String dateStr) {
        try {
            if (StringUtils.hasText(dateStr)) {
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                return LocalDate.parse(dateStr, formatter);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid date");
        }
    }
}
