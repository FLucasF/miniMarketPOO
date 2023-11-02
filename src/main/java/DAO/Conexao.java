package DAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    /*
     * Este método realiza as seguintes ações:
     * 1. Define a URL de conexão com o banco de dados MySQL, que inclui o host, a porta, o nome do banco de dados, o nome de usuário e a senha.
     * 2. Utiliza a classe DriverManager para criar uma conexão com o banco de dados com base na URL especificada.
     * 3. Retorna a conexão estabelecida.
     * 4. Trata exceções do tipo SQLException, exibindo uma mensagem de erro em caso de falha na conexão.
     * @return Uma conexão ativa com o banco de dados MySQL.
     */
    public Connection conectaBD () {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/bancomarket?user=root&password=1234";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexao" + e.getMessage());
        }
        
        return conn;
    }
}
