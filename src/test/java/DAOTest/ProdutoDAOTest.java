package DAOTest;

import DAO.ProdutoDAO;
import DTO.ProdutoDTO;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

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

        //Excluir
        boolean produtoExcluido = produtoDAO.excluirProduto(produtoDTOcadastrado);
        assertTrue(produtoExcluido);

    }

}