package com.hobit.auth.oauth2.dto.response;

public interface OAuth2Response {

    String getProvider();
    String getProvierId();
    String getEmail();
    String getName();

}
