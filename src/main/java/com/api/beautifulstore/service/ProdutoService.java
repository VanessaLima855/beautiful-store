package com.api.beautifulstore.service;

import com.api.beautifulstore.entity.Categoria;
import com.api.beautifulstore.entity.Marca;
import com.api.beautifulstore.entity.Produto;
import com.api.beautifulstore.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private CategoriaService categoriaService;
    private MarcaService marcaService;
    private ProdutoRepository produtoRepository;

    public ProdutoService(CategoriaService categoriaService, MarcaService marcaService,
                          ProdutoRepository produtoRepository) {
        this.categoriaService = categoriaService;
        this.marcaService = marcaService;
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }
    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> buscarTodosOsProdutos(){
        return produtoRepository.findAll();
    }

    public List<Produto> buscarProdutoPorMarca(String marca){
        return produtoRepository.buscarProdutoPorMarca(marca);
    }

    public List<Produto> buscarProdutoPorCategoria(String categoria){
        return produtoRepository.buscarProdutoPorCategoria(categoria);
    }

    public void inativarProduto(Long id, Boolean param){
        try {
            Produto produto = produtoRepository.getReferenceById(id);
            produto.setAtivo(param);
            produtoRepository.save(produto);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Não existe produto com esse id: " + id);
        }
    }
}
