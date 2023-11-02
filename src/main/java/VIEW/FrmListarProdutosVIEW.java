package VIEW;

import DAO.ProdutoDAO;
import DTO.ProdutoDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class FrmListarProdutosVIEW extends JFrame {
    private DefaultTableModel tableModel;
    private JTable productTable;

    public FrmListarProdutosVIEW() {
        setTitle("Listar Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nome");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Valor");

        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);

        /*
         * Método para tratar o evento de fechamento da janela.
         * Quando a janela é fechada pelo usuário. Ele executa as seguintes ações:
         * 1. Redireciona a janela atual para o menu principal, usando o método "mudarParaJanela" da classe FrmMenuOpcoesListarVIEW.
         * 2. Libera os recursos da janela atual, permitindo sua disposição.
         * @param e O evento de fechamento da janela que acionou este método.
         */
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                FrmMainMenuVIEW frmMainMenuVIEW = new FrmMainMenuVIEW();
                frmMainMenuVIEW.setVisible(true);
                dispose();
            }
        });


    }
    /*
     * Este método limpa a tabela e preenche-a com a lista de produtos obtida a partir da classe ProdutoDAO.
     * Ele realiza as seguintes ações:
     * 1. Limpa a tabela existente, removendo todas as linhas.
     * 2. Cria uma instância de ProdutoDAO para acessar a fonte de dados.
     * 3. Chama o método listarProduto da classe ProdutoDAO para obter a lista de produtos.
     * 4. Atualiza a tabela com os produtos retornados, adicionando linhas à tabela com informações de nome, quantidade e valor de cada produto.
     * 5. Trata exceções do tipo Exception, se ocorrerem ao listar os produtos, exibindo uma mensagem de erro.
     */
    public void listarProduto() {
        tableModel.setRowCount(0); //LIMPAR A TABELA
        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            ArrayList<ProdutoDTO> produtos = produtoDAO.listarProduto();

            // ATUALIZAR A TABELA COM OS PRODUTOS RETORNADOS
            for (ProdutoDTO produto : produtos) {
                tableModel.addRow(new Object[]{produto.getNome(), produto.getQuantidade(), produto.getValor()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + ex.getMessage());
        }
    }

    /*
     * Este método limpa a tabela e preenche-a com a lista de produtos em ordem alfabética obtida a partir da classe ProdutoDAO.
     * Ele realiza as seguintes ações:
     * 1. Limpa a tabela existente, removendo todas as linhas.
     * 2. Cria uma instância de ProdutoDAO para acessar a fonte de dados.
     * 3. Chama o método listarProdutoOrdemAlfabetica da classe ProdutoDAO para obter a lista de produtos em ordem alfabética.
     * 4. Atualiza a tabela com os produtos retornados, adicionando linhas à tabela com informações de nome, quantidade e valor de cada produto.
     * 5. Trata exceções do tipo Exception, se ocorrerem ao listar os produtos, exibindo uma mensagem de erro.
     */
    public void listarProdutosAlfabetico() {
        tableModel.setRowCount(0);//LIMPAR A TABELA
        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            ArrayList<ProdutoDTO> produtos = produtoDAO.listarProdutoOrdemAlfabetica();

            // ATUALIZAR A TABELA COM OS PRODUTOS RETORNADOS
            for (ProdutoDTO produto : produtos) {
                tableModel.addRow(new Object[]{produto.getNome(), produto.getQuantidade(), produto.getValor()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + ex.getMessage());
        }
    }

    /*
     * Este trecho de código realiza as seguintes ações:
     * 1. Configura um ouvinte de eventos na janela atual para lidar com o evento de fechamento da janela.
     * 2. Quando o usuário tenta fechar a janela, cria uma nova instância da classe "FrmMainMenuVIEW", que é uma janela que representa o menu principal da aplicação.
     * 3. Torna a janela "FrmMainMenuVIEW" visível, exibindo-a ao usuário.
     * 4. Chama o método "dispose()" na janela atual, que libera os recursos associados à janela e a fecha de forma controlada.
     */
    public void listarProdutoValorMenor() {
        tableModel.setRowCount(0);//LIMPAR A TABELA
        ProdutoDAO produtoDAO = new ProdutoDAO();

        try {
            // Chame o método listarProdutos para obter a lista de produtos
            ArrayList<ProdutoDTO> produtos = produtoDAO.listarProdutoValorCrescente();

            // ATUALIZAR A TABELA COM OS PRODUTOS RETORNADOS
            for (ProdutoDTO produto : produtos) {
                tableModel.addRow(new Object[]{produto.getNome(), produto.getQuantidade(), produto.getValor()});
            }
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + ex.getMessage());
        }
    }
}
