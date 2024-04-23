package com.iba.ipr.dto.request;


import javax.validation.constraints.NotEmpty;

public record UserPasswordResetRequest(
        @NotEmpty
        char[] oldPassword,

        @NotEmpty
        char[] newPassword) {
}
