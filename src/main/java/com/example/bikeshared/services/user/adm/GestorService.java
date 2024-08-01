package com.example.bikeshared.services.user.adm;

import org.springframework.stereotype.Service;

import com.example.bikeshared.database.user.UserModel;
import com.example.bikeshared.database.user.UserRepository;
import com.example.bikeshared.database.user.gestor.GestorModel;
import com.example.bikeshared.database.user.gestor.GestorRepository;
import com.example.bikeshared.services.user.UserService;
import xml.soap.user.GestorCreat;
import xml.soap.user.GestorLoginRequest;
import xml.soap.user.LoginRequest;
import xml.soap.user.User;
import xml.soap.user.UserGestorResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GestorService {

    @Autowired(required = true)
    private UserRepository userRepo;
    @Autowired(required = true)
    private GestorRepository gestorRepo;
    @Autowired(required = true)
    private UserService userService;

    public UserGestorResponse addGestor(GestorCreat request) {
        UserGestorResponse response = new UserGestorResponse();
        GestorModel gestor = new GestorModel();
        User userBD = new User();
        request.getUserCreat().setTipo(2);
        try {
            userBD = userService.addUser(request.getUserCreat());
            if (userBD != null) {
                System.out.println(userBD.getId());
                gestor.setIdUser(userBD.getId());
                gestor.setIdEstacao(request.getIdEstacao());
                GestorModel gestorBD = gestorRepo.save(gestor);
                response.setDadosUser(userBD);
                response.setId(gestorBD.getId());
                response.setIdEstacao(gestorBD.getIdEstacao());
                response.setIdUser(gestorBD.getIdUser());
                response.setErro(false);
                response.setMensagem("Usuário Administrativo adicionado com sucesso!!!");
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

    // SERVIÇO LOGIN DO ADMINISTRADOR
    public UserGestorResponse login(GestorLoginRequest request) {
        UserGestorResponse response = new UserGestorResponse();
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
                if (userModel.getTipo() != 2 && userModel.getTipo() != 3) {
                    response.setErro(true);
                    response.setMensagem("Falha! Não é um administrador!!");
                    return response; 
                }
                LoginRequest dataLogin = new LoginRequest();
                BeanUtils.copyProperties(request, dataLogin);
                User user = userService.login(dataLogin);
                GestorModel gestorBD = gestorRepo.findByIdUser(user.getId());
                System.out.println("ADICONOU NO LOGIN");
                    if (user!=null) {
                        System.out.println("ADICONOU ");
                        response.setErro(false);
                        response.setDadosUser(user);
                        response.setIdUser(gestorBD.getIdUser());
                        response.setIdEstacao(gestorBD.getIdEstacao());
                        response.setId(gestorBD.getId());
                        response.setMensagem("Login efectuado com sucesso!!");
                    }

            } catch (Exception e) {
                response.setErro(true);
                response.setMensagem("Falha ao Efectuar Login! " + e.getMessage());
            }
        return response;
    }

}
