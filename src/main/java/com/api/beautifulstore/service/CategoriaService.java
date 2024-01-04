package com.api.beautifulstore.service;

import com.api.beautifulstore.entity.Categoria;
import com.api.beautifulstore.entity.Marca;
import com.api.beautifulstore.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvar(Categoria categoria){
        return categoriaRepository.save(categoria);
    }


    public Optional<Categoria> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);

    }
    public void inativarCategoria(Long id, Boolean param){
        try {
            Categoria categoria = categoriaRepository.getReferenceById(id);
            categoria.setAtiva(param);
            categoriaRepository.save(categoria);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Não existe categoria com esse id: " + id);
        }
    }
}
