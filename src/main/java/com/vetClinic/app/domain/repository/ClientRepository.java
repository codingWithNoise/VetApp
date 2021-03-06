package com.vetClinic.app.domain.repository;

import com.vetClinic.app.domain.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class ClientRepository {

    @PersistenceContext
    private EntityManager em;

    public ClientRepository() {
    }

    public String getPINForClient(Integer clientId) throws NoResultException {
        Client client = em.createQuery("from Client c where c.clientId=:clientId", Client.class).
                setParameter("clientId", clientId).getSingleResult();
        return client.getClientPIN();
    }
}
