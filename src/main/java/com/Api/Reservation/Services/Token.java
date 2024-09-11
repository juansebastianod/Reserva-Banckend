package com.Api.Reservation.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Token {
    private Long userId;
    private Long rolId;

    public Token() {
    }

    // Constructor
    public Token(Long userId, Long rolId) {
        this.userId = userId;
        this.rolId = rolId;
    }

    // Getters y Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    // Método para convertir el objeto Token a String (JSON)
    public String toJsonString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String token=objectMapper.writeValueAsString(this);
        return Base64.getEncoder().encodeToString(token.getBytes(StandardCharsets.UTF_8));
    }

    // Método estático para convertir un String (JSON) a un objeto Token
    public static Token fromJsonString(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Token.class);
    }

}
