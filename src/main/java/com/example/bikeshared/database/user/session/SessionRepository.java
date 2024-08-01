package com.example.bikeshared.database.user.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionRepository extends JpaRepository<SessionModel, Long> {
        SessionModel findByUserId ( long UserId );
        SessionModel findByToken ( String token );
    
}
