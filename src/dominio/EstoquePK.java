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
public class EstoquePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_est")
    private int idEst;
    @Basic(optional = false)
    @Column(name = "id_for")
    private int idFor;
    @Basic(optional = false)
    @Column(name = "id_prod")
    private int idProd;

    public EstoquePK() {
    }

    public EstoquePK(int idEst, int idFor, int idProd) {
        this.idEst = idEst;
        this.idFor = idFor;
        this.idProd = idProd;
    }

    public int getIdEst() {
        return idEst;
    }

    public void setIdEst(int idEst) {
        this.idEst = idEst;
    }

    public int getIdFor() {
        return idFor;
    }

    public void setIdFor(int idFor) {
        this.idFor = idFor;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEst;
        hash += (int) idFor;
        hash += (int) idProd;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstoquePK)) {
            return false;
        }
        EstoquePK other = (EstoquePK) object;
        if (this.idEst != other.idEst) {
            return false;
        }
        if (this.idFor != other.idFor) {
            return false;
        }
        if (this.idProd != other.idProd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.EstoquePK[ idEst=" + idEst + ", idFor=" + idFor + ", idProd=" + idProd + " ]";
    }
    
}
