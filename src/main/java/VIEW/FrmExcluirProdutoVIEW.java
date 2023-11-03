package VIEW;

import DAO.ProdutoDAO;
import DTO.ProdutoDTO;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrmExcluirProdutoVIEW extends JFrame {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    public FrmExcluirProdutoVIEW() {
        setLayout(null);
        setTitle("Excluir Produto");
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JLabel txtExplicacao = new JLabel("Informe o nome do produto que deseja excluir");
        txtExplicacao.setBounds(15, 5, 400, 20);
        txtExplicacao.setFont(new Font("Arial", Font.BOLD, 12));
        txtExplicacao.setForeground(Color.white);
        add(txtExplicacao);

        JLabel txtNome = new JLabel("Nome do produto:");
        txtNome.setBounds(25, 40, 200, 20);
        txtNome.setFont(new Font("Arial", Font.BOLD, 12));
        txtNome.setForeground(Color.white);
        add(txtNome);

        JTextField txtExcluir = new JTextField();
        txtExcluir.setLayout(new FlowLayout());
        txtExcluir.setBounds(25, 60, 200, 20);
        txtExcluir.setFont(new Font("Arial", Font.BOLD, 12));
        txtExcluir.setForeground(Color.black);
        add(txtExcluir);

        JButton btnExcluirMenu = new JButton("Excluir");
        btnExcluirMenu.setBounds(135, 150, 100, 30);
        btnExcluirMenu.setForeground(Color.white);
        btnExcluirMenu.setBackground(Color.black);
        add(btnExcluirMenu);

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

        /*
         * Quando o botão "btnExcluirMenu" é pressionado. Ele realiza as seguintes ações:
         * 1. Obtém o nome do produto a ser excluído a partir do campo de entrada de texto "txtExcluir".
         * 2. Cria um objeto ProdutoDTO com o nome do produto a ser excluído.
         * 3. Cria um objeto ProdutoDAO para realizar a operação de exclusão do produto no banco de dados.
         * 4. Exibe uma mensagem de sucesso informando que o produto foi excluído com êxito.
         * 5. Pergunta ao usuário se deseja excluir outro produto.
         * 6. Se o usuário optar por não excluir novamente, a janela é redirecionada para o menu principal e os recursos da janela atual são liberados.
         * 7. Trata exceções do tipo NullPointerException, se ocorrerem ao coletar informações dos campos de entrada.
         * @param e O evento de ação que acionou este ActionListener.
         */
        btnExcluirMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameProduct;
                try {
                    nameProduct = txtExcluir.getText();

                    if (nameProduct.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Informe algum nome!");
                        return;
                    }
                    ProdutoDTO produtoDTO = new ProdutoDTO();
                    produtoDTO.setNome(nameProduct);
                    if(!produtoDAO.verificaProduto(produtoDTO.getNome())) {
                        JOptionPane.showMessageDialog(null, "Não existe produto com este nome");
                        return;
                    }
                    if (produtoDAO.excluirProduto(produtoDTO)) {
                        JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possivel excluir o produto!");
                    }
                    int opcao = JOptionPane.showOptionDialog(
                            null,
                            "Deseja excluir novamente?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            null,
                            null
                    );
                    if (opcao == JOptionPane.NO_OPTION) {
                        FrmMainMenuVIEW frmMainMenuVIEW = new FrmMainMenuVIEW();
                        frmMainMenuVIEW.setVisible(true);
                        dispose();
                    }
                }catch (NullPointerException a) {
                    JOptionPane.showMessageDialog(null, "Verifique se as informações foram adicionadas corretamente!");
                }
            }
        });

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
}
