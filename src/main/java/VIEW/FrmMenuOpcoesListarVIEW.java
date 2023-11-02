package VIEW;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrmMenuOpcoesListarVIEW extends JFrame {
    private FrmListarProdutosVIEW frmListarProdutosVIEW;
    public FrmMenuOpcoesListarVIEW() {
        setLayout(null);
        setTitle("Listar Produtos");
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JButton btnListarProduto = new JButton("Ordenar Produtos");
        btnListarProduto.setBounds(40, 100, 300, 30);
        btnListarProduto.setForeground(Color.white);
        btnListarProduto.setBackground(Color.black);
        add(btnListarProduto);

        JButton btnListarProdutoOrdemAlfabetica = new JButton("Ordem Alfabetica pelo nome");
        btnListarProdutoOrdemAlfabetica.setBounds(40, 140, 300, 30);
        btnListarProdutoOrdemAlfabetica.setForeground(Color.white);
        btnListarProdutoOrdemAlfabetica.setBackground(Color.black);
        add(btnListarProdutoOrdemAlfabetica);

        JButton btnListarProdutoValorMaiorMenor = new JButton("Ordem Crescente por valor");
        btnListarProdutoValorMaiorMenor.setBounds(40, 180, 300, 30);
        btnListarProdutoValorMaiorMenor.setForeground(Color.white);
        btnListarProdutoValorMaiorMenor.setBackground(Color.black);
        add(btnListarProdutoValorMaiorMenor);

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
                    "backGroundFuncionalidades.png"));

            instImage = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            JLabel image = new JLabel();
            ImageIcon imageIcon = new ImageIcon(instImage);
            image.setBounds(-14, 0, this.getWidth(), this.getHeight());
            image.setIcon(imageIcon);
            this.add(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        btnListarProduto.addActionListener(this::btnListarProduct);
        btnListarProdutoOrdemAlfabetica.addActionListener(this::btnListarOrdemAlfabetica);
        btnListarProdutoValorMaiorMenor.addActionListener(this::btnListarValorMaiorMenor);

        /*
         * Este trecho de código realiza as seguintes ações:
         * 1. Configura um ouvinte de eventos na janela atual para lidar com o evento de fechamento da janela.
         * 2. Quando o usuário tenta fechar a janela, cria uma nova instância da classe "FrmMainMenuVIEW", que é uma janela que representa o menu principal da aplicação.
         * 3. Torna a janela "FrmMainMenuVIEW" visível, exibindo-a ao usuário.
         * 4. Chama o método "dispose()" na janela atual, que libera os recursos associados à janela e a fecha de forma controlada.
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
     * Este método realiza as seguintes ações:
     * 1. Fecha a janela atual (dispose).
     * 2. Chama o método "listarProduto" na instância de FrmListarProdutosVIEW para listar produtos.
     * @param e O evento de ação que acionou a ação do botão.
     */
    private void btnListarProduct(ActionEvent e) {
        dispose();
        FrmListarProdutosVIEW frmListarProdutosVIEW = new FrmListarProdutosVIEW();
        frmListarProdutosVIEW.listarProduto();
    }

    /*
     * Este método realiza as seguintes ações:
     * 1. Fecha a janela atual (dispose).
     * 2. Chama o método "listarProdutosAlfabetico" na instância de FrmListarProdutosVIEW para listar produtos em ordem alfabética.
     * @param e O evento de ação que acionou a ação do botão.
     */
    private void btnListarOrdemAlfabetica(ActionEvent e) {
        dispose();
        FrmListarProdutosVIEW frmListarProdutosVIEW = new FrmListarProdutosVIEW();
        frmListarProdutosVIEW.listarProdutosAlfabetico();
    }
    /*
     * Este método realiza as seguintes ações:
     * 1. Fecha a janela atual (dispose).
     * 2. Chama o método "listarProdutoValorMenor" na instância de FrmListarProdutosVIEW para listar produtos em ordem crescente de valor.
     * @param e O evento de ação que acionou a ação do botão.
     */
    private void btnListarValorMaiorMenor(ActionEvent e) {
        dispose();
        FrmListarProdutosVIEW frmListarProdutosVIEW = new FrmListarProdutosVIEW();
        frmListarProdutosVIEW.listarProdutoValorMenor();

    }


}
