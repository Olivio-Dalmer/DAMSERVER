package com.example.bikeshared.database.station;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EstacaoRepository extends JpaRepository<EstacaoModel, Long> {
    EstacaoModel findById (long id);

}