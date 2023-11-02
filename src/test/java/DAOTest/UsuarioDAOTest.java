package DAOTest;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

public class UsuarioDAOTest {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    @Test
    public void cadastrarUsuarioComSucesso() {
        UsuarioDTO usuarioDTOcadastrar = new UsuarioDTO();
        usuarioDTOcadastrar.setNome_usuario("teste");
        usuarioDTOcadastrar.setSenha_usuario("1234");
        boolean cadastrar = usuarioDAO.cadastrarUsuario(usuarioDTOcadastrar);
        assertTrue(cadastrar);

        boolean excluiu = usuarioDAO.excluirUsuario(usuarioDTOcadastrar);
        assertTrue(excluiu);
    }

}