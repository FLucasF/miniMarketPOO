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

public class FrmAtualizarInformacaoProdutoVIEW extends JFrame {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    public FrmAtualizarInformacaoProdutoVIEW() {
        setTitle("Atualizar Produto");
        setLayout(null);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JLabel txtExplicacao = new JLabel("Informe as informações que deseja modificar");
        txtExplicacao.setBounds(25, 5, 400, 20);
        txtExplicacao.setFont(new Font("Arial", Font.BOLD, 12));
        txtExplicacao.setForeground(Color.white);
        add(txtExplicacao);

        JLabel txtNome = new JLabel("Nome:");
        txtNome.setBounds(25, 40, 100, 20);
        txtNome.setFont(new Font("Arial", Font.BOLD, 12));
        txtNome.setForeground(Color.white);
        add(txtNome);

        JTextField txtNomeAtualizar = new JTextField();
        txtNomeAtualizar.setLayout(new FlowLayout());
        txtNomeAtualizar.setBounds(25, 60, 100, 20);
        txtNomeAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        txtNomeAtualizar.setForeground(Color.black);
        add(txtNomeAtualizar);

        JLabel txtValor = new JLabel("Valor:");
        txtValor.setBounds(25, 80, 100, 20);
        txtValor.setFont(new Font("Arial", Font.BOLD, 12));
        txtValor.setForeground(Color.white);
        add(txtValor);

        JTextField txtValorAtualizar = new JTextField();
        txtValorAtualizar.setLayout(new FlowLayout());
        txtValorAtualizar.setBounds(25, 100, 100, 20);
        txtValorAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        txtValorAtualizar.setForeground(Color.black);
        add(txtValorAtualizar);

        JLabel txtQuantidade = new JLabel("Quantidade:");
        txtQuantidade.setBounds(25, 120, 200, 20);
        txtQuantidade.setFont(new Font("Arial", Font.BOLD, 12));
        txtQuantidade.setForeground(Color.white);
        add(txtQuantidade);

        JTextField txtQuantidadeAtualizar = new JTextField();
        txtQuantidadeAtualizar.setLayout(new FlowLayout());
        txtQuantidadeAtualizar.setBounds(25, 140, 50, 20);
        txtQuantidadeAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        txtQuantidadeAtualizar.setForeground(Color.black);
        add(txtQuantidadeAtualizar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(135, 200, 100, 30);
        btnAtualizar.setForeground(Color.white);
        btnAtualizar.setBackground(Color.black);
        add(btnAtualizar);

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
         * Método para tratar o evento de clicar no botão de atualizar.
         * Quando o botão "btnAtualizar" é pressionado. Ele realiza as seguintes ações:
         * 1. Obtém as informações de nome, valor e quantidade a partir dos campos de entrada de texto.
         * 2. Cria um objeto ProdutoDTO com as informações coletadas.
         * 3. Tenta atualizar o produto no banco de dados usando um objeto ProdutoDAO.
         * 4. Exibe uma mensagem de sucesso para o usuário.
         * 5. Pergunta ao usuário se deseja atualizar novamente.
         * 6. Se o usuário optar por não atualizar novamente, a janela é redirecionada para o menu principal.
         * 7. Trata exceções do tipo NullPointerException, se ocorrerem ao coletar informações dos campos de entrada.
         * @param e O evento de ação que acionou este ActionListener.
         */
         btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome, valor, quantidade;

                try {
                    nome = txtNomeAtualizar.getText();
                    valor = txtValorAtualizar.getText();
                    quantidade = txtQuantidadeAtualizar.getText();

                    if(nome.isEmpty() || quantidade.isEmpty() || valor.isEmpty()) { //VERIFICAR SE EXISTE INFORMAÇÕES
                        JOptionPane.showMessageDialog(null, "Verifique se colocou as informações corretamente");
                        return;
                    }


                    ProdutoDTO produtoDTO = new ProdutoDTO();
                    produtoDTO.setNome(nome);
                    produtoDTO.setValor(Double.parseDouble(valor));
                    produtoDTO.setQuantidade(Integer.parseInt(quantidade));

                    if(!produtoDAO.verificaProduto(produtoDTO.getNome())) { //VERIFICA SE EXISTE O PRODUTO
                        JOptionPane.showMessageDialog(null, "Não existe produto com este nome");
                        return;
                    }

                    boolean resposta = produtoDAO.alterarProduto(produtoDTO);

                    JOptionPane.showMessageDialog(null, "Produto atualizar com sucesso!");

                    int opcao = JOptionPane.showOptionDialog(
                            null,
                            "Deseja atualizar novamente?",
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
