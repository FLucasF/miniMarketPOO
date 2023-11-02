package DAO;

import DTO.UsuarioDTO;

import java.sql.ResultSet;

public interface UsuarioDAO_Interface {
    ResultSet autenticacaoUsuario (UsuarioDTO objusuariodto);
    boolean cadastrarUsuario(UsuarioDTO objUsuarioDTO);
    boolean excluirUsuario(UsuarioDTO usuarioDTO);
}
