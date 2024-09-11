package com.Api.Reservation.controller;

import com.Api.Reservation.Services.Token;
import com.Api.Reservation.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class InterceptorController implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/login")|| requestURI.contains("/users")) {
            return true;
        }
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader);
        if (authorizationHeader == null) {
            throw new Exception("No se encontro cabecera autenticacion");
        }
        byte[] decodedBytes = Base64.getDecoder().decode(authorizationHeader);
        String decodedAuth = new String(decodedBytes, StandardCharsets.UTF_8);


       boolean variable = userService.validToken(Token.fromJsonString(decodedAuth));
       if(variable) {return  true;}
       else{
           return  true;
       }


    }


}
