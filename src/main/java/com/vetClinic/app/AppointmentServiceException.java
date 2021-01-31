package com.vetClinic.app;

public class AppointmentServiceException extends Exception {
    public AppointmentServiceException(ErrorMessage message) {
        super(message.getMessage());
    }
}

