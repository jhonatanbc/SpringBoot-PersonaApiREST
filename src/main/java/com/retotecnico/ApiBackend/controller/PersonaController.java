package com.retotecnico.ApiBackend.controller;

import com.retotecnico.ApiBackend.entity.Persona;
import com.retotecnico.ApiBackend.model.ErrorModel;
import com.retotecnico.ApiBackend.service.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-persona")
public class PersonaController {

    @Autowired
    protected PersonaService personaService;

    @PostMapping("/save")
    public Persona save(@Valid @RequestBody Persona persona){
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleValidationException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        ErrorModel errorModel = new ErrorModel();
        errorModel.setErrorCode("E001");
        errorModel.setErrorType("Campos Obligatorios");
        assert fieldError != null;
        errorModel.setErrorDescription(fieldError.getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
    }
}