package com.security.nacer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.security.nacer.securityService.JpaClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(converter());
	}
	
	@Bean
	public JwtAccessTokenConverter converter() {
		JwtAccessTokenConverter conv = new JwtAccessTokenConverter();
		conv.setSigningKey("signature");
		return conv;
	}
	
	@Bean
	public JpaClientDetailsService cclientDetailsService() {
		return new JpaClientDetailsService();
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		security.checkTokenAccess("isAuthenticated()")
		        .passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
		clients.withClientDetails(cclientDetailsService());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
				 .tokenStore(jwtTokenStore())
				 .accessTokenConverter(converter());

	}

	
}
