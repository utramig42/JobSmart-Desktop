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
@Table(name = "funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByMatFun", query = "SELECT f FROM Funcionario f WHERE f.matFun = :matFun"),
    @NamedQuery(name = "Funcionario.findByNmFun", query = "SELECT f FROM Funcionario f WHERE f.nmFun = :nmFun"),
    @NamedQuery(name = "Funcionario.findByEndFun", query = "SELECT f FROM Funcionario f WHERE f.endFun = :endFun"),
    @NamedQuery(name = "Funcionario.findByUfFun", query = "SELECT f FROM Funcionario f WHERE f.ufFun = :ufFun"),
    @NamedQuery(name = "Funcionario.findByCidFun", query = "SELECT f FROM Funcionario f WHERE f.cidFun = :cidFun"),
    @NamedQuery(name = "Funcionario.findBySalFun", query = "SELECT f FROM Funcionario f WHERE f.salFun = :salFun"),
    @NamedQuery(name = "Funcionario.findByCpfFun", query = "SELECT f FROM Funcionario f WHERE f.cpfFun = :cpfFun"),
    @NamedQuery(name = "Funcionario.findByTelFun", query = "SELECT f FROM Funcionario f WHERE f.telFun = :telFun"),
    @NamedQuery(name = "Funcionario.findByDtNascFun", query = "SELECT f FROM Funcionario f WHERE f.dtNascFun = :dtNascFun"),
    @NamedQuery(name = "Funcionario.findByDtResFun", query = "SELECT f FROM Funcionario f WHERE f.dtResFun = :dtResFun"),
    @NamedQuery(name = "Funcionario.findByTempAtivoFun", query = "SELECT f FROM Funcionario f WHERE f.tempAtivoFun = :tempAtivoFun"),
    @NamedQuery(name = "Funcionario.findByDtAdmin", query = "SELECT f FROM Funcionario f WHERE f.dtAdmin = :dtAdmin")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mat_fun")
    private Integer matFun;
    @Basic(optional = false)
    @Column(name = "nm_fun")
    private String nmFun;
    @Basic(optional = false)
    @Column(name = "end_fun")
    private String endFun;
    @Basic(optional = false)
    @Column(name = "uf_fun")
    private String ufFun;
    @Basic(optional = false)
    @Column(name = "cid_fun")
    private String cidFun;
    @Basic(optional = false)
    @Column(name = "sal_fun")
    private double salFun;
    @Basic(optional = false)
    @Column(name = "cpf_fun")
    private String cpfFun;
    @Basic(optional = false)
    @Column(name = "tel_fun")
    private String telFun;
    @Basic(optional = false)
    @Column(name = "dt_nasc_fun")
    @Temporal(TemporalType.DATE)
    private Date dtNascFun;
    @Column(name = "dt_res_fun")
    @Temporal(TemporalType.DATE)
    private Date dtResFun;
    @Column(name = "temp_ativo_fun")
    private Boolean tempAtivoFun;
    @Basic(optional = false)
    @Column(name = "dt_admin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAdmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matFun")
    private List<Acesso> acessoList;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne(optional = false)
    private Cargo idCargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mat_fun")
    private List<Venda> vendas;
    
    public Funcionario() {
    }

    public Funcionario(Integer matFun) {
        this.matFun = matFun;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public Funcionario(Integer matFun, String nmFun, String endFun, String ufFun, String cidFun, double salFun, String cpfFun, String telFun, Date dtNascFun, Date dtAdmin) {
        this.matFun = matFun;
        this.nmFun = nmFun;
        this.endFun = endFun;
        this.ufFun = ufFun;
        this.cidFun = cidFun;
        this.salFun = salFun;
        this.cpfFun = cpfFun;
        this.telFun = telFun;
        this.dtNascFun = dtNascFun;
        this.dtAdmin = dtAdmin;
    }

    public Integer getMatFun() {
        return matFun;
    }

    public void setMatFun(Integer matFun) {
        this.matFun = matFun;
    }

    public String getNmFun() {
        return nmFun;
    }

    public void setNmFun(String nmFun) {
        this.nmFun = nmFun;
    }

    public String getEndFun() {
        return endFun;
    }

    public void setEndFun(String endFun) {
        this.endFun = endFun;
    }

    public String getUfFun() {
        return ufFun;
    }

    public void setUfFun(String ufFun) {
        this.ufFun = ufFun;
    }

    public String getCidFun() {
        return cidFun;
    }

    public void setCidFun(String cidFun) {
        this.cidFun = cidFun;
    }

    public double getSalFun() {
        return salFun;
    }

    public void setSalFun(double salFun) {
        this.salFun = salFun;
    }

    public String getCpfFun() {
        return cpfFun;
    }

    public void setCpfFun(String cpfFun) {
        this.cpfFun = cpfFun;
    }

    public String getTelFun() {
        return telFun;
    }

    public void setTelFun(String telFun) {
        this.telFun = telFun;
    }

    public Date getDtNascFun() {
        return dtNascFun;
    }

    public void setDtNascFun(Date dtNascFun) {
        this.dtNascFun = dtNascFun;
    }

    public Date getDtResFun() {
        return dtResFun;
    }

    public void setDtResFun(Date dtResFun) {
        this.dtResFun = dtResFun;
    }

    public Boolean getTempAtivoFun() {
        return tempAtivoFun;
    }

    public void setTempAtivoFun(Boolean tempAtivoFun) {
        this.tempAtivoFun = tempAtivoFun;
    }

    public Date getDtAdmin() {
        return dtAdmin;
    }

    public void setDtAdmin(Date dtAdmin) {
        this.dtAdmin = dtAdmin;
    }

    @XmlTransient
    public List<Acesso> getAcessoList() {
        return acessoList;
    }

    public void setAcessoList(List<Acesso> acessoList) {
        this.acessoList = acessoList;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matFun != null ? matFun.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.matFun == null && other.matFun != null) || (this.matFun != null && !this.matFun.equals(other.matFun))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Funcionario[ matFun=" + matFun + " ]";
    }
    
}
