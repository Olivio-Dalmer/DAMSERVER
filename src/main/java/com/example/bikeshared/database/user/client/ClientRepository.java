package com.example.bikeshared.database.user.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    ClientModel findByIdUser ( long idUser );
}
