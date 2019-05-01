/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author caioa
 */
@Entity
@Table(name = "forma_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f"),
    @NamedQuery(name = "FormaPagamento.findByIdForma", query = "SELECT f FROM FormaPagamento f WHERE f.idForma = :idForma"),
    @NamedQuery(name = "FormaPagamento.findByDescForma", query = "SELECT f FROM FormaPagamento f WHERE f.descForma = :descForma"),
    @NamedQuery(name = "FormaPagamento.findByAtivoForma", query = "SELECT f FROM FormaPagamento f WHERE f.ativoForma = :ativoForma")})
public class FormaPagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_forma")
    private Integer idForma;
    @Basic(optional = false)
    @Column(name = "desc_forma")
    private String descForma;
    @Column(name = "ativo_forma")
    private Boolean ativoForma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idForma")
    private List<Pagamento> pagamentoList;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer idForma) {
        this.idForma = idForma;
    }

    public FormaPagamento(Integer idForma, String descForma) {
        this.idForma = idForma;
        this.descForma = descForma;
    }

    public Integer getIdForma() {
        return idForma;
    }

    public void setIdForma(Integer idForma) {
        this.idForma = idForma;
    }

    public String getDescForma() {
        return descForma;
    }

    public void setDescForma(String descForma) {
        this.descForma = descForma;
    }

    public Boolean getAtivoForma() {
        return ativoForma;
    }

    public void setAtivoForma(Boolean ativoForma) {
        this.ativoForma = ativoForma;
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
        hash += (idForma != null ? idForma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.idForma == null && other.idForma != null) || (this.idForma != null && !this.idForma.equals(other.idForma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.FormaPagamento[ idForma=" + idForma + " ]";
    }
    
}
