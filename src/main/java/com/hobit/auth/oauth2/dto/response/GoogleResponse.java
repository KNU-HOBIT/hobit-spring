package com.hobit.auth.oauth2.dto.response;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {

    private final Map<String,Object> attributes;

    public GoogleResponse(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    /**
     * Provider 측에서 제공하는 사용자 구분 id 값
     */
    @Override
    public String getProvierId() {
        return attributes.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }
}
