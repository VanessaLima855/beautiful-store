package com.api.beautifulstore.controller;

import com.api.beautifulstore.entity.Categoria;
import com.api.beautifulstore.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/salvar-categoria")
    public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria categoria){
        Categoria categoriaSalva = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> inativarCategoria(@PathVariable Long id, @RequestBody Boolean param){
        try {
            categoriaService.inativarCategoria(id, param);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

}
