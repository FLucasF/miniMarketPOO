

package DAO;

import DTO.ProdutoDTO;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class ProdutoDAO implements ProductDAO_Interface {
    private ProdutoDTO produto;
    private Connection conn;
    private PreparedStatement pstm;
    ArrayList<ProdutoDTO> lista = new ArrayList<>();

    /*
     * Este método insere um novo registro de produto na tabela "products" do banco de dados. Ele realiza as seguintes ações:
     * 1. Define a instrução SQL de inserção.
     * 2. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 3. Prepara a declaração SQL com os parâmetros a serem inseridos.
     * 4. Define os valores dos parâmetros com base no objeto "ProdutoDTO".
     * 5. Executa a instrução SQL para inserir o novo produto.
     * 6. Fecha a declaração e retorna "true" se a inserção for bem-sucedida.
     * 7. Exibe uma mensagem de erro e retorna "false" em caso de falha na inserção.
     * @param objproductdto O objeto "ProdutoDTO" contendo informações do produto a ser cadastrado.
     * @return "true" se o produto for cadastrado com sucesso, caso contrário, "false".
     */
    @Override
    public boolean cadastrarProduto(ProdutoDTO objproductdto) {
        String sql = "insert into products (nome, quantidade, valor) values (?,?,?)";
        conn = new Conexao().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objproductdto.getNome());
            pstm.setInt(2, objproductdto.getQuantidade());
            pstm.setDouble(3, objproductdto.getValor());

            pstm.execute();
            pstm.close();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!");
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Define a instrução SQL de atualização para modificar um registro de produto existente.
     * 2. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 3. Prepara a declaração SQL com os parâmetros a serem atualizados.
     * 4. Define os valores dos parâmetros com base no objeto "ProdutoDTO".
     * 5. Executa a instrução SQL para atualizar o produto.
     * 6. Fecha a declaração e retorna "true" se a atualização for bem-sucedida.
     * 7. Exibe uma mensagem de erro e retorna "false" em caso de falha na atualização.
     * @param objproductdto O objeto "ProdutoDTO" contendo informações do produto a ser atualizado.
     * @return "true" se o produto for atualizado com sucesso, caso contrário, "false".
     */
    @Override
    public boolean alterarProduto(ProdutoDTO objproductdto) {
        String sql = "UPDATE products set nome = ?, quantidade = ?, valor = ? where nome = ?";
        conn = new Conexao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objproductdto.getNome());
            pstm.setInt(2, objproductdto.getQuantidade());//é pra ser inteiro
            pstm.setDouble(3, objproductdto.getValor());
            pstm.setString(4, objproductdto.getNome());

            pstm.execute();
            pstm.close();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar produto!");
            e.printStackTrace();
            return false;

        }
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Define a instrução SQL de exclusão para remover um registro de produto pelo nome.
     * 2. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 3. Prepara a declaração SQL com o parâmetro nome a ser excluído.
     * 4. Define o valor do parâmetro com base no objeto "ProdutoDTO".
     * 5. Executa a instrução SQL para excluir o produto.
     * 6. Fecha a declaração e retorna "true" se a exclusão for bem-sucedida.
     * 7. Exibe uma mensagem de erro e retorna "false" em caso de falha na exclusão.
     * @param objproductdto O objeto "ProdutoDTO" contendo o nome do produto a ser excluído.
     * @return "true" se o produto for excluído com sucesso, caso contrário, "false".
     */
    @Override
    public boolean excluirProduto(ProdutoDTO objproductdto) {
        String sql = "DELETE FROM products WHERE nome = ?";

        conn = new Conexao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objproductdto.getNome());
            pstm.execute();
            pstm.close();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!");
            e.printStackTrace();
            return false;

        }
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Define a instrução SQL para selecionar todos os produtos da tabela "products".
     * 2. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 3. Prepara a declaração SQL.
     * 4. Executa a consulta SQL e obtém um conjunto de resultados (ResultSet).
     * 5. Itera através do conjunto de resultados para recuperar os dados de cada produto.
     * 6. Para cada produto recuperado, cria um objeto "ProdutoDTO" com os dados e adiciona-o à lista.
     * 7. Fecha o ResultSet e a declaração.
     * 8. Trata exceções do tipo SQLException, exibindo uma mensagem de erro em caso de falha na consulta.
     * 9. Retorna a lista de produtos recuperados.
     * @return Uma lista de objetos "ProdutoDTO" contendo os produtos recuperados do banco de dados.
     */
    @Override
    public ArrayList<ProdutoDTO> listarProduto () {
        String sql = "SELECT * FROM products";
        conn = new Conexao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                double valor = rs.getDouble("valor");

                // Crie um objeto ProdutoDTO com os dados recuperados e adicione à lista
                ProdutoDTO product = new ProdutoDTO();
                product.setNome(nome);
                product.setQuantidade(quantidade);
                product.setValor(valor);

                lista.add(product);
            }

            rs.close();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Define a instrução SQL para selecionar todos os produtos da tabela "products" em ordem crescente de valor.
     * 2. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 3. Prepara a declaração SQL.
     * 4. Executa a consulta SQL e obtém um conjunto de resultados (ResultSet).
     * 5. Itera através do conjunto de resultados para recuperar os dados de cada produto.
     * 6. Para cada produto recuperado, cria um objeto "ProdutoDTO" com os dados e adiciona-o à lista.
     * 7. Fecha o ResultSet e a declaração.
     * 8. Trata exceções do tipo SQLException, exibindo uma mensagem de erro em caso de falha na consulta.
     * 9. Retorna a lista de produtos recuperados em ordem crescente de valor.
     * @return Uma lista de objetos "ProdutoDTO" contendo os produtos recuperados do banco de dados em ordem crescente de valor.
     */
    @Override
    public ArrayList<ProdutoDTO> listarProdutoValorCrescente() {
        String sql = "SELECT * FROM products ORDER BY products.valor ASC";
        conn = new Conexao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                double valor = rs.getDouble("valor");

                // Crie um objeto ProdutoDTO com os dados recuperados e adicione à lista
                ProdutoDTO product = new ProdutoDTO();
                product.setNome(nome);
                product.setQuantidade(quantidade);
                product.setValor(valor);

                lista.add(product);
            }

            rs.close();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Define a instrução SQL para selecionar todos os produtos da tabela "products" em ordem alfabética de nome.
     * 2. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 3. Prepara a declaração SQL.
     * 4. Executa a consulta SQL e obtém um conjunto de resultados (ResultSet).
     * 5. Itera através do conjunto de resultados para recuperar os dados de cada produto.
     * 6. Para cada produto recuperado, cria um objeto "ProdutoDTO" com os dados e adiciona-o à lista.
     * 7. Fecha o ResultSet e a declaração.
     * 8. Trata exceções do tipo SQLException, exibindo uma mensagem de erro em caso de falha na consulta.
     * 9. Retorna a lista de produtos recuperados em ordem alfabética de nome.
     * @return Uma lista de objetos "ProdutoDTO" contendo os produtos recuperados do banco de dados em ordem alfabética de nome.
     */
    @Override
    public ArrayList<ProdutoDTO> listarProdutoOrdemAlfabetica() {
        String sql = "SELECT * FROM products ORDER BY products.nome ASC";
        conn = new Conexao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                int quantidade = rs.getInt("quantidade");
                double valor = rs.getDouble("valor");

                // Crie um objeto ProdutoDTO com os dados recuperados e adicione à lista
                ProdutoDTO product = new ProdutoDTO();
                product.setNome(nome);
                product.setQuantidade(quantidade);
                product.setValor(valor);

                lista.add(product);
            }

            rs.close();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Define a instrução SQL para selecionar um produto da tabela "products" com o nome especificado.
     * 2. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 3. Prepara a declaração SQL com o parâmetro nome a ser pesquisado.
     * 4. Define o valor do parâmetro com base no nome especificado.
     * 5. Executa a consulta SQL e obtém um conjunto de resultados (ResultSet).
     * 6. Verifica se existe um resultado na consulta.
     * 7. Se um resultado for encontrado, cria um objeto "ProdutoDTO" com os dados, fecha o ResultSet e a declaração, e retorna o objeto.
     * 8. Se nenhum resultado for encontrado, lança uma exceção "ProdutoNaoExistenteException".
     * 9. Trata exceções do tipo Exception, exibindo uma mensagem de erro em caso de falha na consulta.
     * 10. Lança uma exceção "ProdutoNaoExistenteException" se o produto não for encontrado.
     * @param nome O nome do produto a ser buscado no banco de dados.
     * @return Um objeto "ProdutoDTO" contendo os dados do produto encontrado.
     * @throws ProdutoNaoExistenteException Se o produto não for encontrado no banco de dados.
     */
    @Override
    public ProdutoDTO pesquisarProduto(String nome) throws ProdutoNaoExistenteException {
        String sql = "SELECT * FROM products WHERE nome = ?";
        conn = new Conexao().conectaBD();
        ProdutoDTO produtoDTO = new ProdutoDTO();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                produtoDTO.setNome(rs.getString("nome"));
                produtoDTO.setQuantidade(rs.getInt("quantidade"));
                produtoDTO.setValor(rs.getDouble("valor"));
                rs.close();
                pstm.close();

                return produtoDTO;
            } else {
                throw new ProdutoNaoExistenteException("Produto não existente");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!");
            e.printStackTrace();

        }
        throw new ProdutoNaoExistenteException("Produto não existente");
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Estabelece uma conexão com o banco de dados utilizando a classe "Conexao".
     * 2. Define a instrução SQL para selecionar um produto da tabela "products" com base no nome fornecido.
     * 3. Prepara a declaração SQL com o parâmetro "nome" a ser utilizado como critério de seleção.
     * 4. Define o valor do parâmetro com base no nome fornecido como argumento.
     * 5. Executa a consulta SQL e obtém um conjunto de resultados (ResultSet) que contém informações do produto com o nome especificado.
     * 6. Verifica se o ResultSet possui algum resultado usando o método rs.next(). Se houver resultados, significa que o produto com o nome existe.
     * 7. Retorna true se o produto existir, caso contrário, retorna false.
     * 8. Trata exceções do tipo Exception, exibindo uma mensagem de erro em caso de falha na consulta.
     * @param nome O nome do produto a ser verificado.
     * @return true se o produto com o nome especificado existir, caso contrário, retorna false.
     */
    @Override
    public boolean verificaProduto(String nome) {
        String sql = "SELECT * FROM products WHERE nome = ?";
        conn = new Conexao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto!");
            e.printStackTrace();

        }
        return false;
    }

}
