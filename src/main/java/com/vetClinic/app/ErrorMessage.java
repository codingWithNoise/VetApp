package com.vetClinic.app;

public enum ErrorMessage {
    NO_CLIENT_ERROR( "Given clientId is incorrect."),
    AUTHORIZATION_ERROR( "Given PIN is incorrect."),
    APPOINTMENT_ERROR( "Chosen time is not available."),
    CANCELLATION_ERROR( "No appointment to cancel.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}