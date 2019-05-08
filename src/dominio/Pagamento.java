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
@Table(name = "pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p"),
    @NamedQuery(name = "Pagamento.findByIdPag", query = "SELECT p FROM Pagamento p WHERE p.idPag = :idPag"),
    @NamedQuery(name = "Pagamento.findByVlrPag", query = "SELECT p FROM Pagamento p WHERE p.vlrPag = :vlrPag"),
    @NamedQuery(name = "Pagamento.findByVlrTrocoPag", query = "SELECT p FROM Pagamento p WHERE p.vlrTrocoPag = :vlrTrocoPag")})
public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pag")
    private Integer idPag;
    @Basic(optional = false)
    @Column(name = "vlr_pag")
    private double vlrPag;
    @Basic(optional = false)
    @Column(name = "vlr_troco_pag")
    private double vlrTrocoPag;
    @JoinColumn(name = "id_venda", referencedColumnName = "id_venda")
    @ManyToOne(optional = false)
    private Venda idVenda;
    @JoinColumn(name = "id_forma", referencedColumnName = "id_forma")
    @ManyToOne(optional = false)
    private FormaPagamento idForma;

    public Pagamento() {
    }

    public Pagamento(Integer idPag) {
        this.idPag = idPag;
    }

    public Pagamento(Integer idPag, double vlrPag, double vlrTrocoPag) {
        this.idPag = idPag;
        this.vlrPag = vlrPag;
        this.vlrTrocoPag = vlrTrocoPag;
    }

    public Integer getIdPag() {
        return idPag;
    }

    public void setIdPag(Integer idPag) {
        this.idPag = idPag;
    }

    public double getVlrPag() {
        return vlrPag;
    }

    public void setVlrPag(double vlrPag) {
        this.vlrPag = vlrPag;
    }

    public double getVlrTrocoPag() {
        return vlrTrocoPag;
    }

    public void setVlrTrocoPag(double vlrTrocoPag) {
        this.vlrTrocoPag = vlrTrocoPag;
    }

    public Venda getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Venda idVenda) {
        this.idVenda = idVenda;
    }

    public FormaPagamento getIdForma() {
        return idForma;
    }

    public void setIdForma(FormaPagamento idForma) {
        this.idForma = idForma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPag != null ? idPag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.idPag == null && other.idPag != null) || (this.idPag != null && !this.idPag.equals(other.idPag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Pagamento[ idPag=" + idPag + " ]";
    }
    
}
