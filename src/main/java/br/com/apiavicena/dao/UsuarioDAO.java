package br.com.apiavicena.dao;

import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.UsuarioVO;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Marco e Luciane
 */
public class UsuarioDAO {

    public Integer inserir(UsuarioVO usuarioVO) {
        int novoId = 0;

        String sql = "INSERT INTO usuario (login, senha, perfil) VALUES (?,?,?)";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);

        try {
            prepStmt.setString(1, usuarioVO.getLogin());
            prepStmt.setString(2, usuarioVO.getSenha());
            prepStmt.setString(3, usuarioVO.getPerfil());

            prepStmt.executeUpdate();

            ResultSet generatedKeys = prepStmt.getGeneratedKeys();

            if (generatedKeys.next()) {
                novoId = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Cadastro de Usuário! Causa: \n: " + e.getMessage());

        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return novoId;

    }

    public UsuarioVO lerPorId(Integer codigoUsuario) {
        String query = "SELECT * from usuario " + " where codigoUsuario = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        UsuarioVO usuario = null;

        try {
            prepStmt.setInt(1, codigoUsuario);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                usuario = new UsuarioVO();
                usuario.setCodigoUsuario(result.getInt("codigoUsuario"));
                usuario.setLogin(result.getString("login"));
                usuario.setSenha(result.getString("senha"));
                usuario.setPerfil(result.getString("perfil"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a Query de Consulta de Usuário!Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closeStatement(conn);
            Conexao.closeConnection();
        }
        return usuario;
    }

    public List<UsuarioVO> listarUsuario() {

        ArrayList<UsuarioVO> listaUsuarios = new ArrayList<UsuarioVO>();

        String query = "select * from usuario";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        try {
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                UsuarioVO usuarioVO = new UsuarioVO();

                usuarioVO.setCodigoUsuario(result.getInt(1));
                usuarioVO.setLogin(result.getString(2));
                usuarioVO.setSenha(result.getString(3));
                usuarioVO.setPerfil(result.getString(4));

                listaUsuarios.add(usuarioVO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public Boolean alterar(UsuarioVO usuarioVO) {

        boolean sucessoAtualizar = false;

        String query = "UPDATE usuario SET login=?, perfil=? " + " where codigoUsuario = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {

            prepStmt.setString(1, usuarioVO.getLogin());
            prepStmt.setString(2, usuarioVO.getPerfil());
            prepStmt.setInt(3, usuarioVO.getCodigoUsuario());

            int codigoRetorno = prepStmt.executeUpdate();

            if (codigoRetorno == 1) {
                sucessoAtualizar = true;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao executar Query de Atualização do Convenio!Causa: \n: " + ex.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoAtualizar;
    }

    public Boolean excluir(Integer codigoUsuario) {
        boolean sucessoDelete = false;

        String query = "DELETE from usuario where codigoUsuario = ? ";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);

        try {
            prepStmt.setInt(1, codigoUsuario);
            int codigoRetorno = prepStmt.executeUpdate();
            if (codigoRetorno == 1) {
                sucessoDelete = true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar Query de Exclusão do Convênio! Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closePreparedStatement(prepStmt);
            Conexao.closeConnection();
        }
        return sucessoDelete;
    }
    
    private String MD5(String senha) {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(senha.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(digest.digest());
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
        }
        return senha;

    }

    public UsuarioVO pesquisarUsuarioVO(String login, String senha) {
        
       
        String query = "select * from usuario where login = ? and senha = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement prepStmt = Conexao.getPreparedStatement(conn, query);
        UsuarioVO usuario = null;
        
        String senhaMD5 = MD5(senha);
        
        try {
            prepStmt.setString(1, login);
            prepStmt.setString(2, senhaMD5);
            ResultSet result = prepStmt.executeQuery();

            while (result.next()) {
                usuario = new UsuarioVO();
                usuario.setCodigoUsuario(result.getInt("codigoUsuario"));
                usuario.setLogin(result.getString("login"));
                usuario.setSenha(result.getString("senha"));
                usuario.setPerfil(result.getString("perfil"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao executar a Query de Consulta de Usuário!Causa: \n: " + e.getMessage());
        } finally {
            Conexao.closeStatement(conn);
            Conexao.closeConnection();
        }
        return usuario;
    }
}
