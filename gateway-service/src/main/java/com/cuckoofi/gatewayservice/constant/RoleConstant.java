package com.cuckoofi.gatewayservice.constant;

public class RoleConstant {

    public static final String ROLE_SUPER_ADMIN = "ADMIN";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_PARENT = "PARENT";
    public static final String ROLE_TUTOR = "TUTOR";
    public static final Integer USER_TYPE_SUPER_ADMIN = 0;
    public static final Integer USER_TYPE_ADMIN = 1;
    public static final Integer USER_TYPE_PARENT = 2;
    public static final Integer USER_TYPE_TUTOR = 3;
    public static final Integer USER_TYPE_NONE = 4;

    public static final String[] USER_ROLES = {"SUPER ADMIN", "ADMIN", "PARENT", "TUTOR", "NONE"};

    public static String getRoleString( Integer role ){
        if( role < USER_TYPE_SUPER_ADMIN || USER_TYPE_TUTOR < role )
            return USER_ROLES[USER_TYPE_NONE];
        return USER_ROLES[role];
    }
}
