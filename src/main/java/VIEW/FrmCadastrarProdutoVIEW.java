package VIEW;

import DAO.ProdutoDAO;
import DTO.ProdutoDTO;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FrmCadastrarProdutoVIEW extends JFrame {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    public FrmCadastrarProdutoVIEW() {
        setTitle("Registrar Produtos");
        setLayout(null);
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JLabel txtExplicacao = new JLabel("Informe as seguintes informações para cadastrar o produto");
        txtExplicacao.setBounds(20, 5, 400, 20);
        txtExplicacao.setFont(new Font("Arial", Font.BOLD, 12));
        txtExplicacao.setForeground(Color.white);
        add(txtExplicacao);

        JLabel txtNome = new JLabel("Nome:");
        txtNome.setBounds(25,40,100,20);
        txtNome.setFont(new Font("Arial", Font.BOLD, 12));
        txtNome.setForeground(Color.white);
        add(txtNome);

        JTextField txtNomeCadastrar = new JTextField();
        txtNomeCadastrar.setLayout(new FlowLayout());
        txtNomeCadastrar.setBounds(25,60,100,20);
        txtNomeCadastrar.setFont(new Font("Arial", Font.BOLD, 12));
        txtNomeCadastrar.setForeground(Color.black);
        add(txtNomeCadastrar);

        JLabel txtValor = new JLabel("Valor:");
        txtValor.setBounds(25,80,100,20);
        txtValor.setFont(new Font("Arial", Font.BOLD, 12));
        txtValor.setForeground(Color.white);
        add(txtValor);

        JTextField txtValorCadastrar = new JTextField();
        txtValorCadastrar.setLayout(new FlowLayout());
        txtValorCadastrar.setBounds(25,100,100,20);
        txtValorCadastrar.setFont(new Font("Arial", Font.BOLD, 12));
        txtValorCadastrar.setForeground(Color.black);
        add(txtValorCadastrar);

        JLabel txtQuantidade = new JLabel("Quantidade:");
        txtQuantidade.setBounds(25,120,200,20);
        txtQuantidade.setFont(new Font("Arial", Font.BOLD, 12));
        txtQuantidade.setForeground(Color.white);
        add(txtQuantidade);

        JTextField txtQuantidadeCadastrar = new JTextField();
        txtQuantidadeCadastrar.setLayout(new FlowLayout());
        txtQuantidadeCadastrar.setBounds(25,140,50,20);
        txtQuantidadeCadastrar.setFont(new Font("Arial", Font.BOLD, 12));
        txtQuantidadeCadastrar.setForeground(Color.black);
        add(txtQuantidadeCadastrar);

        JButton btnCadastrarMenu = new JButton("Cadastrar");
        btnCadastrarMenu.setBounds(135, 180, 100, 30);
        btnCadastrarMenu.setForeground(Color.white);
        btnCadastrarMenu.setBackground(Color.black);
        add(btnCadastrarMenu);

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
         * Quando o botão "btnCadastrarMenu" é pressionado. Ele realiza as seguintes ações:
         * 1. Obtém informações do nome, quantidade e valor de um produto a ser cadastrado a partir dos campos de entrada de texto.
         * 2. Verifica se o campo de nome está vazio e, se estiver, exibe uma mensagem de erro e não prossegue com o cadastro.
         * 3. Se o campo de nome não estiver vazio, cria um objeto ProdutoDTO com as informações coletadas.
         * 4. Cria um objeto ProdutoDAO para realizar a operação de cadastro do produto no banco de dados.
         * 5. Exibe uma mensagem de sucesso informando que o produto foi cadastrado com êxito.
         * 6. Pergunta ao usuário se deseja cadastrar outro produto.
         * 7. Se o usuário optar por não cadastrar novamente, a janela é redirecionada para o menu principal e os recursos da janela atual são liberados.
         * 8. Trata exceções do tipo NullPointerException, se ocorrerem ao coletar informações dos campos de entrada.
         * @param e O evento de ação que acionou este ActionListener.
         */
        btnCadastrarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome, quantidade, valor;
                try {//PEGAR O TEXTO DOS JtextField
                    nome = txtNomeCadastrar.getText();
                    quantidade = txtQuantidadeCadastrar.getText();
                    valor = txtValorCadastrar.getText();
                    if(nome.isEmpty() || quantidade.isEmpty() || valor.isEmpty()) { //VERIFICAR SE EXISTE INFORMAÇÕES
                        JOptionPane.showMessageDialog(null, "Verifique se colocou as informações corretamente");
                        return;
                    }
                    ProdutoDTO produtoDTO = new ProdutoDTO();
                    produtoDTO.setNome(nome);
                    produtoDTO.setQuantidade(Integer.parseInt(quantidade));
                    produtoDTO.setValor(Double.parseDouble(valor));
                    if(produtoDAO.verificaProduto(produtoDTO.getNome())) {
                        JOptionPane.showMessageDialog(null, "Já existe produto com este nome");
                        return;
                    }
                    if(produtoDAO.cadastrarProduto(produtoDTO)) {
                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto!");
                    }
                    int opcao = JOptionPane.showOptionDialog(
                            null,
                            "Deseja cadastrar novamente?",
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
                } catch (NullPointerException a) {
                    JOptionPane.showMessageDialog(null, "Verifique se as informações foram colocadas corretamente!");
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
