package com.security.nacer.securityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.security.nacer.entities.Client;
import com.security.nacer.repositories.ClientRepository;

import lombok.NoArgsConstructor;

@Service
//@NoArgsConstructor
public class JpaClientDetailsService implements ClientDetailsService{

	@Autowired
	private ClientRepository clientRepository;
	
//	public JpaClientDetailsService(ClientRepository clientRepository) {
//		this.clientRepository = clientRepository;
//	}

	@Override
	public ClientDetails loadClientByClientId(String clientId)
			throws ClientRegistrationException {
		// TODO Auto-generated method stub
		Client clientWithClientId = 
				clientRepository.findClientByClientId(clientId)
					.orElseThrow(
					    ()->new ClientRegistrationException("No client with this ,clientId"));
		return new SecurityClient(clientWithClientId);
	}

}
