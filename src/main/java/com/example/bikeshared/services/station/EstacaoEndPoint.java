package com.example.bikeshared.services.station;

import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.bikeshared.services.station.doca.DocaService;

import jakarta.xml.bind.JAXBElement;
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
import xml.soap.station.GetStationDetailsRequest;
import xml.soap.station.GetStationDetailsResponse;

@Endpoint
@Component
public class EstacaoEndPoint {
	//"http://user.soap.xml"
	private static final String NAMESPACE_URI = "http://station.soap.xml";
	
	@Autowired
	private EstacaoService estacaoService;
	@Autowired
	private DocaService docasService;

	/**
	 *
	 * @param {@link EstacaoCreatResponse}
	 * @return {@link EstacaoCreat}
	 */
	//
	//


	 	// ENDPOINT PEGAR ESTAÇÕES [LAT, LONG]
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "AllEstacoesRequest")
	@ResponsePayload
	public JAXBElement<AllEstacoesResponse>  allEstacoes (@RequestPayload JAXBElement<AllEstacoesRequest> request) {
		AllEstacoesRequest request2 = request.getValue();
		System.out.println(" == Entrando no serviço pegar estações.");
		AllEstacoesResponse response = estacaoService.allEstacao(request2);
		QName qName = new QName(NAMESPACE_URI, "AllDocasResponse");
		JAXBElement<AllEstacoesResponse> jaxbElementResponse = new JAXBElement<>(qName, AllEstacoesResponse.class, response);
		return jaxbElementResponse;
	}
/*
	 	// ENDPOINT CRIAR DOCA 
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "DocaCreat")
	@ResponsePayload
	public JAXBElement<AllDocasResponse>  addDoca (@RequestPayload JAXBElement<DocaCreat> request) {
		DocaCreat docaCreat = request.getValue();
		System.out.println(" == Entrando no serviço criar Doca " + docaCreat.getQtd());
		System.out.println(" == Entrando no serviço criar Doca " + docaCreat.getIdEstacao());
		AllDocasResponse response = docasService.addDoca(docaCreat);
		QName qName = new QName(NAMESPACE_URI, "AllDocasResponse");
		JAXBElement<AllDocasResponse> jaxbElementResponse = new JAXBElement<>(qName, AllDocasResponse.class, response);
		return jaxbElementResponse;
	}
*/

	// ENDPOINT PEGAR DADOS DA ESTAÇÃO
	@PayloadRoot(namespace= NAMESPACE_URI, localPart = "GetStationDetailsRequest")
	@ResponsePayload
	public JAXBElement<GetStationDetailsResponse>  getEstacao (@RequestPayload JAXBElement<GetStationDetailsRequest> request) {
		GetStationDetailsRequest request2 = request.getValue();
		System.out.println(" == Entrando no serviço pegar dados da estacao ");
		GetStationDetailsResponse response = estacaoService.getStationDetails(request2);
		QName qName = new QName(NAMESPACE_URI, "GetStationDetailsResponse");
		JAXBElement<GetStationDetailsResponse> jaxbElementResponse = new JAXBElement<>(qName, GetStationDetailsResponse.class, response);
		return jaxbElementResponse;
	}





}
