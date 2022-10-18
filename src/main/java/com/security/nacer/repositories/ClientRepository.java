package com.security.nacer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.nacer.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	public Optional<Client> findClientByClientId(String clientId);
}
