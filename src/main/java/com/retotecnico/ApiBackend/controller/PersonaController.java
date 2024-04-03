package com.retotecnico.ApiBackend.controller;

import com.retotecnico.ApiBackend.entity.Persona;
import com.retotecnico.ApiBackend.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-persona")
public class PersonaController {

    @Autowired
    protected PersonaService personaService;

    @PostMapping("/save")
    public Persona save(@RequestBody Persona persona){
        return personaService.save(persona);
    }

    @GetMapping("/list-all")
    public List<Persona> findAll(){
        return personaService.findAll();
    }

    @GetMapping("/list")
    public Persona findById(@RequestParam("id") Integer id){
        return personaService.findById(id);
    }
    @GetMapping("/list/{id}")
    public Persona findByIdPath(@PathVariable("id") Integer id){
        return personaService.findById(id);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editPersona(@RequestBody Persona persona, @PathVariable("id") Integer id){
        if (findById(id) != null){
            persona.setId(id);
            return new ResponseEntity<>(personaService.editPersona(persona), HttpStatus.OK);
        }
        else return new ResponseEntity<>("Elemento no encontrado",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/truncate")
    public void truncate(){
        personaService.truncateTable();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        personaService.deletePersona(id);
    }

}