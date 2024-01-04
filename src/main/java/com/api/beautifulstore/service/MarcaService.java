package com.api.beautifulstore.service;

import com.api.beautifulstore.entity.Marca;
import com.api.beautifulstore.entity.Produto;
import com.api.beautifulstore.repository.MarcaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarcaService {
    private MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public Marca salvar(Marca marca){
        return marcaRepository.save(marca);
    }

    public Optional<Marca> buscarMarcaPorId(Long id) {
        return marcaRepository.findById(id);
    }

    public void inativarMarca(Long id, Boolean param){
        try {
            Marca marca = marcaRepository.getReferenceById(id);
            marca.setAtiva(param);
            marcaRepository.save(marca);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Não existe marca com esse id: " + id);
        }
    }
}
