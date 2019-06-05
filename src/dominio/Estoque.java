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
import javax.persistence.Lob;
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
@Table(name = "estoque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estoque.findAll", query = "SELECT e FROM Estoque e"),
    @NamedQuery(name = "Estoque.findByIdEst", query = "SELECT e FROM Estoque e WHERE e.idEst = :idEst"),
    @NamedQuery(name = "Estoque.findByLoteEst", query = "SELECT e FROM Estoque e WHERE e.loteEst = :loteEst"),
    @NamedQuery(name = "Estoque.findByVlrCustoEst", query = "SELECT e FROM Estoque e WHERE e.vlrCustoEst = :vlrCustoEst"),
    @NamedQuery(name = "Estoque.findByVlrVendaEst", query = "SELECT e FROM Estoque e WHERE e.vlrVendaEst = :vlrVendaEst"),
    @NamedQuery(name = "Estoque.findByQtdProdEst", query = "SELECT e FROM Estoque e WHERE e.qtdProdEst = :qtdProdEst"),
    @NamedQuery(name = "Estoque.findByDtFabEst", query = "SELECT e FROM Estoque e WHERE e.dtFabEst = :dtFabEst"),
    @NamedQuery(name = "Estoque.findByDtValEst", query = "SELECT e FROM Estoque e WHERE e.dtValEst = :dtValEst"),
    @NamedQuery(name = "Estoque.findByAtivoEst", query = "SELECT e FROM Estoque e WHERE e.ativoEst = :ativoEst"),
    @NamedQuery(name = "Estoque.findByDtCadEst", query = "SELECT e FROM Estoque e WHERE e.dtCadEst = :dtCadEst")})
public class Estoque implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_est")
    private Integer idEst;
    @Column(name = "lote_est")
    private String loteEst;
    @Basic(optional = false)
    @Column(name = "vlr_custo_est")
    private double vlrCustoEst;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vlr_venda_est")
    private Double vlrVendaEst;
    @Basic(optional = false)
    @Column(name = "qtd_prod_est")
    private int qtdProdEst;
    @Basic(optional = false)
    @Column(name = "dt_fab_est")
    @Temporal(TemporalType.DATE)
    private Date dtFabEst;
    @Basic(optional = false)
    @Column(name = "dt_val_est")
    @Temporal(TemporalType.DATE)
    private Date dtValEst;
    @Lob
    @Column(name = "obs_est")
    private String obsEst;
    @Column(name = "ativo_est")
    private Boolean ativoEst;
    @Basic(optional = false)
    @Column(name = "dt_cad_est")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadEst;
    @JoinColumn(name = "id_for", referencedColumnName = "id_for")
    @ManyToOne(optional = false)
    private Fornecedor idFor;
    @JoinColumn(name = "id_prod", referencedColumnName = "id_prod")
    @ManyToOne(optional = false)
    private Produto idProd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEst")
    private List<ItensVenda> itensVendaList;

    public Estoque() {
    }
    
    
    public Estoque(Integer idEst) {
        this.idEst = idEst;
    }

    public Estoque(Integer idEst, double vlrCustoEst, int qtdProdEst, Date dtFabEst, Date dtValEst, Date dtCadEst) {
        this.idEst = idEst;
        this.vlrCustoEst = vlrCustoEst;
        this.qtdProdEst = qtdProdEst;
        this.dtFabEst = dtFabEst;
        this.dtValEst = dtValEst;
        this.dtCadEst = dtCadEst;
        setVlrVendaEst();
        
    }

    public Integer getIdEst() {
        return idEst;
    }

    public void setIdEst(Integer idEst) {
        this.idEst = idEst;
    }

    public String getLoteEst() {
        return loteEst;
    }

    public void setLoteEst(String loteEst) {
        this.loteEst = loteEst;
    }

    public double getVlrCustoEst() {
        return vlrCustoEst;
    }

    public void setVlrCustoEst(double vlrCustoEst) {
        this.vlrCustoEst = vlrCustoEst;
        setVlrVendaEst();
    }

    public Double getVlrVendaEst() {
        return vlrVendaEst;
    }

    public void setVlrVendaEst(Double vlrVendaEst) {
        this.vlrVendaEst = vlrVendaEst;
    }
    public boolean setVlrVendaEst() {
        if(this.vlrVendaEst == null){
        this.vlrVendaEst = vlrCustoEst * 2;
        return true;
        }
        return false;
    }

    public int getQtdProdEst() {
        return qtdProdEst;
    }

    public void setQtdProdEst(int qtdProdEst) {
        this.qtdProdEst = qtdProdEst;
    }

    public Date getDtFabEst() {
        return dtFabEst;
    }

    public void setDtFabEst(Date dtFabEst) {
        this.dtFabEst = dtFabEst;
    }

    public Date getDtValEst() {
        return dtValEst;
    }

    public void setDtValEst(Date dtValEst) {
        this.dtValEst = dtValEst;
    }

    public String getObsEst() {
        return obsEst;
    }

    public void setObsEst(String obsEst) {
        this.obsEst = obsEst;
    }

    public Boolean getAtivoEst() {
        return ativoEst;
    }

    public void setAtivoEst(Boolean ativoEst) {
        this.ativoEst = ativoEst;
    }

    public Date getDtCadEst() {
        return dtCadEst;
    }

    public void setDtCadEst(Date dtCadEst) {
        this.dtCadEst = dtCadEst;
    }

    public Fornecedor getIdFor() {
        return idFor;
    }

    public void setIdFor(Fornecedor idFor) {
        this.idFor = idFor;
    }

    public Produto getIdProd() {
        return idProd;
    }

    public void setIdProd(Produto idProd) {
        this.idProd = idProd;
    }

    @XmlTransient
    public List<ItensVenda> getItensVendaList() {
        return itensVendaList;
    }

    public void setItensVendaList(List<ItensVenda> itensVendaList) {
        this.itensVendaList = itensVendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEst != null ? idEst.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.idEst == null && other.idEst != null) || (this.idEst != null && !this.idEst.equals(other.idEst))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Estoque[ idEst=" + idEst + " ]";
    }
    
}
