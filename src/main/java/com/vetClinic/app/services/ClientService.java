package com.vetClinic.app.services;

import com.vetClinic.app.ClientServiceException;
import com.vetClinic.app.ErrorMessage;
import com.vetClinic.app.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public boolean isPINCorrect(Integer clientId, String PIN) throws ClientServiceException {
        String clientPIN;
        try {
            clientPIN = repository.getPINForClient(clientId);
        } catch (NoResultException e) {
            throw new ClientServiceException(ErrorMessage.NO_CLIENT_ERROR);
        }
        return clientPIN.equals(PIN);
    }
}
