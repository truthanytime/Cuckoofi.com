package com.cuckoofi.commonclientlibs.constant;

public class AuthConstant {
    public static final String REQUIRED_VALID_TIMEZONE = "Please ensure you provide a valid time zone.";
    public static final String JWT_CLAIMS_KEY_ROLES = "roles";
    public static final String REQUIRED_EMAIL = "Email address is mandatory for this operation.";
    public static final String INVALID_EMAIL = "The provided email address format is invalid. Please use a valid email address.";
    public static final String REQUIRED_FIRST_NAME = "You must provide your first name.";
    public static final String REQUIRED_LAST_NAME = "Your last name is required for this action.";
    public static final String REQUIRED_PASSWORD = "Password is a required field for this operation.";
    public static final String SIGN_IN_SUCCESS = "You have successfully signed in!";
    public static final String SIGN_UP_SUCCESS = "Your sign-up was successful! Welcome!";
    public static final String AUTH_TEST_SUCCESS = "Great! The Authentication Works Well!";
    public static final String INCORRECT_CREDENTIAL = "The email or password you entered is incorrect. Please try again.";
    public static final String EMAIL_IN_USE = "The provided email address is already registered. Please use a different email address.";
    public static final String FAILED_IN_REGISTER = "We couldn't register your new user account. Please try again later or contact support.";
    public static final String DEFAULT_TIMEZONE = "UTC";

    public static class ROLES {
        public static String USER = "USER";
        public static String ADMIN = "ADMIN";
    }

    public static class EMAIL_VERIFY {
        public static Integer VERIFIED = 1;
        public static Integer NOT_VERIFIED = 0;
    }

    public static class SOCIAL_TYPE {
        public static Integer DEFAULT = 0;
    }

    public static class IS_DELETED {
        public static Integer DELETED = 1;
        public static Integer UN_DELETED = 0;
    }

    public static class IS_DISABLED {
        public static Integer DISABLED = 1;
        public static Integer UN_DISABLED = 0;
    }
}
