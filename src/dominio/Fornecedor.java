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
@Table(name = "fornecedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findByIdFor", query = "SELECT f FROM Fornecedor f WHERE f.idFor = :idFor"),
    @NamedQuery(name = "Fornecedor.findByNmFor", query = "SELECT f FROM Fornecedor f WHERE f.nmFor = :nmFor"),
    @NamedQuery(name = "Fornecedor.findByCnpjFor", query = "SELECT f FROM Fornecedor f WHERE f.cnpjFor = :cnpjFor"),
    @NamedQuery(name = "Fornecedor.findByRazSocFor", query = "SELECT f FROM Fornecedor f WHERE f.razSocFor = :razSocFor"),
    @NamedQuery(name = "Fornecedor.findByUfFor", query = "SELECT f FROM Fornecedor f WHERE f.ufFor = :ufFor"),
    @NamedQuery(name = "Fornecedor.findByCidFor", query = "SELECT f FROM Fornecedor f WHERE f.cidFor = :cidFor"),
    @NamedQuery(name = "Fornecedor.findByEndFor", query = "SELECT f FROM Fornecedor f WHERE f.endFor = :endFor"),
    @NamedQuery(name = "Fornecedor.findByNmContFor", query = "SELECT f FROM Fornecedor f WHERE f.nmContFor = :nmContFor"),
    @NamedQuery(name = "Fornecedor.findByTelFixFor", query = "SELECT f FROM Fornecedor f WHERE f.telFixFor = :telFixFor"),
    @NamedQuery(name = "Fornecedor.findByDtCadFor", query = "SELECT f FROM Fornecedor f WHERE f.dtCadFor = :dtCadFor"),
    @NamedQuery(name = "Fornecedor.findByTelCelFor", query = "SELECT f FROM Fornecedor f WHERE f.telCelFor = :telCelFor")})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_for")
    private Integer idFor;
    @Column(name = "nm_for")
    private String nmFor;
    @Basic(optional = false)
    @Column(name = "cnpj_for")
    private String cnpjFor;
    @Basic(optional = false)
    @Column(name = "raz_soc_for")
    private String razSocFor;
    @Basic(optional = false)
    @Column(name = "uf_for")
    private String ufFor;
    @Basic(optional = false)
    @Column(name = "cid_for")
    private String cidFor;
    @Basic(optional = false)
    @Column(name = "end_for")
    private String endFor;
    @Column(name = "nm_cont_for")
    private String nmContFor;
    @Column(name = "tel_fix_for")
    private String telFixFor;
    @Basic(optional = false)
    @Column(name = "dt_cad_for")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadFor;
    @Basic(optional = false)
    @Column(name = "tel_cel_for")
    private String telCelFor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFor")
    private List<Estoque> estoqueList;

    public Fornecedor() {
    }

    public Fornecedor(Integer idFor) {
        this.idFor = idFor;
    }

    public Fornecedor(Integer idFor, String cnpjFor, String razSocFor, String ufFor, String cidFor, String endFor, Date dtCadFor, String telCelFor) {
        this.idFor = idFor;
        this.cnpjFor = cnpjFor;
        this.razSocFor = razSocFor;
        this.ufFor = ufFor;
        this.cidFor = cidFor;
        this.endFor = endFor;
        this.dtCadFor = dtCadFor;
        this.telCelFor = telCelFor;
    }

    public Integer getIdFor() {
        return idFor;
    }

    public void setIdFor(Integer idFor) {
        this.idFor = idFor;
    }

    public String getNmFor() {
        return nmFor;
    }

    public void setNmFor(String nmFor) {
        this.nmFor = nmFor;
    }

    public String getCnpjFor() {
        return cnpjFor;
    }

    public void setCnpjFor(String cnpjFor) {
        this.cnpjFor = cnpjFor;
    }

    public String getRazSocFor() {
        return razSocFor;
    }

    public void setRazSocFor(String razSocFor) {
        this.razSocFor = razSocFor;
    }

    public String getUfFor() {
        return ufFor;
    }

    public void setUfFor(String ufFor) {
        this.ufFor = ufFor;
    }

    public String getCidFor() {
        return cidFor;
    }

    public void setCidFor(String cidFor) {
        this.cidFor = cidFor;
    }

    public String getEndFor() {
        return endFor;
    }

    public void setEndFor(String endFor) {
        this.endFor = endFor;
    }

    public String getNmContFor() {
        return nmContFor;
    }

    public void setNmContFor(String nmContFor) {
        this.nmContFor = nmContFor;
    }

    public String getTelFixFor() {
        return telFixFor;
    }

    public void setTelFixFor(String telFixFor) {
        this.telFixFor = telFixFor;
    }

    public Date getDtCadFor() {
        return dtCadFor;
    }

    public void setDtCadFor(Date dtCadFor) {
        this.dtCadFor = dtCadFor;
    }

    public String getTelCelFor() {
        return telCelFor;
    }

    public void setTelCelFor(String telCelFor) {
        this.telCelFor = telCelFor;
    }

    @XmlTransient
    public List<Estoque> getEstoqueList() {
        return estoqueList;
    }

    public void setEstoqueList(List<Estoque> estoqueList) {
        this.estoqueList = estoqueList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFor != null ? idFor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.idFor == null && other.idFor != null) || (this.idFor != null && !this.idFor.equals(other.idFor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Fornecedor[ idFor=" + idFor + " ]";
    }
    
}
