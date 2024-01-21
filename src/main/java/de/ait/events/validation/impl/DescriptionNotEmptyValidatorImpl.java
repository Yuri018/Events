package de.ait.events.validation.impl;

import de.ait.events.validation.DescriptionValidator;

public class DescriptionNotEmptyValidatorImpl implements DescriptionValidator {

    @Override
    public void validate(String description) {
        if (description == null || description.isEmpty() || description.equals(" ")) {
            throw new IllegalArgumentException("Description can't be empty");
        }
    }
}
