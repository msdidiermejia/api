package com.bun.register.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;

import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;


import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceConfig.class);

	@Autowired
	private EnviromentPropertyConfig enviromentPropertyConfig;
	
    @Bean
    DataSource dataSource() throws Exception {

        log.info("Iniciando configuración del DataSource dinámico");

        log.debug("Creando cliente de SecretsManager en región: {}", enviromentPropertyConfig.getRegion());
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.of(enviromentPropertyConfig.getRegion()))
                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .build();

        log.debug("Obteniendo secreto con id: {}", enviromentPropertyConfig.getSecretId());
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(enviromentPropertyConfig.getSecretId()) 
                .build();

        GetSecretValueResponse getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        String secretJson = getSecretValueResponse.secretString();
        log.info("Secreto obtenido exitosamente desde SecretsManager");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> secretMap = mapper.readValue(secretJson, Map.class);
        log.debug("Secreto parseado correctamente, configurando HikariDataSource");
		
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(enviromentPropertyConfig.getUrlDB());
        ds.setUsername(secretMap.get(enviromentPropertyConfig.getUserDB()));
        ds.setPassword(secretMap.get(enviromentPropertyConfig.getPassDB()));

        ds.setDriverClassName("oracle.jdbc.OracleDriver");
        ds.addDataSourceProperty("oracle.jdbc.timezoneAsRegion", "false");

        log.info("DataSource configurado exitosamente con URL: {}", enviromentPropertyConfig.getUrlDB());
        return ds;
    }
}

