package com.retotecnico.ApiBackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retotecnico.ApiBackend.entity.Persona;
import com.retotecnico.ApiBackend.model.ErrorModel;
import com.retotecnico.ApiBackend.service.PersonaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api-persona")
public class PersonaController {

    private final ObjectMapper objectMapper;
    @Autowired
    protected PersonaService personaService;

    public PersonaController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Persona persona){
        try{
            log.info("Request Body: {}",objectMapper.writeValueAsString(persona));
            ResponseEntity<?> response =  ResponseEntity.ok(personaService.save(persona));
            log.info(response.getStatusCode() + " Response Body: {}",objectMapper.writeValueAsString(response.getBody()));
            return response;
        }catch (Exception e){
            log.error("Error guardando la persona: {}" + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error guardando la persona");
        }
    }

    @GetMapping("/list-all")
    public ResponseEntity<?> findAll(){
        List<Persona> list = personaService.findAll();
        if(!list.isEmpty()) return ResponseEntity.ok(personaService.findAll());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran personas registradas");
    }

    @GetMapping("/list")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id){
        Persona persona = personaService.findById(id);
        if (persona != null)
            return ResponseEntity.ok(persona);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no registrada");
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") Integer id){
        Persona persona = personaService.findById(id);
        if (persona != null)
            return ResponseEntity.ok(persona);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no registrada");
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editPersona(@RequestBody Persona persona, @PathVariable("id") Integer id){
        Persona personaFind = personaService.findById(id);
        if (personaFind != null){
            persona.setId(id);
            try{
                log.info("Request Body: {}",objectMapper.writeValueAsString(persona));
                ResponseEntity<?> response =  ResponseEntity.ok(personaService.editPersona(persona));
                log.info(response.getStatusCode() + " Response Body: {}",objectMapper.writeValueAsString(response.getBody()));
                return response;
            }catch (Exception e){
                log.error("Error editando la persona: {}" + e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error editando la persona");
            }
        }
        else return new ResponseEntity<>("Persona no registrada",HttpStatus.NOT_FOUND);
    }
    @GetMapping("/truncate")
    public void truncate(){
        personaService.truncateTable();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id){
        Persona persona = personaService.findById(id);
        if (persona != null){
            personaService.deletePersona(id);
            return ResponseEntity.ok("Registro eliminado: "+persona.getNombres());
        }
        else return new ResponseEntity<>("Persona no registrada",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleValidationException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setErrorCode("E001");
        errorModel.setErrorType("Campos Obligatorios");
        assert fieldError != null;
        errorModel.setErrorDescription(fieldError.getDefaultMessage());
        try {
            log.error(" Response Body: {}", objectMapper.writeValueAsString(errorModel));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
    }
}