package de.ait.events.validation.impl;

import de.ait.events.validation.DateValidator;

import java.time.LocalDate;

public class DateNotEmptyValidatorImpl implements DateValidator {
    @Override
    public void validate(String date) {
        if (date == null) {
            throw new IllegalArgumentException("Date can't be empty");
        }
    }
}
