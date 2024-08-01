package com.example.bikeshared.database.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail ( String email );
    UserModel findById ( long id );

    

}
