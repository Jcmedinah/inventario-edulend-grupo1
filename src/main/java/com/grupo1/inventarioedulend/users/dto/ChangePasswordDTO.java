package com.grupo1.inventarioedulend.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChangePasswordDTO(
    @JsonProperty("currentPassword") String currentPassword,
    @JsonProperty("newPassword") String newPassword,
    @JsonProperty("confirmPassword") String confirmPassword
) {}
