    package com.bun.register.util;

import org.springframework.stereotype.Component;

@Component
    public class EnmaskDataUtil {

    public String maskValue(String value) {
        if (value == null || value.length() <= 4) {
            return value;
        }
        int hiddenChars = value.length() - 4;
        String maskedPart = String.join("", java.util.Collections.nCopies(hiddenChars, "*"));
        String visiblePart = value.substring(value.length() - 4);
        return maskedPart + visiblePart;
    }

    public String maskValueNumCuenta(String value) {
        if (value == null || value.length() <= 4) {
            return value;
        }

        String maskedPart = "**** **** **** ";
        String visiblePart = value.substring(value.length() - 4);

        return maskedPart + visiblePart;
    }


    public String maskEmail(String email) {
            if (email == null || !email.contains("@")) {
                return email;
            }

            String[] parts = email.split("@");
            String username = parts[0];
            String domain = parts[1];

            if (username.length() == 0) {
                return "***@" + domain;
            }

            return username.charAt(0) + "***@" + domain;
    }

    public String maskPhone(String value) {
        if (value == null || value.length() <= 3) {
            return value;
        }

        String firstChar = value.substring(0, 1);
        String lastTwo = value.substring(value.length() - 2);

        int hiddenChars = value.length() - 3;
        String maskedPart = String.join("", java.util.Collections.nCopies(hiddenChars, "*"));

        return firstChar + maskedPart + lastTwo;
    }
}
