/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.dados;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dominio.Funcionario;
import dominio.ItensVenda;
import java.util.ArrayList;
import java.util.List;
import dominio.Pagamento;
import dominio.Venda;
import dominio.dados.exceptions.IllegalOrphanException;
import dominio.dados.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author caioa
 */
public class VendaJpaController implements Serializable {

    public VendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venda venda) {
        if (venda.getItensVendaList() == null) {
            venda.setItensVendaList(new ArrayList<ItensVenda>());
        }
        if (venda.getPagamentoList() == null) {
            venda.setPagamentoList(new ArrayList<Pagamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario matFun = venda.getMatFun();
            if (matFun != null) {
                matFun = em.getReference(matFun.getClass(), matFun.getMatFun());
                venda.setMatFun(matFun);
            }
            List<ItensVenda> attachedItensVendaList = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListItensVendaToAttach : venda.getItensVendaList()) {
                itensVendaListItensVendaToAttach = em.getReference(itensVendaListItensVendaToAttach.getClass(), itensVendaListItensVendaToAttach.getIditensvenda());
                attachedItensVendaList.add(itensVendaListItensVendaToAttach);
            }
            venda.setItensVendaList(attachedItensVendaList);
            List<Pagamento> attachedPagamentoList = new ArrayList<Pagamento>();
            for (Pagamento pagamentoListPagamentoToAttach : venda.getPagamentoList()) {
                pagamentoListPagamentoToAttach = em.getReference(pagamentoListPagamentoToAttach.getClass(), pagamentoListPagamentoToAttach.getIdPag());
                attachedPagamentoList.add(pagamentoListPagamentoToAttach);
            }
            venda.setPagamentoList(attachedPagamentoList);
            em.persist(venda);
            if (matFun != null) {
                matFun.getVendaList().add(venda);
                matFun = em.merge(matFun);
            }
            for (ItensVenda itensVendaListItensVenda : venda.getItensVendaList()) {
                Venda oldIdVendaOfItensVendaListItensVenda = itensVendaListItensVenda.getIdVenda();
                itensVendaListItensVenda.setIdVenda(venda);
                itensVendaListItensVenda = em.merge(itensVendaListItensVenda);
                if (oldIdVendaOfItensVendaListItensVenda != null) {
                    oldIdVendaOfItensVendaListItensVenda.getItensVendaList().remove(itensVendaListItensVenda);
                    oldIdVendaOfItensVendaListItensVenda = em.merge(oldIdVendaOfItensVendaListItensVenda);
                }
            }
            for (Pagamento pagamentoListPagamento : venda.getPagamentoList()) {
                Venda oldIdVendaOfPagamentoListPagamento = pagamentoListPagamento.getIdVenda();
                pagamentoListPagamento.setIdVenda(venda);
                pagamentoListPagamento = em.merge(pagamentoListPagamento);
                if (oldIdVendaOfPagamentoListPagamento != null) {
                    oldIdVendaOfPagamentoListPagamento.getPagamentoList().remove(pagamentoListPagamento);
                    oldIdVendaOfPagamentoListPagamento = em.merge(oldIdVendaOfPagamentoListPagamento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venda venda) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda persistentVenda = em.find(Venda.class, venda.getIdVenda());
            Funcionario matFunOld = persistentVenda.getMatFun();
            Funcionario matFunNew = venda.getMatFun();
            List<ItensVenda> itensVendaListOld = persistentVenda.getItensVendaList();
            List<ItensVenda> itensVendaListNew = venda.getItensVendaList();
            List<Pagamento> pagamentoListOld = persistentVenda.getPagamentoList();
            List<Pagamento> pagamentoListNew = venda.getPagamentoList();
            List<String> illegalOrphanMessages = null;
            for (ItensVenda itensVendaListOldItensVenda : itensVendaListOld) {
                if (!itensVendaListNew.contains(itensVendaListOldItensVenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ItensVenda " + itensVendaListOldItensVenda + " since its idVenda field is not nullable.");
                }
            }
            for (Pagamento pagamentoListOldPagamento : pagamentoListOld) {
                if (!pagamentoListNew.contains(pagamentoListOldPagamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagamento " + pagamentoListOldPagamento + " since its idVenda field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (matFunNew != null) {
                matFunNew = em.getReference(matFunNew.getClass(), matFunNew.getMatFun());
                venda.setMatFun(matFunNew);
            }
            List<ItensVenda> attachedItensVendaListNew = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListNewItensVendaToAttach : itensVendaListNew) {
                itensVendaListNewItensVendaToAttach = em.getReference(itensVendaListNewItensVendaToAttach.getClass(), itensVendaListNewItensVendaToAttach.getIditensvenda());
                attachedItensVendaListNew.add(itensVendaListNewItensVendaToAttach);
            }
            itensVendaListNew = attachedItensVendaListNew;
            venda.setItensVendaList(itensVendaListNew);
            List<Pagamento> attachedPagamentoListNew = new ArrayList<Pagamento>();
            for (Pagamento pagamentoListNewPagamentoToAttach : pagamentoListNew) {
                pagamentoListNewPagamentoToAttach = em.getReference(pagamentoListNewPagamentoToAttach.getClass(), pagamentoListNewPagamentoToAttach.getIdPag());
                attachedPagamentoListNew.add(pagamentoListNewPagamentoToAttach);
            }
            pagamentoListNew = attachedPagamentoListNew;
            venda.setPagamentoList(pagamentoListNew);
            venda = em.merge(venda);
            if (matFunOld != null && !matFunOld.equals(matFunNew)) {
                matFunOld.getVendaList().remove(venda);
                matFunOld = em.merge(matFunOld);
            }
            if (matFunNew != null && !matFunNew.equals(matFunOld)) {
                matFunNew.getVendaList().add(venda);
                matFunNew = em.merge(matFunNew);
            }
            for (ItensVenda itensVendaListNewItensVenda : itensVendaListNew) {
                if (!itensVendaListOld.contains(itensVendaListNewItensVenda)) {
                    Venda oldIdVendaOfItensVendaListNewItensVenda = itensVendaListNewItensVenda.getIdVenda();
                    itensVendaListNewItensVenda.setIdVenda(venda);
                    itensVendaListNewItensVenda = em.merge(itensVendaListNewItensVenda);
                    if (oldIdVendaOfItensVendaListNewItensVenda != null && !oldIdVendaOfItensVendaListNewItensVenda.equals(venda)) {
                        oldIdVendaOfItensVendaListNewItensVenda.getItensVendaList().remove(itensVendaListNewItensVenda);
                        oldIdVendaOfItensVendaListNewItensVenda = em.merge(oldIdVendaOfItensVendaListNewItensVenda);
                    }
                }
            }
            for (Pagamento pagamentoListNewPagamento : pagamentoListNew) {
                if (!pagamentoListOld.contains(pagamentoListNewPagamento)) {
                    Venda oldIdVendaOfPagamentoListNewPagamento = pagamentoListNewPagamento.getIdVenda();
                    pagamentoListNewPagamento.setIdVenda(venda);
                    pagamentoListNewPagamento = em.merge(pagamentoListNewPagamento);
                    if (oldIdVendaOfPagamentoListNewPagamento != null && !oldIdVendaOfPagamentoListNewPagamento.equals(venda)) {
                        oldIdVendaOfPagamentoListNewPagamento.getPagamentoList().remove(pagamentoListNewPagamento);
                        oldIdVendaOfPagamentoListNewPagamento = em.merge(oldIdVendaOfPagamentoListNewPagamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venda.getIdVenda();
                if (findVenda(id) == null) {
                    throw new NonexistentEntityException("The venda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda venda;
            try {
                venda = em.getReference(Venda.class, id);
                venda.getIdVenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venda with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ItensVenda> itensVendaListOrphanCheck = venda.getItensVendaList();
            for (ItensVenda itensVendaListOrphanCheckItensVenda : itensVendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venda (" + venda + ") cannot be destroyed since the ItensVenda " + itensVendaListOrphanCheckItensVenda + " in its itensVendaList field has a non-nullable idVenda field.");
            }
            List<Pagamento> pagamentoListOrphanCheck = venda.getPagamentoList();
            for (Pagamento pagamentoListOrphanCheckPagamento : pagamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venda (" + venda + ") cannot be destroyed since the Pagamento " + pagamentoListOrphanCheckPagamento + " in its pagamentoList field has a non-nullable idVenda field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Funcionario matFun = venda.getMatFun();
            if (matFun != null) {
                matFun.getVendaList().remove(venda);
                matFun = em.merge(matFun);
            }
            em.remove(venda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venda> findVendaEntities() {
        return findVendaEntities(true, -1, -1);
    }

    public List<Venda> findVendaEntities(int maxResults, int firstResult) {
        return findVendaEntities(false, maxResults, firstResult);
    }

    private List<Venda> findVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venda.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Venda findVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venda> rt = cq.from(Venda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
