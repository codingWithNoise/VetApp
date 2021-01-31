package com.vetClinic.app;

public class ClientServiceException extends Exception {
    public ClientServiceException(ErrorMessage message) {
        super(message.getMessage());
    }
}
