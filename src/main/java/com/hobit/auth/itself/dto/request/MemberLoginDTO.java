package com.hobit.auth.itself.dto.request;

public record MemberLoginDTO(
        String email,
        String password
) {
}
