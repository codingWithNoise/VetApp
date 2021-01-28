package com.vetClinic.app.services;

import com.vetClinic.app.domain.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;

class ClientServiceTests {

    @Test
    void clientPINCheckTest() {
        ClientRepository repository = mock(ClientRepository.class);
        ClientService service = new ClientService(repository);
        when(repository.clientPINCheck(1111, 2222)).thenReturn(true);
        assertTrue(service.clientPINcheck(1111, 2222));
    }
}
