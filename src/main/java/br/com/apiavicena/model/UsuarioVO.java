package br.com.apiavicena.model;

import java.io.Serializable;

/**
 *
 * @author Marco e Luciane
 */
public class UsuarioVO implements Serializable{
    
    private static final long serialVersionUID = 7635818218078030057L;    
    private Integer codigoUsuario;
    private String login;
    private String senha;
    private String perfil;

    public UsuarioVO() {
    }

    public UsuarioVO(Integer codigoUsuario, String login, String senha, String perfil) {
        this.codigoUsuario = codigoUsuario;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" + "codigoUsuario=" + codigoUsuario + ", login=" + login + ", senha=" + senha + ", perfil=" + perfil + '}';
    }
}
