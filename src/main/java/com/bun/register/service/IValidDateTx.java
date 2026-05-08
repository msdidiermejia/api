package com.bun.register.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.bun.register.util.DateTxValidatorUtil;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTxValidatorUtil.class)
public @interface IValidDateTx {
    String field();
    String message() default "ERR_FORMAT";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
