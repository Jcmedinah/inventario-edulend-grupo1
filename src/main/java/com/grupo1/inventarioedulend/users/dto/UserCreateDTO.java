package com.grupo1.inventarioedulend.users.dto;

public record UserCreateDTO(
    String first_name,
    String last_name,
    String email,
    String password,
    String phone_number,
    String user_role
) {}
