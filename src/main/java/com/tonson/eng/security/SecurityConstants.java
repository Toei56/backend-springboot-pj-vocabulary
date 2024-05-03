package com.tonson.eng.security;

public interface SecurityConstants {

    String SECRET_KEY = "TonsonTOKEN";

    String TOKEN_PREFIX = "Bearer ";

    String HEADER_AUTHORIZATION = "Authorization";

    String CLAIMS_ROLE = "role";

    long EXPIRATION_TIME = (5 * 60 * 1000);
}
