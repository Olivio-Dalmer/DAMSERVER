package com.example.bikeshared.services.servers;

import org.springframework.stereotype.Component;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml.soap.station.AddDocaRequest;
import xml.soap.station.AddDocaResponse;
import xml.soap.station.AlterStateDockBikeRequest;
import xml.soap.station.AlterStateDockBikeResponse;
import xml.soap.station.DeleteDockRequest;
import xml.soap.station.DeleteDockResponse;
import xml.soap.station.GetAllDocaRequest;
import xml.soap.station.GetAllDocaResponse;
import xml.soap.station.GetStationDetailsRequest;
import xml.soap.station.GetStationDetailsResponse;
import xml.soap.station.GetStationRequest;
import xml.soap.station.GetStationResponse;

@Component
public class EstacaoWS extends WebServiceGatewaySupport {

    // private static final Logger log = LoggerFactory.getLogger(StationClient.class);



    // PEGAR DETALHES DE UMA ESTAÇÃO
    public GetStationDetailsResponse getStationDetails (String url) {
        GetStationDetailsRequest request = new GetStationDetailsRequest();
        System.out.println("Data of station: ");
        System.out.println("URI " + url);
        System.out.println("REQUEST " + request);
    
        GetStationDetailsResponse response = (GetStationDetailsResponse) getWebServiceTemplate()
            .marshalSendAndReceive(
                url,
                request,
                new SoapActionCallback("http://soap.xml/GetStationDetailsResponse")
            );
        System.out.println("RESPONSE " + response);
        return response;
    }
    
    // ADICONAR NOVA DOCA
    public AddDocaResponse addDoca (String url) {
        AddDocaRequest request = new AddDocaRequest();
        System.out.println("Data of station: ");
        System.out.println("URI " + url);
        System.out.println("REQUEST " + request);
        AddDocaResponse response  = (AddDocaResponse) getWebServiceTemplate()
            .marshalSendAndReceive(
                url,
                request,
                new SoapActionCallback("http://soap.xml/AddDocaResponse")
            );
        return response;
    }


    // PEGAR TODAS AS DOCAS DE UMA ESTAÇÃO
    public GetAllDocaResponse getAllDoca (String url) {
        GetAllDocaRequest request = new GetAllDocaRequest();
        System.out.println("Data of station: ");
        System.out.println("REQUEST " + request);
        System.out.println("URI " + url);
        GetAllDocaResponse response = (GetAllDocaResponse) getWebServiceTemplate()
            .marshalSendAndReceive(
                url,
                request,
                new SoapActionCallback("http://soap.xml/GetAllDocaResponse")
            );
        System.out.println("RESPONSE " + response);
        return response;
    }


    // PEGAR TODAS AS ESTAÇÕES
    public GetStationResponse getStationState (String url) {
        GetStationRequest request = new GetStationRequest();
        System.out.println("Data of station: ");
        System.out.println("REQUEST " + request);
        System.out.println("URI " + url);
    
        GetStationResponse response = (GetStationResponse) getWebServiceTemplate()
            .marshalSendAndReceive(
                url,
                request,
                new SoapActionCallback("http://soap.xml/GetStationRequest")
            );
        System.out.println("RESPONSE " + response);
        return response;
    }




    public AlterStateDockBikeResponse updateDockStateInUpBike (String url, Long dock, int state) {
        AlterStateDockBikeRequest request = new AlterStateDockBikeRequest();
        request.setIdDock(dock);
        request.setState(state);
        AlterStateDockBikeResponse response = (AlterStateDockBikeResponse) getWebServiceTemplate()
            .marshalSendAndReceive(
                url,
                request,
                new SoapActionCallback("http://soap.xml/AlterStateDockInUpBikeRequest")
            );
        return response;
    }

    public AlterStateDockBikeResponse updateDockStateInDownBike (String url, Long dock, int state, String info) {
        AlterStateDockBikeRequest request = new AlterStateDockBikeRequest();
        request.setIdDock(dock);
        request.setState(state);
        AlterStateDockBikeResponse response = (AlterStateDockBikeResponse) getWebServiceTemplate()
            .marshalSendAndReceive(
                url,
                request,
                new SoapActionCallback("http://soap.xml/AlterStateDockInDownBikeRequest")
            );
        return response;
    }



    public DeleteDockResponse deleteDock (String url, String reference) {
        DeleteDockRequest request = new DeleteDockRequest();
        request.setReference(reference);
        DeleteDockResponse response  = (DeleteDockResponse) getWebServiceTemplate()
            .marshalSendAndReceive(url,
            request,
            new SoapActionCallback("http://soap.xml/DeleteDockRequest")
        );
        return response;
    }

}
