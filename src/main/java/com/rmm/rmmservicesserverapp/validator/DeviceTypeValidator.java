package com.rmm.rmmservicesserverapp.validator;

import com.rmm.rmmservicesserverapp.domain.model.DeviceType;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator of device type
 */
@Component
public class DeviceTypeValidator implements ConstraintValidator<DeviceTypeValid, String>
{
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        for (DeviceType type: DeviceType.values())
        {
            if (type.name().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
