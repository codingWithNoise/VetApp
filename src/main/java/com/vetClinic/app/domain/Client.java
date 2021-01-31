package com.vetClinic.app.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    private Integer clientId;
    private String clientPIN;
    private String name;
    private String surname;

    public Client() {
    }

    public String getClientPIN() {
        return clientPIN;
    }
}
