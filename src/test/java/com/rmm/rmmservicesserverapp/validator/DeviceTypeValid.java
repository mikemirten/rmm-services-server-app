package com.rmm.rmmservicesserverapp.validator;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.validation.ConstraintValidatorContext;

public class DeviceTypeValid
{
    @Test
    public void isValid()
    {
        DeviceTypeValidator validator = new DeviceTypeValidator();

        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);

        assertTrue(validator.isValid("WINDOWS_WORKSTATION", context));
        assertTrue(validator.isValid("APPLE_MACKINTOSH", context));
        assertFalse(validator.isValid("PDP_11", context));
    }
}
