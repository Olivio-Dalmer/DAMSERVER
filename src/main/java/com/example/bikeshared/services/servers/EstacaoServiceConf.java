package com.example.bikeshared.services.servers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class EstacaoServiceConf {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("xml.soap.station"); // Ajuste para o seu pacote de modelos JAXB
        return marshaller;
    }

    @Bean
    public EstacaoWS estacaoClient(Jaxb2Marshaller marshaller) {
        EstacaoWS client = new EstacaoWS();
        client.setDefaultUri("http://localhost:8081/wsStation");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}

