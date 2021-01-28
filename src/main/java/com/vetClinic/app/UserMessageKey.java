package com.vetClinic.app;

public enum UserMessageKey {
    AUTHORISATION_FAILED( "Given clientId and/or password are incorrect."),
    APPOINTMENT_NEW_DONE( "The appointment is scheduled. ID of the appointment: "),
    APPOITNMENT_FAILED( "Chosen time is not available."),
    CANCELLATION_FAILED( "No appointment to cancel."),
    CANCELLATION_DONE( "The appointment was cancelled.");
    private final String message;

    UserMessageKey(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}