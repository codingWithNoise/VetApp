package com.vetClinic.app.services;

import com.vetClinic.app.ClientServiceException;
import com.vetClinic.app.domain.repository.ClientRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;

class ClientServiceTests {

    @Test
    void clientPINCheckTest() throws ClientServiceException {
        ClientRepository repository = mock(ClientRepository.class);
        ClientService service = new ClientService(repository);
        when(repository.getPINForClient(1111)).thenReturn("2222");
        assertTrue(service.isPINCorrect(1111, "2222"));
    }
}
