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
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByIdProd", query = "SELECT p FROM Produto p WHERE p.idProd = :idProd"),
    @NamedQuery(name = "Produto.findByNmProd", query = "SELECT p FROM Produto p WHERE p.nmProd = :nmProd"),
    @NamedQuery(name = "Produto.findByQtdMinProd", query = "SELECT p FROM Produto p WHERE p.qtdMinProd = :qtdMinProd"),
    @NamedQuery(name = "Produto.findByDtCadProd", query = "SELECT p FROM Produto p WHERE p.dtCadProd = :dtCadProd"),
    @NamedQuery(name = "Produto.findByAtivoProd", query = "SELECT p FROM Produto p WHERE p.ativoProd = :ativoProd"),
    @NamedQuery(name = "Produto.findByQtdProd", query = "SELECT p FROM Produto p WHERE p.qtdProd = :qtdProd")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prod")
    private Integer idProd;
    @Basic(optional = false)
    @Column(name = "nm_prod")
    private String nmProd;
    @Column(name = "qtd_min_prod")
    private Integer qtdMinProd;
    @Basic(optional = false)
    @Column(name = "dt_cad_prod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadProd;
    @Lob
    @Column(name = "obs_prod")
    private String obsProd;
    @Column(name = "ativo_prod")
    private Boolean ativoProd;
    @Column(name = "qtd_prod")
    private Integer qtdProd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProd")
    private List<Estoque> estoqueList;
    @JoinColumn(name = "id_cat", referencedColumnName = "id_cat")
    @ManyToOne(optional = false)
    private Categoria idCat;
    @JoinColumn(name = "id_marca", referencedColumnName = "id_marca")
    @ManyToOne(optional = false)
    private Marca idMarca;

    public Produto() {
    }

    public Produto(Integer idProd) {
        this.idProd = idProd;
    }

    public Produto(Integer idProd, String nmProd, Date dtCadProd) {
        this.idProd = idProd;
        this.nmProd = nmProd;
        this.dtCadProd = dtCadProd;
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    public String getNmProd() {
        return nmProd;
    }

    public void setNmProd(String nmProd) {
        this.nmProd = nmProd;
    }

    public Integer getQtdMinProd() {
        return qtdMinProd;
    }

    public void setQtdMinProd(Integer qtdMinProd) {
        this.qtdMinProd = qtdMinProd;
    }

    public Date getDtCadProd() {
        return dtCadProd;
    }

    public void setDtCadProd(Date dtCadProd) {
        this.dtCadProd = dtCadProd;
    }

    public String getObsProd() {
        return obsProd;
    }

    public void setObsProd(String obsProd) {
        this.obsProd = obsProd;
    }

    public Boolean getAtivoProd() {
        return ativoProd;
    }

    public void setAtivoProd(Boolean ativoProd) {
        this.ativoProd = ativoProd;
    }

    public Integer getQtdProd() {
        return qtdProd;
    }

    public void setQtdProd(Integer qtdProd) {
        this.qtdProd = qtdProd;
    }

    @XmlTransient
    public List<Estoque> getEstoqueList() {
        return estoqueList;
    }

    public void setEstoqueList(List<Estoque> estoqueList) {
        this.estoqueList = estoqueList;
    }

    public Categoria getIdCat() {
        return idCat;
    }

    public void setIdCat(Categoria idCat) {
        this.idCat = idCat;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProd != null ? idProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.idProd == null && other.idProd != null) || (this.idProd != null && !this.idProd.equals(other.idProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Produto[ idProd=" + idProd + " ]";
    }
    
}
