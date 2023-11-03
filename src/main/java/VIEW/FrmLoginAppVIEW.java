package VIEW;

import DAO.ProdutoDAO;
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class FrmLoginAppVIEW extends javax.swing.JFrame {
    private UsuarioDAO usuarioDAO;
    public FrmLoginAppVIEW() {
        setLayout(null);
        setTitle("Login");
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel txtLogin = new JLabel("Login:");
        txtLogin.setBounds(25, 40, 150, 20);
        txtLogin.setFont(new Font("Arial", Font.BOLD, 12));
        txtLogin.setForeground(Color.white);
        add(txtLogin);

        JTextField txtLoginUser = new JTextField();
        txtLoginUser.setLayout(new FlowLayout());
        txtLoginUser.setBounds(25, 60, 200, 20);
        txtLoginUser.setFont(new Font("Arial", Font.BOLD, 12));
        txtLoginUser.setForeground(Color.black);
        add(txtLoginUser);

        JLabel txtSenha = new JLabel("Password:");
        txtSenha.setBounds(25, 80, 200, 20);
        txtSenha.setFont(new Font("Arial", Font.BOLD, 12));
        txtSenha.setForeground(Color.white);
        add(txtSenha);

        JPasswordField txtSenhaUser = new JPasswordField();
        txtSenhaUser.setLayout(new FlowLayout());
        txtSenhaUser.setBounds(25, 100, 200, 20);
        txtSenhaUser.setFont(new Font("Arial", Font.BOLD, 12));
        txtSenhaUser.setForeground(Color.black);
        add(txtSenhaUser);

        JButton btnLogar = new JButton("Entrar");
        btnLogar.setBounds(135, 150, 100, 30);
        btnLogar.setForeground(Color.white);
        btnLogar.setBackground(Color.black);
        add(btnLogar);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(135, 190, 100, 30);
        btnRegistrar.setForeground(Color.white);
        btnRegistrar.setBackground(Color.black);
        add(btnRegistrar);

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
                    "backGroundLogin.png"));

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
         * Quando o botão "btnLogar" é pressionado. Ele realiza as seguintes ações:
         * 1. Obtém o nome de usuário e senha informados nos campos de entrada de texto.
         * 2. Cria um objeto UsuarioDTO com o nome de usuário e senha informados.
         * 3. Cria um objeto UsuarioDAO para autenticar o usuário chamando o método autenticacaoUsuario.
         * 4. Verifica se a autenticação foi bem-sucedida consultando o resultado do ResultSet.
         * 5. Se a autenticação for bem-sucedida, redireciona para a janela do menu principal (frmMainMenuVIEW) e fecha a janela atual.
         * 6. Caso contrário, exibe uma mensagem de erro informando que o usuário ou senha é inválido.
         * 7. Trata exceções do tipo SQLException, exibindo uma mensagem de erro em caso de problemas com a operação de autenticação.
         * @param e O evento de ação que acionou este ActionListener.
         */
        btnLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome_usuario;
                    String senha_usuario;
                    nome_usuario = txtLoginUser.getText();
                    senha_usuario = txtSenhaUser.getText();
                    UsuarioDTO objusuariodto = new UsuarioDTO();
                    objusuariodto.setNome_usuario(nome_usuario);
                    objusuariodto.setSenha_usuario(senha_usuario);
                    usuarioDAO = new UsuarioDAO();
                    ResultSet rsusuariodao = usuarioDAO.autenticacaoUsuario(objusuariodto);
                    if (rsusuariodao.next()) { //VERIFICAÇÃO
                        FrmMainMenuVIEW frmMainMenuVIEW = new FrmMainMenuVIEW();
                        frmMainMenuVIEW.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou Senha Inválida.");
                    }
                } catch (SQLException a) {
                    JOptionPane.showMessageDialog(null, "FrmLoginVIEWideia" + a);
                }
            }
        });

        /*
         * Quando o botão "btnRegistrar" é pressionado. Ele realiza as seguintes ações:
         * 1. Cria uma nova instância da classe FrmRegistrarUsuarioVIEW para exibir a janela de registro de usuário.
         * 2. Fecha a janela atual (dispose).
         * @param e O evento de ação que acionou este ActionListener.
         */
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmRegistrarUsuarioVIEW frmRegistrarUsuarioVIEW = new FrmRegistrarUsuarioVIEW();
                dispose();
            }
        });

        /*
         * Este método é acionado quando o usuário tenta fechar a janela.
         * Realiza a seguinte ação:
         * 1. finaliza o aplicativo.
         */
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        });
    }

}