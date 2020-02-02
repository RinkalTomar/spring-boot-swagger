package com.java.constants;

public class Constants {
	
	public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String CLIENT_ID = "greatgames";
    public static final String RESOURCE_ID = "GREATGAMES_APIS";
    public static final String CLIENT_SECRET = "$2a$04$7Ljl0S56V5gO7ahT3nqQmOBcuCFKbrjpDyQzCM5rPAEfMCF/es.AC";
    public static final String CLIENT_SECRET_PLAIN = "GreatGames";
    public static final String CLIENT_ROLE_CLIENT = "ROLE_CLIENT";
    public static final String CLIENT_ROLE_TRUSTED_CLIENT = "ROLE_TRUSTED_CLIENT";
    public static final String SCOPE_READ = "read";
    public static final String SCOPE_WRITE = "write";
    public static final String SCOPE_TRUST = "trust";
    public static final String GRANT_TYPE_CLIENT_REFRESH_TOKEN = "refresh_token";
    public static final String TEMP_PASSWORD = "GreatGames";

    public static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 3 * 24 * 60 * 60; //3 days
    public static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60; // 1 day
    public static final Integer INVITE_ACTIVATION_TOKEN_EXPIRES_ON_DAYS = 30; //30 Days
    public static final Long PASSOWORD_RESET_TOKEN_EXPIRY_DURATION = 2L;

}
