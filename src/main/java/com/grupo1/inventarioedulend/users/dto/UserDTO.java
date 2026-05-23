package com.grupo1.inventarioedulend.users.dto;

public record UserDTO(
    int userId,
    String first_name,
    String last_name,
    String email,
    String phone_number,
    String user_role
) {}
