/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caioa
 */
@Entity
@Table(name = "itens_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItensVenda.findAll", query = "SELECT i FROM ItensVenda i"),
    @NamedQuery(name = "ItensVenda.findByIditensvenda", query = "SELECT i FROM ItensVenda i WHERE i.itensVendaPK.iditensvenda = :iditensvenda"),
    @NamedQuery(name = "ItensVenda.findByIdEst", query = "SELECT i FROM ItensVenda i WHERE i.itensVendaPK.idEst = :idEst"),
    @NamedQuery(name = "ItensVenda.findByIdVenda", query = "SELECT i FROM ItensVenda i WHERE i.itensVendaPK.idVenda = :idVenda"),
    @NamedQuery(name = "ItensVenda.findByIdProd", query = "SELECT i FROM ItensVenda i WHERE i.itensVendaPK.idProd = :idProd"),
    @NamedQuery(name = "ItensVenda.findByIdFor", query = "SELECT i FROM ItensVenda i WHERE i.itensVendaPK.idFor = :idFor")})
public class ItensVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItensVendaPK itensVendaPK;
    @JoinColumn(name = "id_venda", referencedColumnName = "id_venda", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Venda venda;
    @JoinColumns({
        @JoinColumn(name = "id_est", referencedColumnName = "id_est", insertable = false, updatable = false),
        @JoinColumn(name = "id_for", referencedColumnName = "id_for", insertable = false, updatable = false),
        @JoinColumn(name = "id_prod", referencedColumnName = "id_prod", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Estoque estoque;

    public ItensVenda() {
    }

    public ItensVenda(ItensVendaPK itensVendaPK) {
        this.itensVendaPK = itensVendaPK;
    }

    public ItensVenda(int iditensvenda, int idEst, int idVenda, int idProd, int idFor) {
        this.itensVendaPK = new ItensVendaPK(iditensvenda, idEst, idVenda, idProd, idFor);
    }

    public ItensVendaPK getItensVendaPK() {
        return itensVendaPK;
    }

    public void setItensVendaPK(ItensVendaPK itensVendaPK) {
        this.itensVendaPK = itensVendaPK;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itensVendaPK != null ? itensVendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensVenda)) {
            return false;
        }
        ItensVenda other = (ItensVenda) object;
        if ((this.itensVendaPK == null && other.itensVendaPK != null) || (this.itensVendaPK != null && !this.itensVendaPK.equals(other.itensVendaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.ItensVenda[ itensVendaPK=" + itensVendaPK + " ]";
    }
    
}
