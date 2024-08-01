package com.example.bikeshared.services.user.adm;

import javax.xml.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.bikeshared.database.user.gestor.GestorModel;

import jakarta.xml.bind.JAXBElement;
import xml.soap.user.CiclistaCreat;
import xml.soap.user.GestorCreat;
import xml.soap.user.GestorLoginRequest;
import xml.soap.user.LoginRequest;
import xml.soap.user.UserClientResponse;
import xml.soap.user.UserGestorResponse;

@Endpoint
@Component
public class GestorEndPoint {
    
    private static final String NAMESPACE_URI = "http://user.soap.xml";
    
    @Autowired
    private GestorService gestorService;

	/**
	 *
	 * @param {@link CiclistaCreat}
	 * @return {@link UserGestorResponse}
	 */

     @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GestorCreat")
     @ResponsePayload
     public JAXBElement<UserGestorResponse>  addUser(@RequestPayload  JAXBElement<GestorCreat> request) {
         System.out.println(" == ENTRANDO NO SERVIÇO CRIAR GESTOR");
         GestorCreat gestorCreat = request.getValue();
         UserGestorResponse response = gestorService.addGestor(gestorCreat);
         QName qName = new QName(NAMESPACE_URI, "UserGestorResponse");
         JAXBElement<UserGestorResponse> jaxbElementResponse = new JAXBElement<>(qName, UserGestorResponse.class, response);
         return jaxbElementResponse;
     }


    // ENDPOINT LOGIN ADMINISTRADOR
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GestorLoginRequest")
    @ResponsePayload
    public JAXBElement<UserGestorResponse>  login(@RequestPayload  JAXBElement<GestorLoginRequest> request) {
        System.out.println(" == ENTRANDO NO SERVIÇO LOGIN CICLISTA");
        GestorLoginRequest dataLogin = request.getValue();
        UserGestorResponse response = gestorService.login(dataLogin);
        QName qName = new QName(NAMESPACE_URI, "UserClientResponse");
        JAXBElement<UserGestorResponse> jaxbElementResponse = new JAXBElement<>(qName, UserGestorResponse.class, response);
        return jaxbElementResponse;
    }
}

