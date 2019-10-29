package br.com.apiavicena.model;

import java.io.Serializable;

/**
 *
 * @author Marco e Luciane
 */
public class EspecialidadeVO implements Serializable {

    private static final long serialVersionUID = 2644409752911462587L;
    
    private Integer codigoEspecialidade;
    private String nomeEspecialidade;
    private String instituicao;

    public EspecialidadeVO() {
    }

    public EspecialidadeVO(Integer codigoEspecialidade, String nomeEspecialidade, String instituicao) {
        this.codigoEspecialidade = codigoEspecialidade;
        this.nomeEspecialidade = nomeEspecialidade;
        this.instituicao = instituicao;
    }

    public Integer getCodigoEspecialidade() {
        return codigoEspecialidade;
    }

    public void setCodigoEspecialidade(Integer codigoEspecialidade) {
        this.codigoEspecialidade = codigoEspecialidade;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public String toString() {
        return "EspecialidadeVO{" + "codigoEspecialidade=" + codigoEspecialidade + ", nomeEspecialidade=" + nomeEspecialidade + ", instituicao=" + instituicao + '}';
    }
}
