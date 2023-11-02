package DTO;

public class UsuarioDTO {
    private String nome_usuario;
    private String senha_usuario;
    public String getNome_usuario() {
        return this.nome_usuario;
    }
    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }
    public String getSenha_usuario() {
        return this.senha_usuario;
    }
    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

}