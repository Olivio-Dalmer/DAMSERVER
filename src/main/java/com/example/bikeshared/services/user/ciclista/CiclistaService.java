package com.example.bikeshared.services.user.ciclista;

import org.springframework.stereotype.Service;

import com.example.bikeshared.database.user.UserModel;
import com.example.bikeshared.database.user.UserRepository;
import com.example.bikeshared.database.user.client.ClientModel;
import com.example.bikeshared.database.user.client.ClientRepository;
import com.example.bikeshared.database.user.session.SessionRepository;
import com.example.bikeshared.services.user.UserService;
import com.example.bikeshared.services.user.session.SessionService;
import com.example.bikeshared.services.user.token.TokenService;

import xml.soap.user.CiclistaCreat;
import xml.soap.user.CiclistaLoginRequest;
import xml.soap.user.LoginRequest;
import xml.soap.user.User;
import xml.soap.user.UserClientResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CiclistaService {

    @Autowired(required = true)
    private UserRepository userRepo;
    @Autowired(required = true)
    private SessionService sessionService;
    @Autowired(required = true)
    private SessionRepository sessionRepo;
    @Autowired(required = true)
    private TokenService tokenService;
    @Autowired(required = true)
    private ClientRepository clientRepo;
    @Autowired(required = true)
    private UserService userService;

    // SERVIÇO ADICONAR NOVO CICLISTA
    public UserClientResponse addCiclista(CiclistaCreat request) {
        UserClientResponse response = new UserClientResponse();
        ClientModel ciclista = new ClientModel();
        User userBD = new User();
        request.getUserCreat().setTipo(1);
        try {
            userBD = userService.addUser(request.getUserCreat());
            if (userBD != null) {
                System.out.println(userBD.getId());
                ciclista.setIdUser(userBD.getId());
                ciclista.setSaldo(10);
                ClientModel ciclistaBD = clientRepo.save(ciclista);
                response.setDadosUser(userBD);
                response.setIsDevedor(false);
                response.setIdUser(ciclistaBD.getIdUser());
                response.setErro(false);
                response.setSaldo(ciclistaBD.getSaldo());
                response.setMensagem("Usuário Ciclista adicionado com sucesso!!!");
            } else {
                response.setErro(true);
                response.setMensagem("Erro ao adicionar o usuário.");
            }   
        } catch (Exception e) {
            response.setErro(true);
            response.setMensagem("Erro ao adicionar o usuário." +  e.getMessage());
            System.out.println(e);
        }        
        return response;
    }


    // SERVIÇO LOGIN CICLISTA
    public UserClientResponse login(CiclistaLoginRequest request) {
        UserClientResponse response = new UserClientResponse();
            try {
                System.out.println("FINIT LOGIN" + request.getEmail());                
                UserModel userModel = userRepo.findByEmail(request.getEmail());
                System.out.println("FINIT LOGIN " + request.getEmail());                
                if (userModel == null) {
                    System.out.println("USER NÃO ENCONTRADO");
                    response.setErro(true);
                    response.setMensagem("Falha! Usuário não registrado!!");
                    return response; 
                }
                System.out.println("FINIT LOGIN");
                if (userModel.getTipo() != 1) {
                    response.setErro(true);
                    response.setMensagem("Falha! Não é um ciclista!!");
                    return response; 
                }
                System.out.println("FAZENDO LOGIN");
                LoginRequest dataLogin = new LoginRequest();
                BeanUtils.copyProperties(request, dataLogin);
                User user = userService.login(dataLogin);
                ClientModel ciclistaBD = clientRepo.findByIdUser(user.getId());
                System.out.println("ADICONOU NO LOGIN");
                    if (user!=null) {
                        System.out.println("ADICONOU ");
                        response.setErro(false);
                        response.setDadosUser(user);
                        response.setIdUser(ciclistaBD.getIdUser());
                        response.setSaldo(ciclistaBD.getSaldo());
                        response.setId(ciclistaBD.getId());
                        response.setMensagem("Login efectuado com sucesso!!");
                    }

            } catch (Exception e) {
                // TODO: handle exception
                response.setErro(true);
                response.setMensagem("Falha ao Efectuar Login! " + e.getMessage());
            }
        return response;
    }
    
}
