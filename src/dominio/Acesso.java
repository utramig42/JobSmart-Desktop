/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author caioa
 */
@Entity
@Table(name = "acesso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acesso.findAll", query = "SELECT a FROM Acesso a"),
    @NamedQuery(name = "Acesso.findByIdAcesso", query = "SELECT a FROM Acesso a WHERE a.idAcesso = :idAcesso"),
    @NamedQuery(name = "Acesso.findBySenhaAcesso", query = "SELECT a FROM Acesso a WHERE a.senhaAcesso = :senhaAcesso"),
    @NamedQuery(name = "Acesso.findByDtUltAcesso", query = "SELECT a FROM Acesso a WHERE a.dtUltAcesso = :dtUltAcesso")})
public class Acesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_acesso")
    private Integer idAcesso;
    @Basic(optional = false)
    @Column(name = "senha_acesso")
    private String senhaAcesso;
    @Column(name = "dt_ult_acesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltAcesso;
    @JoinColumn(name = "mat_fun", referencedColumnName = "mat_fun")
    @ManyToOne(optional = false)
    private Funcionario matFun;

    public Acesso() {
    }

    public Acesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public Acesso(Integer idAcesso, String senhaAcesso) {
        this.idAcesso = idAcesso;
        this.senhaAcesso = senhaAcesso;
    }

    public Integer getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(Integer idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public Date getDtUltAcesso() {
        return dtUltAcesso;
    }

    public void setDtUltAcesso(Date dtUltAcesso) {
        this.dtUltAcesso = dtUltAcesso;
    }

    public Funcionario getMatFun() {
        return matFun;
    }

    public void setMatFun(Funcionario matFun) {
        this.matFun = matFun;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAcesso != null ? idAcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.idAcesso == null && other.idAcesso != null) || (this.idAcesso != null && !this.idAcesso.equals(other.idAcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idAcesso + "" ;
    }
    
}
