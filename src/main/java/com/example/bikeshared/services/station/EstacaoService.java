package com.example.bikeshared.services.station;

import org.springframework.stereotype.Service;
import com.example.bikeshared.database.station.EstacaoModel;
import com.example.bikeshared.database.station.EstacaoRepository;
import com.example.bikeshared.services.servers.EstacaoWS;
import com.example.bikeshared.services.station.doca.DocaService;
import xml.soap.station.AddDockRequest;
import xml.soap.station.AddDockResponse;
import xml.soap.station.AllEstacoesRequest;
import xml.soap.station.AllEstacoesResponse;
import xml.soap.station.DockType;
import xml.soap.station.EstacaoModelXSD;
import xml.soap.station.GetStationDetailsRequest;
import xml.soap.station.GetStationDetailsResponse;
import xml.soap.station.GetStationResponse;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EstacaoService {

    @Autowired(required = true)
    private EstacaoRepository estacaoRepo;
    @Autowired(required = true)
    private DocaService docaService;
    @Autowired(required = true)
    private EstacaoWS estacaoClient;



    // DETALHES DE UMA ESTAÇÃO
    public GetStationDetailsResponse getStationDetails (GetStationDetailsRequest request) {
        System.out.println("DENTRO DO SERVIÇO PEGAR DETALHES DA ESTAÇÃO: " + request.getBody().getId());
        GetStationDetailsResponse response = new GetStationDetailsResponse();
            EstacaoModel stationModel = estacaoRepo.findById(request.getBody().getId());
            if(stationModel != null) {
                GetStationDetailsResponse gsr = estacaoClient.getStationDetails(stationModel.getUrl());
                if (gsr != null) {
                    EstacaoModelXSD dadosEstacao = new EstacaoModelXSD();
                    dadosEstacao.setId(gsr.getDadosEstacao().getId());
                    dadosEstacao.setNome(gsr.getDadosEstacao().getNome());
                    dadosEstacao.setActiveState(gsr.getDadosEstacao().isActiveState());

                    System.out.println("GSR 2" + gsr.getDadosEstacao().isActiveState());
                    dadosEstacao.setPremio(gsr.getDadosEstacao().getPremio());
                    dadosEstacao.setLatitude(gsr.getDadosEstacao().getLatitude());
                    dadosEstacao.setLongitude(gsr.getDadosEstacao().getLongitude());
                    dadosEstacao.setQtdDocasTotais(gsr.getDadosEstacao().getQtdDocasTotais());
                    dadosEstacao.setQtdDocasLivres(gsr.getDadosEstacao().getQtdDocasLivres());
                    dadosEstacao.setDateCreated(gsr.getDadosEstacao().getDateCreated());
                    dadosEstacao.setDateUpdated(gsr.getDadosEstacao().getDateUpdated());
                    response.setDadosEstacao(dadosEstacao);
                    response.setEstado(true);
                    System.err.println("DADOS ESTAÇÃO" + dadosEstacao.getNome()
                    );
                    response.setMensagem("Estação encontrada com sucesso!!!");
                    return response;
                }
                response.setEstado(false);
                response.setMensagem("Dados da estação corrompido!!!");
                return response;
            }
            else {
                response.setEstado(false);
                response.setMensagem("Estação não foi encontrada");
            }
        return response;
    }



    // SERVIÇO PEGAR TODAS AS ESTAÇÕES REGISTRADAS
    public AllEstacoesResponse allEstacao (AllEstacoesRequest request) {
        AllEstacoesResponse response = new AllEstacoesResponse();
        try {
            //
            List<EstacaoModel> listEstacoesBD = estacaoRepo.findAll();
            if (listEstacoesBD == null) {
                response.setErro(false);
                response.setMensagem("Sucesso ao pegar todas as estações!! ");
                return response;
            }
            // listEstacoesBD.sort(Comparator.comparingDouble(coord -> CalculateDistance.calcularDistanciaHaversine(request.getLatitude(), request.getLongitude(), coord.getLatitude(), coord.getLongitude())));
            for (EstacaoModel s : listEstacoesBD) {
                EstacaoModelXSD estacao = new EstacaoModelXSD();
                BeanUtils.copyProperties(s, estacao);
                response.getTodasEstacoes().add(estacao);
            }
            response.setErro(false);
            response.setMensagem("Sucesso as carregar as estações!!");
        } catch (Exception e) {
            response.setErro(true);
            response.setMensagem("Falha ao pesquisar as estações!! " + e.getMessage());
            System.out.println("EXC: " + e.getMessage());
        }
        return response;
    }

    // 
public GetStationDetailsResponse getStation (GetStationDetailsRequest request) {

        System.out.println("Dentro do Serviço e ID da estação: " + request.getBody().getId());
        GetStationDetailsResponse response = new GetStationDetailsResponse();

            EstacaoModel stationModel = estacaoRepo.findById(request.getBody().getId());
            if(stationModel != null) {
                GetStationResponse gsr = estacaoClient.getStationState(stationModel.getUrl());
                if (gsr != null) {
                    int qtdDocks = gsr.getDockItem().size();
                    int qtdDocksDispo = 0;
                    // for (xml.soap.DockType dock : gsr.getDockItem()) {
                    //     if (dock.getState()==0) qtdDocksDispo++;
                    // }
                    estacaoRepo.save(stationModel);
                    BeanUtils.copyProperties(gsr, response);
                    response.setEstado(true);
                    response.setMensagem("Estação encontrada com sucesso!!!");

                    gsr.getDockItem().forEach( dock -> {
                        DockType d = new DockType();
                        d.setState(dock.getState());
                        d.setIdDock(dock.getIdDock());
                        d.setReference(dock.getReference());
                    
                    });
                    return response;
                }
                response.setEstado(false);
                response.setMensagem("Station data corrupted!!!");
                return response;
            }
            else {
                response.setEstado(false);
                response.setMensagem("Estação não foi encontrada");
            }
        return response;
    }




    

}