
package br.com.apiavicena.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marco e Luciane
 */
public class ConsultaVO implements Serializable{

    private static final long serialVersionUID = -7647978359300743090L;
    
    private Integer codigoConsulta;
    private EspecializacaoVO especializacaoVO;
    private PacienteVO pacienteVO;
    private ConvenioVO convenioVO;
    private Date dataConsulta;
    private String atencaoEspecial;
    private String horarioConsulta;
    private String valorConsulta;

    public ConsultaVO() {
    }

    public ConsultaVO(Integer codigoConsulta, EspecializacaoVO especializacaoVO, PacienteVO pacienteVO, ConvenioVO convenioVO, Date dataConsulta, String atencaoEspecial, String horarioConsulta, String valorConsulta) {
        this.codigoConsulta = codigoConsulta;
        this.especializacaoVO = especializacaoVO;
        this.pacienteVO = pacienteVO;
        this.convenioVO = convenioVO;
        this.dataConsulta = dataConsulta;
        this.atencaoEspecial = atencaoEspecial;
        this.horarioConsulta = horarioConsulta;
        this.valorConsulta = valorConsulta;
    }

    public String getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(String valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

    
    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Integer getCodigoConsulta() {
        return codigoConsulta;
    }

    public void setCodigoConsulta(Integer codigoConsulta) {
        this.codigoConsulta = codigoConsulta;
    }

    public EspecializacaoVO getEspecializacaoVO() {
        return especializacaoVO;
    }

    public void setEspecializacaoVO(EspecializacaoVO especializacaoVO) {
        this.especializacaoVO = especializacaoVO;
    }

    public PacienteVO getPacienteVO() {
        return pacienteVO;
    }

    public void setPacienteVO(PacienteVO pacienteVO) {
        this.pacienteVO = pacienteVO;
    }

    public ConvenioVO getConvenioVO() {
        return convenioVO;
    }

    public void setConvenioVO(ConvenioVO convenioVO) {
        this.convenioVO = convenioVO;
    }

    public String getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(String horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public String getAtencaoEspecial() {
        return atencaoEspecial;
    }

    public void setAtencaoEspecial(String atencaoEspecial) {
        this.atencaoEspecial = atencaoEspecial;
    }

    @Override
    public String toString() {
        
             SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                 
            return "ConsultaVO{" + "codigoConsulta=" + codigoConsulta + ", especializacaoVO=" + especializacaoVO + ", pacienteVO=" + pacienteVO + ", convenioVO=" + convenioVO + ", dataConsulta=" +   sdf.format(dataConsulta) + ", horarioConsulta=" + horarioConsulta + ", atencaoEspecial=" + atencaoEspecial + '}';
    }

}
