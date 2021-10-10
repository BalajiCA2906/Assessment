package com.balaji.springappsecurityjwt.model;

public class AuthenticatioResponse {
    private final String jwt;

    public AuthenticatioResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt(){
        return jwt;
    }
}
