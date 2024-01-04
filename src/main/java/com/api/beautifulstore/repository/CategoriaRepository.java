package com.api.beautifulstore.repository;

import com.api.beautifulstore.entity.Categoria;
import com.api.beautifulstore.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {


}
