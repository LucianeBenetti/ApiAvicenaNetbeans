package br.com.apiavicena.server;

import br.com.apiavicena.dao.ConsultaDAO;
import br.com.apiavicena.dao.MedicoDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.ConsultaVO;
import br.com.apiavicena.model.MedicoVO;
import java.util.List;
import com.google.gson.Gson;
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
@Path("/medico")
public class MedicoServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MedicoVO> getMedicoVO() {
        MedicoDAO medicoDao = new MedicoDAO();
        return medicoDao.listarTodosOsMedicos();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{emailMedico}")
    public MedicoVO getMedicoVO(@PathParam("emailMedico") String emailMedico) {
        MedicoDAO medicoDao = new MedicoDAO();
        MedicoVO medicoVO = medicoDao.pesquisarMedicoVOPorEmail(emailMedico);
        Conexao.closeConnection();
        return medicoVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Integer cadastrar(@FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
        MedicoVO medicoVO = gson.fromJson(dadosJSON, MedicoVO.class);
        MedicoDAO medicoDao = new MedicoDAO();
        int lastId = medicoDao.inserir(medicoVO);
        Conexao.closeConnection();
        return lastId;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{codigoMedico}")
    public Boolean alterar(@PathParam("codigoMedico") Integer id, @FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
        MedicoVO medicoVO = gson.fromJson(dadosJSON, MedicoVO.class);

      if (id == medicoVO.getCodigoMedico() || medicoVO.getCodigoMedico() == null) {
            medicoVO.setCodigoMedico(id);
        } else {
            return false;
        }

        MedicoDAO medicoDao = new MedicoDAO();
        Boolean res = medicoDao.alterar(medicoVO);
        Conexao.closeConnection();
        return res;
    }

        @DELETE
        @Produces(MediaType.APPLICATION_JSON)
        @Path("{codigoMedico}")
        public Boolean deletar
        (@PathParam("codigoMedico")
        Integer id
        
            ) {
        MedicoDAO medicoDao = new MedicoDAO();
            Boolean res = medicoDao.excluir(id);
            Conexao.closeConnection();
            return res;
        }

    }
