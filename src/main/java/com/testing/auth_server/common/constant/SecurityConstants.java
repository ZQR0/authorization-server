package com.testing.auth_server.common.constant;

public class SecurityConstants {
    public static final String LOGIN_PAGE = "/login";
    public static final String LOGIN_ERROR_PAGE = "/login?error";
    public static final String PROCESSING_LOGIN_PAGE_URL = "/processing-login";
    public static final String REGISTER_PAGE = "/sign-up";
    public static final String LOGOUT_PAGE = "/logout";
    public static final String TEMP_OAUTH2_TOKEN_ENDPOINT = "/oauth2/token";
    public static final String TEMP_OAUTH2_REDIRECTION_URI = "http://localhost:3333/code";
    public static final String[] PERMITTED_URL_PATTERNS = {
            LOGIN_PAGE,
            REGISTER_PAGE,
            LOGIN_ERROR_PAGE,
            "/api/v1/users/**",
            LOGOUT_PAGE
    };
}
