package com.example.bikeshared.services.user.ciclista;

import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import jakarta.xml.bind.JAXBElement;
import xml.soap.user.CiclistaCreat;
import xml.soap.user.CiclistaLoginRequest;
import xml.soap.user.LoginRequest;
import xml.soap.user.UserClientResponse;

@Endpoint
@Component
public class CiclistaEndPoint {
    
    private static final String NAMESPACE_URI = "http://user.soap.xml";
    
    @Autowired
    private CiclistaService ciclistaService;

	/**
	 *
	 * @param {@link CiclistaCreat}
	 * @return {@link UserClientResponse}
	 */

    // ENDPOINT CRIAR NOVO CICLISTA
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CiclistaCreat")
    @ResponsePayload
    public JAXBElement<UserClientResponse>  addUser(@RequestPayload  JAXBElement<CiclistaCreat> request) {
        System.out.println(" == ENTRANDO NO SERVIÇO CRIAR CICLISTA");
        CiclistaCreat ciclistaCreat = request.getValue();
        UserClientResponse response = ciclistaService.addCiclista(ciclistaCreat);
        QName qName = new QName(NAMESPACE_URI, "UserClientResponse");
        JAXBElement<UserClientResponse> jaxbElementResponse = new JAXBElement<>(qName, UserClientResponse.class, response);
        return jaxbElementResponse;
    }

    // ENDPOINT LOGIN CICLISTA
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CiclistaLoginRequest")
    @ResponsePayload
    public JAXBElement<UserClientResponse>  login(@RequestPayload  JAXBElement<CiclistaLoginRequest> request) {
        System.out.println(" == ENTRANDO NO SERVIÇO LOGIN CICLISTA");
        CiclistaLoginRequest dataLogin = request.getValue();
        UserClientResponse response = ciclistaService.login(dataLogin);
        QName qName = new QName(NAMESPACE_URI, "UserClientResponse");
        JAXBElement<UserClientResponse> jaxbElementResponse = new JAXBElement<>(qName, UserClientResponse.class, response);
        return jaxbElementResponse;
    }


}

