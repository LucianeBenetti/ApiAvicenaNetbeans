
package br.com.apiavicena.server;

import br.com.apiavicena.dao.ConvenioDAO;
import br.com.apiavicena.dao.PacienteDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.ConvenioVO;
import br.com.apiavicena.model.PacienteVO;
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
@Path("/convenio")
public class ConvenioServer {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConvenioVO> getConvenioVO() {
        ConvenioDAO convenioDao = new ConvenioDAO();
        return convenioDao.listar();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigoConvenio}")
    public ConvenioVO getConvenioVO(@PathParam("codigoConvenio") Integer id) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        ConvenioVO convenioVO = convenioDao.lerPorId(id);
        Conexao.closeConnection();
        return convenioVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Integer cadastrar(@FormParam("dado") String dadosJSON) {
       Gson gson = new Gson();
       ConvenioVO convenioVO = gson.fromJson(dadosJSON, ConvenioVO.class);
       ConvenioDAO convenioDao = new ConvenioDAO();
       int lastId = convenioDao.inserir(convenioVO);
       Conexao.closeConnection();
       return lastId;
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{codigoConvenio}")
    public Boolean alterar(@PathParam("codigoConvenio") Integer id, @FormParam("dado") String dadosJSON) {
       Gson gson = new Gson();
       ConvenioVO convenioVO = gson.fromJson(dadosJSON, ConvenioVO.class);

        if (id == convenioVO.getCodigoConvenio() || convenioVO.getCodigoConvenio() == null) {
            convenioVO.setCodigoConvenio(id);
        } else {
            return false;
        }

        ConvenioDAO convenioDao = new ConvenioDAO();;
        Boolean res = convenioDao.alterar(convenioVO);
        Conexao.closeConnection();
        return res;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigoConvenio}")
    public Boolean deletar(@PathParam("codigoConvenio") Integer codigoConvenio) {
        ConvenioDAO convenioDao = new ConvenioDAO();
        Boolean res = convenioDao.excluir(codigoConvenio);
        Conexao.closeConnection();
        return res;
    }
    
}
