package com.sl.nextflight.config;

import com.sl.nextflight.controller.LoginController;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Controller
public class TokenAuthFilter implements Filter {


    private final LoginController authController;

    public TokenAuthFilter(LoginController authController) {
        this.authController = authController;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        String contextPath = req.getContextPath();

        Set<String> publicExactPaths = Set.of(
                contextPath + "/home",
                contextPath + "/login",
                contextPath + "/logout",
                contextPath + "/searchFlights"
        );

        // List of allowed path prefixes (static resources etc.)
        List<String> publicPathPrefixes = List.of(
                contextPath + "/auth",
                contextPath + "/css/",
                contextPath + "/js/",
                contextPath + "/images/",
                contextPath + "/fonts/"
        );

        boolean isPublicPath = publicExactPaths.contains(uri) ||
                publicPathPrefixes.stream().anyMatch(uri::startsWith);

        if (isPublicPath) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null || !authController.isValidToken(session)) {
            res.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        chain.doFilter(request, response);
    }
}
