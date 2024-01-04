package com.api.beautifulstore.controller;

import com.api.beautifulstore.entity.Categoria;
import com.api.beautifulstore.entity.Marca;
import com.api.beautifulstore.service.MarcaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    private MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping("/salvar-marca")
    public ResponseEntity<Marca> salvarMarca(@RequestBody Marca marca){
        Marca marcaSalva = marcaService.salvar(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaSalva);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> inativarMarca(@PathVariable Long id, @RequestBody Boolean param){
        try {
            marcaService.inativarMarca(id, param);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

}


