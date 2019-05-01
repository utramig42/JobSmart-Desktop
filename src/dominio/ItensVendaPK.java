/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author caioa
 */
@Embeddable
public class ItensVendaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Id_itens_venda")
    private int iditensvenda;
    @Basic(optional = false)
    @Column(name = "id_est")
    private int idEst;
    @Basic(optional = false)
    @Column(name = "id_venda")
    private int idVenda;
    @Basic(optional = false)
    @Column(name = "id_prod")
    private int idProd;
    @Basic(optional = false)
    @Column(name = "id_for")
    private int idFor;

    public ItensVendaPK() {
    }

    public ItensVendaPK(int iditensvenda, int idEst, int idVenda, int idProd, int idFor) {
        this.iditensvenda = iditensvenda;
        this.idEst = idEst;
        this.idVenda = idVenda;
        this.idProd = idProd;
        this.idFor = idFor;
    }

    public int getIditensvenda() {
        return iditensvenda;
    }

    public void setIditensvenda(int iditensvenda) {
        this.iditensvenda = iditensvenda;
    }

    public int getIdEst() {
        return idEst;
    }

    public void setIdEst(int idEst) {
        this.idEst = idEst;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getIdFor() {
        return idFor;
    }

    public void setIdFor(int idFor) {
        this.idFor = idFor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iditensvenda;
        hash += (int) idEst;
        hash += (int) idVenda;
        hash += (int) idProd;
        hash += (int) idFor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensVendaPK)) {
            return false;
        }
        ItensVendaPK other = (ItensVendaPK) object;
        if (this.iditensvenda != other.iditensvenda) {
            return false;
        }
        if (this.idEst != other.idEst) {
            return false;
        }
        if (this.idVenda != other.idVenda) {
            return false;
        }
        if (this.idProd != other.idProd) {
            return false;
        }
        if (this.idFor != other.idFor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.ItensVendaPK[ iditensvenda=" + iditensvenda + ", idEst=" + idEst + ", idVenda=" + idVenda + ", idProd=" + idProd + ", idFor=" + idFor + " ]";
    }
    
}
