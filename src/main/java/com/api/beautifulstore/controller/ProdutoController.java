package com.api.beautifulstore.controller;


import com.api.beautifulstore.entity.Categoria;
import com.api.beautifulstore.entity.Marca;
import com.api.beautifulstore.entity.Produto;
import com.api.beautifulstore.service.CategoriaService;
import com.api.beautifulstore.service.MarcaService;
import com.api.beautifulstore.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController( ProdutoService produtoService) {
        this.produtoService = produtoService;

    }

    @PostMapping("/salvar-produto")
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto){
        Produto produtoSalvo = produtoService.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("listar-produtos")
    public ResponseEntity<List<Produto>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarTodosOsProdutos());
    }

    @GetMapping("/marca")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> buscarProdutoPorMarca(@RequestParam(value = "param") String marca){
        return produtoService.buscarProdutoPorMarca(marca);
    }

    @GetMapping("/categoria")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> buscarProdutoPorCategoria(@RequestParam(value = "param") String categoria){
        return produtoService.buscarProdutoPorCategoria(categoria);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> inativarProduto(@PathVariable Long id, @RequestBody Boolean param){
        try {
            produtoService.inativarProduto(id, param);
        }catch (EntityNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }

}
