package com.example.bikeshared.services.user.session;
import org.springframework.stereotype.Service;

import com.example.bikeshared.database.user.session.SessionModel;
import com.example.bikeshared.database.user.session.SessionModelRequest;
import com.example.bikeshared.database.user.session.SessionRepository;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SessionService {
    @Autowired(required = true)
    private SessionRepository sessionRepo;

    public boolean loggedIn (long UserId){
        SessionModel session = sessionRepo.findByUserId(UserId);
        return session!=null;
    }

    public boolean deleteSession (SessionModel session){
            sessionRepo.delete(session);
            return true;            
    }

    public SessionModel initSession (SessionModelRequest request){
        SessionModel session = new SessionModel();
        session.setToken(request.getToken());
        session.setUserId(request.getUserId());
        session.setDataCreated(LocalDateTime.now());
        session.setDataUpdated(session.getDataCreated());
        return sessionRepo.save(session);
    }

    
}
