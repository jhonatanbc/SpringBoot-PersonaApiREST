package com.retotecnico.ApiBackend.repository;

import com.retotecnico.ApiBackend.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    @Modifying
    @Query(value = "TRUNCATE TABLE personas", nativeQuery = true)
    void truncateTable();
}
