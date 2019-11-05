package br.com.apiavicena.server;

import br.com.apiavicena.dao.ConsultaDAO;
import br.com.apiavicena.dao.MedicoDAO;
import br.com.apiavicena.factory.Conexao;
import br.com.apiavicena.model.ConsultaVO;
import br.com.apiavicena.model.MedicoVO;
import br.com.apiavicena.model.PacienteVO;
import com.google.gson.Gson;
import java.util.ArrayList;
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
@Path("/consulta")
public class ConsultaServer {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConsultaVO> getConsulta() {
        ConsultaDAO consultaDAO = new ConsultaDAO();
        return consultaDAO.listar();
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("{codigoConsulta}")
//    public ConsultaVO getConvenioVO(@PathParam("codigoConsulta") Integer id) {
//        ConsultaDAO consultaDAO= new ConsultaDAO();
//        ConsultaVO consultaVO = consultaDAO.lerPorId(id);
//        Conexao.closeConnection();
//        return consultaVO;
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{emailMedico}")
    public List<ConsultaVO> getConvenioVO(@PathParam("emailMedico") String emailMedico) {
        ConsultaDAO consultaDAO = new ConsultaDAO();
        List<ConsultaVO> consultasVO = consultaDAO.listar();
        List<ConsultaVO> listaConsultasVO = new ArrayList();

        for (int i = 0; i < consultasVO.size(); i++) {
            if (emailMedico.equals(consultasVO.get(i).getEspecializacaoVO().getMedicoVO().getEmailMedico())) {
                ConsultaVO consultaVO = new ConsultaVO();
                consultaVO.setAtencaoEspecial(consultasVO.get(i).getAtencaoEspecial());
                consultaVO.setDataConsulta(consultasVO.get(i).getDataConsulta());
                consultaVO.setHorarioConsulta(consultasVO.get(i).getHorarioConsulta());
                PacienteVO pacienteVO = new PacienteVO();
                pacienteVO.setNomePaciente(consultasVO.get(i).getPacienteVO().getNomePaciente());
                consultaVO.setPacienteVO(pacienteVO);
                listaConsultasVO.add(consultaVO);
            }
        }

        Conexao.closeConnection();
        return listaConsultasVO;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Integer cadastrar(@FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
        ConsultaVO consultaVO = gson.fromJson(dadosJSON, ConsultaVO.class);
        ConsultaDAO consultaDAO = new ConsultaDAO();
        int lastId = consultaDAO.cadastrar(consultaVO);
        Conexao.closeConnection();
        return lastId;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("{codigoConsulta}")
    public Boolean alterar(@PathParam("codigoConsulta") Integer id, @FormParam("dado") String dadosJSON) {
        Gson gson = new Gson();
        ConsultaVO consultaVO = gson.fromJson(dadosJSON, ConsultaVO.class);

        if (id == consultaVO.getCodigoConsulta() || consultaVO.getCodigoConsulta() == null) {
            consultaVO.setCodigoConsulta(id);
        } else {
            return false;
        }

        ConsultaDAO consultaDAO = new ConsultaDAO();
        Boolean res = consultaDAO.alterar(consultaVO);
        Conexao.closeConnection();
        return res;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigoConsulta}")
    public Boolean deletar(@PathParam("codigoConsulta") Integer id) {
        ConsultaDAO consultaDAO = new ConsultaDAO();
        Boolean res = consultaDAO.excluir(id);
        Conexao.closeConnection();
        return res;
    }

}
