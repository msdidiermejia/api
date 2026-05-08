package com.bun.register.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import com.bun.register.config.EnviromentPropertyConfig;
import com.bun.register.service.IValidDateTx;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class DateTxValidatorUtil implements ConstraintValidator<IValidDateTx, String> {

    private EnviromentPropertyConfig env;
    private Pattern pattern;
    private String field;

    public DateTxValidatorUtil(EnviromentPropertyConfig env) {
        this.env = env;
    }

    @Override
    public void initialize(IValidDateTx constraintAnnotation) {
        this.pattern = Pattern.compile(env.getPatterFormatDateContext());
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;

        if (!pattern.matcher(value).matches()) {
            buildError(context);
            return false;
        }

        try {
            OffsetDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return true;
        } catch (DateTimeParseException e) {
            buildError(context);
            return false;
        }
    }

    private void buildError(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                "ERR_FORMAT:" + field
        ).addConstraintViolation();
    }
}
