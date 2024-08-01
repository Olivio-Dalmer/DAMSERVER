package com.example.bikeshared.database.user.gestor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface GestorRepository extends JpaRepository<GestorModel, Long> {
    GestorModel findByIdUser ( long idUser );
}