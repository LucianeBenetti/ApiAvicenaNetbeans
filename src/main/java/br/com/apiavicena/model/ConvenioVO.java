
package br.com.apiavicena.model;

import java.io.Serializable;

/**
 *
 * @author Marco e Luciane
 */
public class ConvenioVO implements Serializable{

    private static final long serialVersionUID = 5528232307395605305L;
    private Integer codigoConvenio;
    private String nomeConvenio;
    private String cnpjConvenio;
    private String valorConvenio;

    public ConvenioVO(Integer codigoConvenio, String nomeConvenio, String cnpjConvenio, String valorConvenio) {
        this.codigoConvenio = codigoConvenio;
        this.nomeConvenio = nomeConvenio;
        this.cnpjConvenio = cnpjConvenio;
        this.valorConvenio = valorConvenio;
    }

    public ConvenioVO() {
    }

    public Integer getCodigoConvenio() {
        return codigoConvenio;
    }

    public void setCodigoConvenio(Integer codigoConvenio) {
        this.codigoConvenio = codigoConvenio;
    }

    public String getNomeConvenio() {
        return nomeConvenio;
    }

    public void setNomeConvenio(String nomeConvenio) {
        this.nomeConvenio = nomeConvenio;
    }

    public String getCnpjConvenio() {
        return cnpjConvenio;
    }

    public void setCnpjConvenio(String cnpjConvenio) {
        this.cnpjConvenio = cnpjConvenio;
    }

    public String getValorConvenio() {
        return valorConvenio;
    }

    public void setValorConvenio(String valorConvenio) {
        this.valorConvenio = valorConvenio;
    }

    @Override
    public String toString() {
        return "ConvenioVO{" + "codigoConvenio=" + codigoConvenio + ", nomeConvenio=" + nomeConvenio + ", cnpjConvenio=" + cnpjConvenio + ", valor=" + valorConvenio + '}';
    }
}
