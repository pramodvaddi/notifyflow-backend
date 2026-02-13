package com.pramdvaddiraju.notifyflow_backend.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ReminderRequestDto {

    @NotBlank(message = "Email cannot be null")
    private String email;

    @NotBlank(message = "Message cannot be null")
    private String message;

    private LocalDateTime scheduledTime;

    public ReminderRequestDto(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
