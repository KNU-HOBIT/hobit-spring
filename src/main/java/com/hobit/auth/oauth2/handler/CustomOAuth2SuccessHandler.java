package com.hobit.auth.oauth2.handler;

import com.hobit.auth.Principal.PrincipalDetails;
import com.hobit.auth.jwt.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        PrincipalDetails oAuth2User = (PrincipalDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = oAuth2User.getAuthorities();

        String email = oAuth2User.getEmail();
        String role = authorities.iterator().next().getAuthority();

        String accessToken = jwtService.createAccessToken(email, role);
        String refreshToken =  jwtService.createRefreshToken();
        jwtService.saveRefreshToken(email, refreshToken);

        response.setHeader("Accesstoken", accessToken);
        response.addCookie(jwtService.createCookie("Authorization-refresh", refreshToken));
        response.sendRedirect("http://localhost:3000");

        log.info("OAuth2 로그인에 성공하였습니다. 이메일 : {}",  oAuth2User.getEmail());
        log.info("OAuth2 로그인에 성공하였습니다. Access Token : {}",  accessToken);
        log.info("OAuth2 로그인에 성공하였습니다. Refresh Token : {}",  refreshToken);
    }
}
