package VIEW;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FrmMainMenuVIEW extends JFrame {
    public FrmMainMenuVIEW() {
        setTitle("Menu");
        setLayout(null);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);


        JLabel label = new JLabel("Market");
        label.setBounds(120,1,400,50);
        label.setFont(new Font("Arial", Font.BOLD, 40));
        label.setForeground(Color.black);
        add(label);

        JButton btnCadastrarMenu = new JButton("Cadastrar");
        btnCadastrarMenu.setBounds(135, 100, 100, 30);
        btnCadastrarMenu.setForeground(Color.white);
        btnCadastrarMenu.setBackground(Color.black);
        add(btnCadastrarMenu);

        JButton btnExcluirMenu = new JButton("Excluir");
        btnExcluirMenu.setBounds(135, 150, 100, 30);
        btnExcluirMenu.setForeground(Color.white);
        btnExcluirMenu.setBackground(Color.black);
        add(btnExcluirMenu);

        JButton btnBuscarProduto = new JButton("Pesquisar");
        btnBuscarProduto.setBounds(135, 200, 100, 30);
        btnBuscarProduto.setForeground(Color.white);
        btnBuscarProduto.setBackground(Color.black);
        add(btnBuscarProduto);

        JButton btnAtualizarMenu = new JButton("Atualizar");
        btnAtualizarMenu.setBounds(135, 250, 100, 30);
        btnAtualizarMenu.setForeground(Color.white);
        btnAtualizarMenu.setBackground(Color.black);
        add(btnAtualizarMenu);

        JButton btnListarMenu = new JButton("Listar");
        btnListarMenu.setBounds(135, 300, 100, 30);
        btnListarMenu.setForeground(Color.white);
        btnListarMenu.setBackground(Color.black);
        add(btnListarMenu);

        /*
         * Este código realiza as seguintes ações:
         * 1. Tenta carregar uma imagem do arquivo especificado.
         * 2. Redimensiona a imagem para que se ajuste às dimensões da janela atual.
         * 3. Cria um JLabel e define a imagem como um ícone do JLabel.
         * 4. Define a posição e dimensões do JLabel para cobrir toda a janela.
         * 5. Adiciona o JLabel como plano de fundo à janela atual.
         */
        BufferedImage img;
        Image instImage;
        try {
            img = ImageIO.read(new File("src" + File.separator + "main" + File.separator +
                    "java" + File.separator + "VIEW" + File.separator + "imagem" + File.separator +
                    "backGroundMenu.png"));
            instImage = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            JLabel image = new JLabel();
            ImageIcon imageIcon = new ImageIcon(instImage);
            image.setBounds(-14, 0, this.getWidth(), this.getHeight());
            image.setIcon(imageIcon);
            this.add(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * Este trecho de código realiza as seguintes ações:
         * 1. Configura um ouvinte de eventos na janela atual para lidar com o evento de fechamento da janela.
         * 2. Quando o usuário tenta fechar a janela, cria uma nova instância da classe "FrmLoginVIEW".
         * 3. Torna a janela "FrmLoginVIEW" visível, exibindo-a ao usuário.
         * 4. Chama o método "dispose()" na janela atual, que libera os recursos associados à janela e a fecha de forma controlada.
         */
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                FrmLoginAppVIEW frmLoginAppVIEW = new FrmLoginAppVIEW();
                frmLoginAppVIEW.setVisible(true);
                dispose();
            }
        });

        btnCadastrarMenu.addActionListener(this::btnCadastrarMenu);
        btnAtualizarMenu.addActionListener(this::btnAtualizarMenu);
        btnExcluirMenu.addActionListener(this::btnExcluirMenu);
        btnListarMenu.addActionListener(this::btnListarMenu);
        btnBuscarProduto.addActionListener(this::btnBuscarProduto);

    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Cria uma nova instância da classe "FrmCadastrarProdutoVIEW".
     * 2. Torna a nova janela (FrmCadastrarProdutoVIEW) visível, exibindo-a ao usuário.
     * 3. Chama o método "dispose()" na janela atual.
     */
    private void btnCadastrarMenu(ActionEvent e) {
        FrmCadastrarProdutoVIEW frmCadastrarProdutoVIEW = new FrmCadastrarProdutoVIEW();
        frmCadastrarProdutoVIEW.setVisible(true);
        dispose();
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Cria uma nova instância da classe "FrmMenuOpcoesListarVIEW".
     * 2. Torna a nova janela (FrmMenuOpcoesListarVIEW) visível, exibindo-a ao usuário.
     * 3. Chama o método "dispose()" na janela atual.
     */
    private void btnListarMenu(ActionEvent e) {
        FrmMenuOpcoesListarVIEW frmMenuOpcoesListarVIEW = new FrmMenuOpcoesListarVIEW();
        frmMenuOpcoesListarVIEW.setVisible(true);
        dispose();
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Cria uma nova instância da classe "FrmAtualizarInformacaoProdutoVIEW".
     * 2. Torna a nova janela (FrmAtualizarInformacaoProdutoVIEW) visível, exibindo-a ao usuário.
     * 3. Chama o método "dispose()" na janela atual.
     */
    private void btnAtualizarMenu(ActionEvent e) {
        FrmAtualizarInformacaoProdutoVIEW frmAtualizarInformacaoProdutoVIEW = new FrmAtualizarInformacaoProdutoVIEW();
        frmAtualizarInformacaoProdutoVIEW.setVisible(true);
        dispose();
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Cria uma nova instância da classe "FrmExcluirProdutoVIEW".
     * 2. Torna a nova janela (FrmExcluirProdutoVIEW) visível, exibindo-a ao usuário.
     * 3. Chama o método "dispose()" na janela atual.
     */
    private void btnExcluirMenu(ActionEvent e) {
        FrmExcluirProdutoVIEW frmExcluirProdutoVIEW = new FrmExcluirProdutoVIEW();
        frmExcluirProdutoVIEW.setVisible(true);
        dispose();
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Cria uma nova instância da classe "FrmPesquisarProdutoVIEW".
     * 2. Torna a nova janela (FrmPesquisarProdutoVIEW) visível, exibindo-a ao usuário.
     * 3. Chama o método "dispose()" na janela atual.
     */
    private void btnBuscarProduto(ActionEvent e) {
        FrmPesquisarProdutoVIEW frmPesquisarProdutoVIEW = new FrmPesquisarProdutoVIEW();
        frmPesquisarProdutoVIEW.setVisible(true);
        dispose();
    }

}
