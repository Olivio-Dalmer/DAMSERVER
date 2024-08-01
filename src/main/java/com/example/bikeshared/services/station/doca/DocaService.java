package com.example.bikeshared.services.station.doca;

import org.springframework.stereotype.Service;

import com.example.bikeshared.database.station.EstacaoModel;
import com.example.bikeshared.database.station.EstacaoRepository;
import com.example.bikeshared.database.station.doca.DocaModel;
import com.example.bikeshared.database.station.doca.DocaRepository;
import com.example.bikeshared.services.servers.EstacaoWS;

import xml.soap.station.AddDocaRequest;
import xml.soap.station.AddDocaResponse;
import xml.soap.station.AllDocasResponse;
import xml.soap.station.Doca;
import xml.soap.station.DocaCreat;
import xml.soap.station.DocaXSD;
import xml.soap.station.EstacaoModelXSD;
import xml.soap.station.GetAllDocaRequest;
import xml.soap.station.GetAllDocaResponse;
import xml.soap.station.GetStationDetailsRequest;
import xml.soap.station.GetStationDetailsResponse;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DocaService {

    @Autowired(required = true)
    private DocaRepository docaRepo;
    @Autowired(required = true)
    private EstacaoRepository estacaoRepo;
    @Autowired(required = true)
    private EstacaoWS estacaoClient;

    public AllDocasResponse addDoca (DocaCreat request){
        AllDocasResponse response = new AllDocasResponse();
        long qtdDocasExistent = docaRepo.countByIdEstacao(request.getIdEstacao());
        qtdDocasExistent=qtdDocasExistent;
        System.out.println("DOCAS EXISTENTES: " + qtdDocasExistent);
        long qtdTotal = qtdDocasExistent + request.getQtd();
        System.out.println("DOCAS TOTAL: " + qtdTotal);
        LocalDateTime dataCreated = LocalDateTime.now();
        for (long i = qtdDocasExistent; i < qtdTotal; i++) {
            long n = i + 1;
            DocaModel docaModel = new DocaModel();
            docaModel.setIdEstacao(request.getIdEstacao());
            docaModel.setAberta(true);
            docaModel.setReference("DCE0" + request.getIdEstacao() + "N0"+ qtdDocasExistent);
            docaModel.setDataCreated(dataCreated);
            docaModel.setDataUpdated(dataCreated);
            DocaModel docaBD = docaRepo.save(docaModel);
            Doca doca = new Doca();
            BeanUtils.copyProperties(docaBD, doca);
            response.getDocas().add(doca);
            System.out.println(i);
        }
        return response;
    }


    // SERVIÇO ADICIONAR DOCA NUMA ESTAÇÃO
    public AddDocaResponse addDoca (AddDocaRequest request) {
        System.out.println("DENTRO DO SERVIÇO ADD DOCA DA ESTAÇÃO: " + request.getIdEstacao());
        AddDocaResponse response = new AddDocaResponse();
            EstacaoModel stationModel = estacaoRepo.findById(request.getIdEstacao());
            if (stationModel == null) {
                response.setMensagem("Falha! ID da estação não está associado ao BIKE SHARED!");
                response.setErro(true);
                return response;
            }
            System.out.println("- GSR");
            AddDocaResponse gsr = estacaoClient.addDoca(stationModel.getUrl());

            System.out.println("GSR 1");
            if (gsr.getDadosDoca() != null) {
                System.out.println("GSR 2");
                DocaXSD novaDoca = new DocaXSD();
                novaDoca.setId(gsr.getDadosDoca().getId());
                novaDoca.setReference(gsr.getDadosDoca().getReference());
                novaDoca.setAberta(gsr.getDadosDoca().isAberta());
                novaDoca.setDateCreated(gsr.getDadosDoca().getDateCreated());
                novaDoca.setDateUpdated(gsr.getDadosDoca().getDateUpdated());
                stationModel.setDataUpdated(LocalDateTime.now());
                estacaoRepo.save(stationModel);
                response.setDadosDoca(novaDoca);
                response.setErro(false);
                response.setMensagem("Nova doca adiconada com sucesso!");
                return response;
            } else {
                response.setErro(true);
                response.setMensagem("Dados da estação corrompido!!!");
                return response;
            }
         
    }

    // SERVIÇO PEGAR TODAS DOCAS DE UM ESTAÇÃO
    public GetAllDocaResponse getAllDoca (GetAllDocaRequest request) {
        System.out.println("DENTRO DO SERVIÇO PEGAR TODAS DOCAS: " + request.getIdEstacao());
        GetAllDocaResponse response = new GetAllDocaResponse();
            EstacaoModel stationModel = estacaoRepo.findById(request.getIdEstacao());
            if (stationModel == null) {
                response.setMensagem("Falha! ID da estação não está associado ao BIKE SHARED!");
                response.setErro(true);
                return response;
            }
            System.out.println("- GSR");
            GetAllDocaResponse gsr = estacaoClient.getAllDoca(stationModel.getUrl());
            System.out.println("GSR 1");
            if (gsr != null) {
                System.out.println("GSR 2");
                response.setTodasDocas(gsr.getTodasDocas());
                response.setErro(false);
                response.setMensagem("Nova doca adiconada com sucesso!");
                return response;
            } else {
                response.setErro(true);
                response.setMensagem("Dados da estação corrompido!!!");
                return response;
            }
         
    }




}