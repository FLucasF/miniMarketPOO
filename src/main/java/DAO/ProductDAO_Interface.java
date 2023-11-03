package DAO;

import DTO.ProdutoDTO;

import java.util.ArrayList;

public interface ProductDAO_Interface {
    boolean cadastrarProduto(ProdutoDTO objproductdto);
    boolean alterarProduto(ProdutoDTO objproductdto);
    boolean excluirProduto(ProdutoDTO objproductdto);
    boolean verificaProduto(String nome);
    ProdutoDTO pesquisarProduto(String nome) throws ProdutoNaoExistenteException;
    ArrayList<ProdutoDTO> listarProdutoOrdemAlfabetica();
    ArrayList<ProdutoDTO> listarProduto();
    ArrayList<ProdutoDTO> listarProdutoValorCrescente();
}
