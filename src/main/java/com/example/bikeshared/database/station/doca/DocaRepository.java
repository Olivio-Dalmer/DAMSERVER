package com.example.bikeshared.database.station.doca;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DocaRepository extends JpaRepository<DocaModel, Long> {
    DocaModel findByIdEstacao (long idEstacao );
    DocaModel findById (long id);
    long countByIdEstacao(long idEstacao);

}
