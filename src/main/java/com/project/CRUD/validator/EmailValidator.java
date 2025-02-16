package com.project.CRUD.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class validator {
    public class EmailValidator implements ConstraintValidator<valid.ValidEmail, String> {
        private Pattern pattern;
        private Matcher matcher;
        private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+
        (.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*
                (.[A-Za-z]{2,})$";
        @Override
        public void initialize(valid.ValidEmail constraintAnnotation) {
        }
        @Override
        public boolean isValid(String email, ConstraintValidatorContext context){
            return (validateEmail(email));
        }
        private boolean validateEmail(String email) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            return matcher.matches();
        }
//        private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
//
//        @Override
//        public boolean isValid(String email, ConstraintValidatorContext context) {
//            return email != null && email.matches(EMAIL_PATTERN);
//        }
    }
}

