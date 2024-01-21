package de.ait.events.validation.impl;

import de.ait.events.validation.DateValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateFormatValidatorImpl implements DateValidator {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public void validate(String date) {

        Pattern pattern = Pattern.compile(DATE_FORMAT);
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()){
            throw new IllegalArgumentException(("Incorrect date input format. Input format: year-month-day"));
        }

    }
}
