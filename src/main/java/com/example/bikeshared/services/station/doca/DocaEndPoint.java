package com.example.bikeshared.services.station.doca;

import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.bikeshared.services.servers.EstacaoWS;
import com.example.bikeshared.services.station.EstacaoService;
import com.example.bikeshared.services.station.doca.DocaService;

import jakarta.xml.bind.JAXBElement;
import xml.soap.station.AddDocaRequest;
import xml.soap.station.AddDocaResponse;
import xml.soap.station.AddDockRequest;
import xml.soap.station.AddDockResponse;
import xml.soap.station.AddInfoStationRequest;
import xml.soap.station.AddInfoStationResponse;
import xml.soap.station.AllDocasResponse;
import xml.soap.station.AllEstacoesRequest;
import xml.soap.station.AllEstacoesResponse;
import xml.soap.station.DocaCreat;
import xml.soap.station.EstacaoCreat;
import xml.soap.station.EstacaoCreatResponse;
import xml.soap.station.GetAllDocaRequest;
import xml.soap.station.GetAllDocaResponse;
import xml.soap.station.GetStationDetailsRequest;
import xml.soap.station.GetStationDetailsResponse;

@Endpoint
@Component
public class DocaEndPoint {
	//"http://user.soap.xml"
	private static final String NAMESPACE_URI = "http://station.soap.xml";
	
	@Autowired
	private EstacaoService estacaoService;
	@Autowired
	private DocaService docasService;

    //
 	// ENDPOINT ADICIONAR DOCA
	 @PayloadRoot(namespace= NAMESPACE_URI, localPart = "AddDocaRequest")
	 @ResponsePayload
	 public JAXBElement<AddDocaResponse>  addDocaServer (@RequestPayload JAXBElement<AddDocaRequest> request) {
		 AddDocaRequest request2 = request.getValue();
		 System.out.println(" == Entrando no serviço adiconar doca " + request2.getIdEstacao());
		 AddDocaResponse response = docasService.addDoca(request2);
		 QName qName = new QName(NAMESPACE_URI, "AllDocasResponse");
		 JAXBElement<AddDocaResponse> jaxbElementResponse = new JAXBElement<>(qName, AddDocaResponse.class, response);
		 return jaxbElementResponse;
	 }

 	// ENDPOINT PEGAR TODAS AS DOCA DE UMA ESTAÇÕES
	 @PayloadRoot(namespace= NAMESPACE_URI, localPart = "GetAllDocaRequest")
	 @ResponsePayload
	 public JAXBElement<GetAllDocaResponse>  getAllDoca (@RequestPayload JAXBElement<GetAllDocaRequest> request) {
		 GetAllDocaRequest request2 = request.getValue();
		 System.out.println(" == Entrando no serviço pegar todas estações " + request2.getIdEstacao());
		 GetAllDocaResponse response = docasService.getAllDoca(request2);
		 QName qName = new QName(NAMESPACE_URI, "AllDocasResponse");
		 JAXBElement<GetAllDocaResponse> jaxbElementResponse = new JAXBElement<>(qName, GetAllDocaResponse.class, response);
		 return jaxbElementResponse;
	 }
   
}
