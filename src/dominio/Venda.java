/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author caioa
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v"),
    @NamedQuery(name = "Venda.findByIdVenda", query = "SELECT v FROM Venda v WHERE v.idVenda = :idVenda"),
    @NamedQuery(name = "Venda.findByDtVenda", query = "SELECT v FROM Venda v WHERE v.dtVenda = :dtVenda"),
    @NamedQuery(name = "Venda.findByVlrVenda", query = "SELECT v FROM Venda v WHERE v.vlrVenda = :vlrVenda")})
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_venda")
    private Integer idVenda;
    @Basic(optional = false)
    @Column(name = "dt_venda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtVenda;
    @Basic(optional = false)
    @Column(name = "vlr_venda")
    private double vlrVenda;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenda")
    private List<ItensVenda> itensVendaList;
    @JoinColumn(name = "mat_fun", referencedColumnName = "mat_fun")
    @ManyToOne(optional = false)
    private Funcionario matFun;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenda")
    private List<Pagamento> pagamentoList;

    public Venda() {
    }

    public Venda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Venda(Integer idVenda, Date dtVenda, double vlrVenda) {
        this.idVenda = idVenda;
        this.dtVenda = dtVenda;
        this.vlrVenda = vlrVenda;
    }
    
    public Venda(Integer idVenda, Date dtVenda, Funcionario matFun){
        this.idVenda = idVenda;
        this.dtVenda = dtVenda;
        this.matFun = matFun;
    }

    public Integer getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Integer idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    }

    public double getVlrVenda() {
        return vlrVenda;
    }

    public void setVlrVenda(double vlrVenda) {
        this.vlrVenda = vlrVenda;
    }

    @XmlTransient
    public List<ItensVenda> getItensVendaList() {
        return itensVendaList;
    }

    public void setItensVendaList(List<ItensVenda> itensVendaList) {
        this.itensVendaList = itensVendaList;
    }

    public Funcionario getMatFun() {
        return matFun;
    }

    public void setMatFun(Funcionario matFun) {
        this.matFun = matFun;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenda != null ? idVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.idVenda == null && other.idVenda != null) || (this.idVenda != null && !this.idVenda.equals(other.idVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Venda[ idVenda=" + idVenda + " ]";
    }
    
}
