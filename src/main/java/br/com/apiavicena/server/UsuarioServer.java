package br.com.apiavicena.server;

import br.com.apiavicena.dao.UsuarioDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.UsuarioVO;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Marco e Luciane
 */
@Path("/usuario")
public class UsuarioServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioVO> getUsuarioVO() {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        return usuarioDao.listarUsuario();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigoUsuario}")
    public UsuarioVO getUsuarioVO(@PathParam("codigoUsuario") Integer codigoUsuario) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        UsuarioVO usuarioVO = usuarioDao.lerPorId(codigoUsuario);
        Conexao.closeConnection();
        return usuarioVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public UsuarioVO autenticar(@FormParam("dado") String dadosJSON) {

       Gson gson = new Gson();
       UsuarioVO usuarioVO = gson.fromJson(dadosJSON, UsuarioVO.class);
       UsuarioDAO usuarioDao = new UsuarioDAO();
       UsuarioVO usuarioRetornado =  usuarioDao.pesquisarUsuarioVO(usuarioVO.getLogin(), usuarioVO.getSenha());
       if(usuarioRetornado==null){
            return new UsuarioVO();
       }else{
            if (usuarioRetornado.getPerfil().equals("medico")) {
                Conexao.closeConnection();
                 return usuarioRetornado;
             } else {
                 Conexao.closeConnection();
                 return new UsuarioVO();
            }
       }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{codigoUsuario}")
    public Boolean alterar(@PathParam("codigoUsuario") Integer id, @FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
        UsuarioVO usuarioVO = gson.fromJson(dadosJSON, UsuarioVO.class);

        if (id == usuarioVO.getCodigoUsuario() || usuarioVO.getCodigoUsuario() == null) {
            usuarioVO.setCodigoUsuario(id);
        } else {
            return false;
        }

        UsuarioDAO usuarioDao = new UsuarioDAO();
        Boolean res = usuarioDao.alterar(usuarioVO);
        Conexao.closeConnection();
        return res;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigoUsuario}")
    public Boolean deletar(@PathParam("codigoUsuario") Integer codigoUsuario) {
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Boolean res = usuarioDao.excluir(codigoUsuario);
        Conexao.closeConnection();
        return res;
    }
}
