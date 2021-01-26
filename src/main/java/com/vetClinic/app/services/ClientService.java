package com.vetClinic.app.services;

import com.vetClinic.app.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public boolean clientPINcheck(Integer clientId, Integer clientPIN) {
        return repository.clientPINCheck(clientId, clientPIN);
    }
}
