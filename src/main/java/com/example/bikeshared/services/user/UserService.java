package com.example.bikeshared.services.user;
import org.springframework.stereotype.Service;

import com.example.bikeshared.database.user.UserModel;
import com.example.bikeshared.database.user.UserRepository;
import com.example.bikeshared.database.user.session.SessionModel;
import com.example.bikeshared.database.user.session.SessionModelRequest;
import com.example.bikeshared.database.user.session.SessionRepository;
import com.example.bikeshared.helpers.HashPassword;
import com.example.bikeshared.services.user.session.SessionService;
import com.example.bikeshared.services.user.token.TokenService;

import jakarta.jws.soap.SOAPBinding.Use;
import xml.soap.user.EmailUserRequest;
import xml.soap.user.LoginRequest;
import xml.soap.user.LogoutRequest;
import xml.soap.user.User;
import xml.soap.user.UserCreat;
import xml.soap.user.UserResponse;
import xml.soap.user.UserToListType;
import xml.soap.user.UserUpdatePasswordRequest;
import xml.soap.user.UserUpdateRequest;
import xml.soap.user.UsersListResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService {

    @Autowired(required = true)
    private UserRepository userRepo;
    @Autowired(required = true)
    private SessionService sessionService;
    @Autowired(required = true)
    private SessionRepository sessionRepo;
    @Autowired(required = true)
    private TokenService tokenService;



    public User addUser (UserCreat request) {
        User response = new User();
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(request, userModel);
        userModel.setPassword(HashPassword.hashing(request.getPassword()));
        userModel.setTipo(request.getTipo());
        userModel.setDataCreated(LocalDateTime.now());
        userModel.setDataUpdated(userModel.getDataCreated());
        UserModel userModel2 = userRepo.save(userModel);
        BeanUtils.copyProperties(userModel2, response);
        return response;
    }

    // SERVIÇO USER PARA ACTUALIZAÇÃO DO USER
    public UserResponse upadteUser (UserUpdateRequest request) {
        System.out.println("ACTUALIZANDO OS DADOS DO USUÁRIO");
        UserResponse response = new UserResponse();
        UserModel userBD = userRepo.findById(request.getId()); // GET DO USER QUE QUEREMOS ACTUALIZAR
        //HashPassword.hashing(request.getPassword())
        if (userBD!=null) {
            BeanUtils.copyProperties(request, userBD); // ACTUALIZAMOS OS VALORES DO USER DA BD COM O NOVOS VALORES
            userBD.setDataUpdated(LocalDateTime.now());
            UserModel userSave = userRepo.save(userBD); // SALVAMOS O USER ACTUALIZADO NA BD
            BeanUtils.copyProperties(userSave, response); // COPIAMOS O USER SALVO PRA A RESPONSE
            response.setMensagem("Usuário actualizado com sucesso!!!");
            return response;                
        }
        response.setErro(true);
        response.setMensagem("Usuário não encontrado!!!");
        return response;
    }

    // SERVIÇO USER PARA ACTUALIZAÇÃO DA PASSWORD DO USER
    public UserResponse upadtePasswordUser (UserUpdatePasswordRequest request) {
        System.out.println("ACTUALIZANDO A PASSWORD DO USUÁRIO");
        UserResponse response = new UserResponse();
        UserModel userBD = userRepo.findById(request.getId()); // GET DO USER QUE QUEREMOS ACTUALIZAR
        if (userBD!=null) {
            BeanUtils.copyProperties(request, userBD); // ACTUALIZAMOS OS VALORES DO USER DA BD COM O NOVOS VALORES
            userBD.setDataUpdated(LocalDateTime.now());
            UserModel userSave = userRepo.save(userBD); // SALVAMOS O USER ACTUALIZADO NA BD
            BeanUtils.copyProperties(userSave, response); // COPIAMOS O USER SALVO PRA A RESPONSE
            response.setMensagem("Passord do usuário actualizada com sucesso!!!");
            return response;                
        }
        response.setErro(true);
        response.setMensagem("Usuário não encontrado!!!");
        return response;
    }

    // SERVIÇO USER PARA APAGAR UM USER
    public UserResponse deleteUser (EmailUserRequest email) {
        System.out.println("ELIMINANDO UM USER");
        UserResponse response = new UserResponse();
        UserModel userBD = userRepo.findByEmail(email.getEmail()); // GET DO USER QUE QUEREMOS ACTUALIZAR
        if(userBD!=null){
            response.setErro(false);
            response.setMensagem("Usuário apagado com sucesso!!!");    
            userRepo.delete(userBD);
            return response;
        }
        response.setErro(true);
        response.setMensagem("Usuário não encontrado!!!");
        return response;     
    }


    // SERVIÇO QUE PEGA TODOS OS USUÁRIOS DA BD
    public UsersListResponse getAllUsers () {
        System.out.println("PEGANDO TODOS OS USUÁRIOS");
        UsersListResponse response = new UsersListResponse();
        List<UserModel> usersBD = userRepo.findAll();
        if(usersBD!= null){
            response.setEstado(true);
            response.setMensagem("Sucesso ao pegar todos usuários");
            for (UserModel user : usersBD) {
                UserToListType userToListType = new UserToListType();
                BeanUtils.copyProperties(user, userToListType);
                response.getUsers().add(userToListType);
            }
        }
        return response;
    }

    // SERVIÇO LOGIN DO USUÁRIO
    public User login (LoginRequest request){
        int time = 1;
        User response = new User();
        UserModel userBD = userRepo.findByEmail(request.getEmail());
        if (userBD!=null) {
            if (userBD.getPassword().equals(HashPassword.hashing(request.getPassword()))) {   
                /*
                if (sessionService.loggedIn(userBD.getId())) {
                    throw new RuntimeException("Já possui uma sessão iniciada.");
                }*/
                try {
                    System.out.println("GERANDO TOKEN ");
                    String token = tokenService.generateToken(request.getEmail(), time);
                    System.out.println("TOKEN GERADO " + token);
                    System.out.println("USER ID "+ userBD.getId());
                    SessionModelRequest session = new SessionModelRequest(token, userBD.getId());
                    sessionService.initSession(session);
                    BeanUtils.copyProperties(userBD, response);
                    response.setToken(token);
                    return response;
                } catch (Exception e) {
                    System.out.println("EXC: AO EFECTUAR LOGIN DO USER " + e.getMessage());
                    return response;
                }         
                }    
        }
        throw new RuntimeException("Credenciais inválidas.");
    }

  

    // SERVIÇO LOGOUT DO USUÁRIO
    public UserResponse logout (LogoutRequest request) {
        UserResponse response = new UserResponse();
        SessionModel session = sessionRepo.findByToken(request.getHeader().getToken());
        if (session!=null) {
            sessionRepo.delete(session);
            response.setErro(true);
            response.setMensagem("Sessão terminada com sucesso");
        }
        return response;
    }







    /*
     
        public UserResponse login(LoginRequest request) {
        System.out.println("Dentro do Serviço startSession"+request.getEmail());
        UserResponse response = new UserResponse();

        UserModel userModel = userRepo.findByEmail(request.getEmail());

        if (userModel != null) {
            if (userModel.getPassword().equals(HashPassword.hashing(request.getPassword()))) {
                SessionModel session = new SessionModel();
                session.setToken(jwtToken.generateToken(userModel.getEmail(), 1));
                sessionRepo.save(session);
                response.setToken(session.getToken());
                response.setEstado(true);
                response.setMensagem("Sessao iniciada com sucesso!");
                BeanUtils.copyProperties(userModel, response);
            } else {
                response.setEstado(false);
                response.setMensagem("email ou password errados!");
            }
            return response;
        }

        response.setEstado(false);
        response.setMensagem("email ou password errados!");
        return response;
    }
     */


/*
 public UserResponse logout(LogoutRequest request) {
        UserResponse response = new UserResponse();
        System.out.println("Dentro do Serviço startSession"+request.getAuthToken());
        SessionModel sessionModel = sessionRepo.findByToken(request.getAuthToken());

        if (sessionModel == null){
            response.setEstado(false);
            response.setMensagem("Sessão já foi terminada anteriormente!");
        } else {
            sessionRepo.deleteById(sessionModel.getId());
            response.setEstado(true);
            response.setMensagem("Sessão terminada com sucesso!");
        }

        return response;
    }

 */

    /*     
    public UserResponse getUser (UserIdRequest request) {

        UserResponse response = new UserResponse();
        SessionModel session = sessionRepo.findByToken(request.getHeader().getAuthToken());

        if ( session == null ){
            response.setEstado(false);
            response.setMensagem("Token inválido, undefined!");
        }
        else if (jwtToken.getSubject(request.getHeader().getAuthToken()).equals(" ")) {
            response.setEstado(false);
            response.setMensagem("Sessão expirada!");
            sessionRepo.deleteById(session.getId());
        }
        else {
            Optional<UserModel> userOp = userRepo.findById(request.getBody().getUserId());
            if (userOp.isPresent()) {
                UserModel userModel = userOp.get();
                response.setEstado(true);
                response.setMensagem("User encontrado com sucesso!");
                BeanUtils.copyProperties(userModel, response);
            } else {
                response.setEstado(false);
                response.setMensagem("User não encontrado!");
            }
        }
        return response;
    }

    */

    /*

    public UserListResponse getAllUsers (AllUsersRequest request) {

        UserListResponse response = new UserListResponse();
        SessionModel session = sessionRepo.findByToken(request.getHeader().getAuthToken());

        if ( session == null ){
            response.setEstado(false);
            response.setMensagem("Token inválido, undefined!");
        }
        else if (jwtToken.getSubject(request.getHeader().getAuthToken()).equals(" ")) {
            response.setEstado(false);
            response.setMensagem("Sessão expirada!");
            sessionRepo.deleteById(session.getId());
        }
        else {
            List<UserModel> userList = userRepo.findAll();
            if (!userList.isEmpty()) {
                response.setEstado(true);
                response.setMensagem("User encontrado com sucesso!");
                userList.forEach(
                    user -> {
                        UserType userType = new UserType();
                        BeanUtils.copyProperties(user, userType);
                        response.getUsers().add(userType);
                    });
            } else {
                response.setEstado(false);
                response.setMensagem("User não encontrado!");
            }
        }
        return response;
    }
    */
}
