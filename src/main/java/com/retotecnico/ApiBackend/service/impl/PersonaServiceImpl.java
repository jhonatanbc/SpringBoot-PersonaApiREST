package com.retotecnico.ApiBackend.service.impl;

import com.retotecnico.ApiBackend.entity.Persona;
import com.retotecnico.ApiBackend.repository.PersonaRepository;
import com.retotecnico.ApiBackend.service.PersonaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    protected PersonaRepository personaRepository;
    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    @Override
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }
    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }
    @Override
    public Persona findById(Integer id) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        return personaOptional.orElse(null);
    }
    @Transactional
    public void truncateTable() {
        personaRepository.truncateTable();
    }

    @Override
    public Persona editPersona(Persona Persona) {
        return personaRepository.save(Persona);
    }
    @Override
    public void deletePersona(Integer id) {
        personaRepository.deleteById(id);
    }

}
