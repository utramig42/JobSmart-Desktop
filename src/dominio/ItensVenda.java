/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @NamedQuery(name = "ItensVenda.findByIditensvenda", query = "SELECT i FROM ItensVenda i WHERE i.iditensvenda = :iditensvenda"),
    @NamedQuery(name = "ItensVenda.findByQuantItensVenda", query = "SELECT i FROM ItensVenda i WHERE i.quantItensVenda = :quantItensVenda")})
public class ItensVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_itens_venda")
    private Integer iditensvenda;
    @Basic(optional = false)
    @Column(name = "quant_itens_venda")
    private int quantItensVenda;
    @JoinColumn(name = "id_venda", referencedColumnName = "id_venda")
    @ManyToOne(optional = false)
    private Venda idVenda;
    @JoinColumn(name = "id_est", referencedColumnName = "id_est")
    @ManyToOne(optional = false)
    private Estoque idEst;

    public ItensVenda() {
    }

    public ItensVenda(Integer iditensvenda) {
        this.iditensvenda = iditensvenda;
    }

    public ItensVenda(Integer iditensvenda, int quantItensVenda) {
        this.iditensvenda = iditensvenda;
        this.quantItensVenda = quantItensVenda;
    }

    public Integer getIditensvenda() {
        return iditensvenda;
    }

    public void setIditensvenda(Integer iditensvenda) {
        this.iditensvenda = iditensvenda;
    }

    public int getQuantItensVenda() {
        return quantItensVenda;
    }

    public void setQuantItensVenda(int quantItensVenda) {
        this.quantItensVenda = quantItensVenda;
    }

    public Venda getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Venda idVenda) {
        this.idVenda = idVenda;
    }

    public Estoque getIdEst() {
        return idEst;
    }

    public void setIdEst(Estoque idEst) {
        this.idEst = idEst;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iditensvenda != null ? iditensvenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItensVenda)) {
            return false;
        }
        ItensVenda other = (ItensVenda) object;
        if ((this.iditensvenda == null && other.iditensvenda != null) || (this.iditensvenda != null && !this.iditensvenda.equals(other.iditensvenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.ItensVenda[ iditensvenda=" + iditensvenda + " ]";
    }
    
}
