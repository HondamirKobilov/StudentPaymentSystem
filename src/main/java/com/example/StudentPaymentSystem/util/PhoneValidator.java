package com.example.StudentPaymentSystem.util;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final Pattern UZB_PHONE_REGEX = Pattern.compile("^(\\+998|998)(\\d{9})$");
    private static final Pattern PREFIX_PHONE_REGEX = Pattern.compile("^(\\d{9})$");

    private static final String[] VALID_PREFIXES = {
            "33", "50", "77", "90", "91", "93", "94", "95", "97", "99", "88"
    };

    public static boolean isValid(String phoneNumber) {
        if (phoneNumber == null) return false;

        if (UZB_PHONE_REGEX.matcher(phoneNumber).matches()) {
            String prefix = phoneNumber.substring(phoneNumber.length() - 9, phoneNumber.length() - 7);
            return isValidPrefix(prefix);
        }

        if (PREFIX_PHONE_REGEX.matcher(phoneNumber).matches()) {
            String prefix = phoneNumber.substring(0, 2);
            return isValidPrefix(prefix);
        }

        return false;
    }

    private static boolean isValidPrefix(String prefix) {
        for (String validPrefix : VALID_PREFIXES) {
            if (validPrefix.equals(prefix)) {
                return true;
            }
        }
        return false;
    }
}
