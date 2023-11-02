package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO implements UsuarioDAO_Interface {
    private UsuarioDTO usuarioDTO;
    private Connection conn = null;
    private PreparedStatement pstm;

    /*
     * Este método realiza as seguintes ações:
     * 1. Define a instrução SQL para inserir um novo usuário na tabela "usuario" com o nome de usuário e senha especificados.
     * 2. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 3. Prepara a declaração SQL com os parâmetros nome de usuário e senha a serem cadastrados.
     * 4. Define os valores dos parâmetros com base nos dados fornecidos no objeto "UsuarioDTO".
     * 5. Executa a operação de inserção no banco de dados.
     * 6. Fecha a declaração.
     * 7. Retorna verdadeiro se o cadastro for bem-sucedido, caso contrário, retorna falso.
     * 8. Trata exceções do tipo Exception, exibindo uma mensagem de erro em caso de falha no cadastro.
     * @param objUsuarioDTO O objeto "UsuarioDTO" contendo os dados do usuário a ser cadastrado.
     * @return true se o cadastro do usuário for bem-sucedido, false caso contrário.
     */
    @Override
    public boolean cadastrarUsuario(UsuarioDTO objUsuarioDTO) {
        String sql = "INSERT INTO usuario (nome_usuario, senha_usuario) VALUES (?,?)";

        conn = new Conexao().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuarioDTO.getNome_usuario());
            pstm.setInt(2, Integer.parseInt(objUsuarioDTO.getSenha_usuario()));

            pstm.execute();
            pstm.close();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuario!");
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 2. Define a instrução SQL para selecionar um usuário da tabela "usuario" com base no nome de usuário e senha fornecidos.
     * 3. Prepara a declaração SQL com os parâmetros nome de usuário e senha a serem verificados.
     * 4. Define os valores dos parâmetros com base nos dados fornecidos no objeto "UsuarioDTO".
     * 5. Executa a consulta SQL e obtém um conjunto de resultados (ResultSet) que contém informações de usuário se a autenticação for bem-sucedida.
     * 6. Retorna o ResultSet contendo informações de usuário se a autenticação for bem-sucedida, caso contrário, retorna null.
     * 7. Trata exceções do tipo SQLException, exibindo uma mensagem de erro em caso de falha na consulta.
     * @param objusuariodto O objeto "UsuarioDTO" contendo os dados do usuário a ser autenticado.
     * @return Um ResultSet contendo informações de usuário se a autenticação for bem-sucedida, caso contrário, retorna null.
     */
    @Override
    public ResultSet autenticacaoUsuario (UsuarioDTO objusuariodto) {
        conn = new Conexao().conectaBD();

        try {
            String sql = "SELECT * FROM usuario WHERE nome_usuario = ? AND senha_usuario = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getNome_usuario()); //primeiro ponto de interrogação
            pstm.setString(2, objusuariodto.getSenha_usuario()); //segundo ponto de interrogação
            ResultSet rs = pstm.executeQuery();

            return rs;

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + e);
            return null;
        }
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 2. Define a instrução SQL para excluir um usuário da tabela "usuario" com base no nome de usuário fornecido.
     * 3. Prepara a declaração SQL com o parâmetro "nome_usuario" a ser utilizado como critério de exclusão.
     * 4. Define o valor do parâmetro com base no nome de usuário fornecido no objeto "UsuarioDTO".
     * 5. Executa a consulta SQL para excluir o usuário com o nome de usuário especificado.
     * 6. Retorna true se a exclusão for bem-sucedida, caso contrário, retorna false.
     * 7. Trata exceções do tipo Exception, exibindo uma mensagem de erro em caso de falha na exclusão.
     * @param usuarioDTO O objeto "UsuarioDTO" contendo o nome de usuário a ser excluído.
     * @return true se a exclusão for bem-sucedida, caso contrário, retorna false.
     */
    @Override
    public boolean excluirUsuario(UsuarioDTO usuarioDTO) {
        String sql = "DELETE FROM usuario WHERE nome_usuario = ?";

        conn = new Conexao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, usuarioDTO.getNome_usuario());
            pstm.execute();
            pstm.close();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuario!");
            e.printStackTrace();
            return false;

        }
    }

}
