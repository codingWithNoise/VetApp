package com.vetClinic.app.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    private Integer clientId;
    private Integer clientPIN;
    private String name;
    private String surname;

    public Client() {
    }

    public Integer getClientPIN() {
        return clientPIN;
    }
}
