
package br.com.apiavicena.model;

import java.io.Serializable;

/**
 *
 * @author Marco e Luciane
 */
public class EspecializacaoVO implements Serializable{

    private static final long serialVersionUID = -1708326655850777463L;
    
    private Integer codigoEspecializacao;
    private EspecialidadeVO especialidadeVO;
    private MedicoVO medicoVO;
    private String anoEspecializacao;

    public EspecializacaoVO() {
    }

    public EspecializacaoVO(Integer codigoEspecializacao, EspecialidadeVO especialidadeVO, MedicoVO medicoVO, String anoEspecializacao) {
        this.codigoEspecializacao = codigoEspecializacao;
        this.especialidadeVO = especialidadeVO;
        this.medicoVO = medicoVO;
        this.anoEspecializacao = anoEspecializacao;
    }

    public Integer getCodigoEspecializacao() {
        return codigoEspecializacao;
    }

    public void setCodigoEspecializacao(Integer codigoEspecializacao) {
        this.codigoEspecializacao = codigoEspecializacao;
    }

    public EspecialidadeVO getEspecialidadeVO() {
        return especialidadeVO;
    }

    public void setEspecialidadeVO(EspecialidadeVO especialidadeVO) {
        this.especialidadeVO = especialidadeVO;
    }

    public MedicoVO getMedicoVO() {
        return medicoVO;
    }

    public void setMedicoVO(MedicoVO medicoVO) {
        this.medicoVO = medicoVO;
    }

    public String getAnoEspecializacao() {
        return anoEspecializacao;
    }

    public void setAnoEspecializacao(String anoEspecializacao) {
        this.anoEspecializacao = anoEspecializacao;
    }

    @Override
    public String toString() {
        return "EspecializacaoVO{" + "codigoEspecializacao=" + codigoEspecializacao + ", especialidadeVO=" + especialidadeVO + ", medicoVO=" + medicoVO + ", anoEspecializacao=" + anoEspecializacao + '}';
    }
}
