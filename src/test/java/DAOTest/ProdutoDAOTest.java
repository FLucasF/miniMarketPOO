package DAOTest;

import DAO.ProdutoDAO;
import DAO.ProdutoNaoExistenteException;
import DTO.ProdutoDTO;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProdutoDAOTest {
    @Mock
    private ProdutoDAO produtoDAO;

    @Before
    public void init(){
        produtoDAO = new ProdutoDAO();

    }

    @Test
    public void testesMetodosPrincipaisJunit() {
        //Cadastro
        ProdutoDTO produtoDTO = new ProdutoDTO("teste", 10, 10);
        ProdutoDTO produtoDTOcadastrado = new ProdutoDTO();

        boolean cadastro = produtoDAO.cadastrarProduto(produtoDTO);
        assertTrue(cadastro);

        boolean cadastroRepetido = produtoDAO.verificaProduto("teste");
        assertTrue(cadastroRepetido);

        //Pesquisa
        try {
            produtoDTOcadastrado = produtoDAO.pesquisarProduto("teste");
        }catch (Exception e) {
            fail("Não deveria lançar exceção");
        }
        Assert.assertEquals("teste", produtoDTOcadastrado.getNome());
        Assert.assertEquals(10, produtoDTOcadastrado.getQuantidade());
        Assert.assertEquals(10.0, produtoDTOcadastrado.getValor());

        //Atualizar
        ProdutoDTO produtoAlterado = new ProdutoDTO("testeAlterar", 25, 15);
        produtoDAO.alterarProduto(produtoAlterado);
        Assert.assertEquals("testeAlterar", produtoAlterado.getNome());
        Assert.assertEquals(25, produtoAlterado.getQuantidade());
        Assert.assertEquals(15.0, produtoAlterado.getValor());

        //Excluir
        boolean produtoExcluido = produtoDAO.excluirProduto(produtoDTOcadastrado);
        assertTrue(produtoExcluido);
        boolean produtoExcluidoAt = produtoDAO.excluirProduto(produtoAlterado);
        assertTrue(produtoExcluidoAt);

    }

}