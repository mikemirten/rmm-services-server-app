package com.rmm.rmmservicesserverapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DeviceTypeValidator.class)
public @interface DeviceTypeValid
{
    String message() default "Invalid device type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
