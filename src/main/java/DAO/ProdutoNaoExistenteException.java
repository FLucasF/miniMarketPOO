package DAO;

public class ProdutoNaoExistenteException extends Exception {
    public ProdutoNaoExistenteException(String msg) {
        super(msg);
    }
}
