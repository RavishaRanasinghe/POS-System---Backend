package com.ijse.posDatabase.Security;

public class WebSecurityConfigurerAdapter {
    private Object userDetails;
    String token = JwtTokenProvider.createToken(userDetails);

}
