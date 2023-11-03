package VIEW;

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

public class FrmRegistrarUsuarioVIEW extends JFrame {
    private FrmLoginAppVIEW frmLoginAppVIEW;    
    public FrmRegistrarUsuarioVIEW () {
        setLayout(null);
        setTitle("Cadastro");
        setResizable(false);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        JLabel txtExplicacao = new JLabel("Informe as seguintes informações para cadastrar o usuário");
        txtExplicacao.setBounds(20, 5, 400, 20);
        txtExplicacao.setFont(new Font("Arial", Font.BOLD, 12));
        txtExplicacao.setForeground(Color.white);
        add(txtExplicacao);

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

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(135, 150, 100, 30);
        btnRegistrar.setForeground(Color.white);
        btnRegistrar.setBackground(Color.black);
        add(btnRegistrar);

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
         * Quando o botão "btnRegistrar" é pressionado. Ele realiza as seguintes ações:
         * 1. Obtém o nome de usuário e senha informados nos campos de entrada de texto.
         * 2. Cria um objeto UsuarioDTO com o nome de usuário e senha informados.
         * 3. Cria um objeto UsuarioDAO para cadastrar o usuário chamando o método cadastrarUsuario.
         * 4. Exibe uma mensagem de sucesso informando que o usuário foi cadastrado com êxito.
         * 5. Cria uma nova instância da classe FrmLoginAppVIEW para exibir a janela de login.
         * 6. Fecha a janela atual (dispose).
         * 7. Trata exceções do tipo NullPointerException, se ocorrerem ao coletar informações dos campos de entrada.
         * @param e O evento de ação que acionou este ActionListener.
         */
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome, password;
                try {
                    nome = txtLoginUser.getText();
                    password = txtSenhaUser.getText();
                    if(nome.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Digite todas as informações pedidas!");
                        return;
                    }
                    UsuarioDTO usuarioDTO = new UsuarioDTO();
                    usuarioDTO.setNome_usuario(nome);
                    usuarioDTO.setSenha_usuario(password);
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    if(usuarioDAO.cadastrarUsuario(usuarioDTO)) {
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Houve uma falha no cadastro!");
                    }
                    frmLoginAppVIEW = new FrmLoginAppVIEW();
                    dispose();
                } catch (NullPointerException a) {
                    JOptionPane.showMessageDialog(null, "Verifique se as informações foram colocadas corretamente!");
                }
            }
        });

        /*
         * Este método é acionado quando o usuário tenta fechar a janela.
         * Realiza as seguintes ações:
         * 1. Cria uma nova instância da classe FrmLoginAppVIEW, que provavelmente representa a tela de login.
         * 2. Torna a nova janela (FrmLoginAppVIEW) visível, exibindo-a ao usuário.
         * 3. Chama o método "dispose()" na janela atual, o que libera os recursos associados à janela e a fecha de forma controlada.
         */
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                frmLoginAppVIEW = new FrmLoginAppVIEW();
                frmLoginAppVIEW.setVisible(true);
                dispose();
            }
        });
    }
}
