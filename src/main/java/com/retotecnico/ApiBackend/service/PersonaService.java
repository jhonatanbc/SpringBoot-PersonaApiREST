package com.retotecnico.ApiBackend.service;

import com.retotecnico.ApiBackend.entity.Persona;

import java.util.List;

public interface PersonaService{
    public Persona save(Persona persona);
    public List<Persona> findAll();
    public Persona findById(Integer id);
    public void truncateTable();
    public Persona editPersona(Persona Persona);
    public void deletePersona (Integer id);

}