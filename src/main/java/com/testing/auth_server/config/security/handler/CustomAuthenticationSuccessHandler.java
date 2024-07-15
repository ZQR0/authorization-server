package com.testing.auth_server.config.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String authenticationSuccessUrl;
    private String customHandlerHeaderName;
    private String savedRequestUrlStartsWith; // Начало сохранённого в кэше URL

    private final RequestCache requestCache = new HttpSessionRequestCache();


    public CustomAuthenticationSuccessHandler(
            String authenticationSuccessUrl,
            String customHandlerHeaderName,
            String savedRequestUrlStartsWith
    )
    {
        this.authenticationSuccessUrl = authenticationSuccessUrl;
        this.customHandlerHeaderName = customHandlerHeaderName;
        this.savedRequestUrlStartsWith = savedRequestUrlStartsWith;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        SavedRequest savedRequest = this.requestCache.getRequest(request, response);
        if (savedRequest == null) {
            response.setHeader(this.customHandlerHeaderName, this.authenticationSuccessUrl);
        } else {
            this.requestCache.removeRequest(request, response);
            this.clearTemporaryAuthenticationAttributes(request);
            String targetUrl = savedRequest.getRedirectUrl();

            if (targetUrl.startsWith(this.savedRequestUrlStartsWith)) {
                response.setHeader(this.customHandlerHeaderName, targetUrl);
            } else {
                response.setHeader(this.customHandlerHeaderName, this.authenticationSuccessUrl);
            }
        }

        response.sendRedirect("code");
        log.debug("Custom success handler works!!!");
    }


    // Удаление временных аттрибутов, использующихся в процессе аутентификации
    private void clearTemporaryAuthenticationAttributes(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        if (httpSession != null) {
            httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }

}
