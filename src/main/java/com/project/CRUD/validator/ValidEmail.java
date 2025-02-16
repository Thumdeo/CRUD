package com.project.CRUD.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

public class valid {
    @Documented
    @Constraint(validatedBy = validator.EmailValidator.class)
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValidEmail {
        String message() default "Invalid email format";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }


}
