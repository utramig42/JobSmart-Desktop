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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Estoque.findByIdEst", query = "SELECT e FROM Estoque e WHERE e.estoquePK.idEst = :idEst"),
    @NamedQuery(name = "Estoque.findByIdFor", query = "SELECT e FROM Estoque e WHERE e.estoquePK.idFor = :idFor"),
    @NamedQuery(name = "Estoque.findByIdProd", query = "SELECT e FROM Estoque e WHERE e.estoquePK.idProd = :idProd"),
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
    @EmbeddedId
    protected EstoquePK estoquePK;
    @Basic(optional = false)
    @Column(name = "lote_est")
    private String loteEst;
    @Basic(optional = false)
    @Column(name = "vlr_custo_est")
    private double vlrCustoEst;
    @Basic(optional = false)
    @Column(name = "vlr_venda_est")
    private double vlrVendaEst;
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
    @JoinColumn(name = "id_for", referencedColumnName = "id_for", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
    @JoinColumn(name = "id_prod", referencedColumnName = "id_prod", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estoque")
    private List<ItensVenda> itensVendaList;

    public Estoque() {
    }

    public Estoque(EstoquePK estoquePK) {
        this.estoquePK = estoquePK;
    }

    public Estoque(EstoquePK estoquePK, String loteEst, double vlrCustoEst, double vlrVendaEst, int qtdProdEst, Date dtFabEst, Date dtValEst, Date dtCadEst) {
        this.estoquePK = estoquePK;
        this.loteEst = loteEst;
        this.vlrCustoEst = vlrCustoEst;
        this.vlrVendaEst = vlrVendaEst;
        this.qtdProdEst = qtdProdEst;
        this.dtFabEst = dtFabEst;
        this.dtValEst = dtValEst;
        this.dtCadEst = dtCadEst;
    }

    public Estoque(int idEst, int idFor, int idProd) {
        this.estoquePK = new EstoquePK(idEst, idFor, idProd);
    }
    
    public EstoquePK converteIdEstoque(List<Estoque> estoques, int codigoEstoque){
        for(Estoque etoc : estoques){
            if(etoc.getEstoquePK().getIdEst() == codigoEstoque ){
                return etoc.getEstoquePK();

            }
        }
        return null;
    }

    public EstoquePK getEstoquePK() {
        return estoquePK;
    }

    public void setEstoquePK(EstoquePK estoquePK) {
        this.estoquePK = estoquePK;
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
    }

    public double getVlrVendaEst() {
        return vlrVendaEst;
    }

    public void setVlrVendaEst(double vlrVendaEst) {
        this.vlrVendaEst = vlrVendaEst;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
        hash += (estoquePK != null ? estoquePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estoque)) {
            return false;
        }
        Estoque other = (Estoque) object;
        if ((this.estoquePK == null && other.estoquePK != null) || (this.estoquePK != null && !this.estoquePK.equals(other.estoquePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Estoque[ estoquePK=" + estoquePK + " ]";
    }
    
}
