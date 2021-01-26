package com.vetClinic.app.services;

import com.vetClinic.app.domain.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;

class ClientServiceTests {

    private ClientRepository repository;
    private ClientService service;

    @Test
    void clientPINCheckTest() {
        repository = mock(ClientRepository.class);
        service = new ClientService(repository);
        when(repository.clientPINCheck(1111, 2222)).thenReturn(true);
        assertTrue(service.clientPINcheck(1111, 2222));
    }
}
