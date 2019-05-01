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
import dominio.Venda;
import dominio.FormaPagamento;
import dominio.Pagamento;
import dominio.dados.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author caioa
 */
public class PagamentoJpaController implements Serializable {

    public PagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pagamento pagamento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda idVenda = pagamento.getIdVenda();
            if (idVenda != null) {
                idVenda = em.getReference(idVenda.getClass(), idVenda.getIdVenda());
                pagamento.setIdVenda(idVenda);
            }
            FormaPagamento idForma = pagamento.getIdForma();
            if (idForma != null) {
                idForma = em.getReference(idForma.getClass(), idForma.getIdForma());
                pagamento.setIdForma(idForma);
            }
            em.persist(pagamento);
            if (idVenda != null) {
                idVenda.getPagamentoList().add(pagamento);
                idVenda = em.merge(idVenda);
            }
            if (idForma != null) {
                idForma.getPagamentoList().add(pagamento);
                idForma = em.merge(idForma);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pagamento pagamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagamento persistentPagamento = em.find(Pagamento.class, pagamento.getIdPag());
            Venda idVendaOld = persistentPagamento.getIdVenda();
            Venda idVendaNew = pagamento.getIdVenda();
            FormaPagamento idFormaOld = persistentPagamento.getIdForma();
            FormaPagamento idFormaNew = pagamento.getIdForma();
            if (idVendaNew != null) {
                idVendaNew = em.getReference(idVendaNew.getClass(), idVendaNew.getIdVenda());
                pagamento.setIdVenda(idVendaNew);
            }
            if (idFormaNew != null) {
                idFormaNew = em.getReference(idFormaNew.getClass(), idFormaNew.getIdForma());
                pagamento.setIdForma(idFormaNew);
            }
            pagamento = em.merge(pagamento);
            if (idVendaOld != null && !idVendaOld.equals(idVendaNew)) {
                idVendaOld.getPagamentoList().remove(pagamento);
                idVendaOld = em.merge(idVendaOld);
            }
            if (idVendaNew != null && !idVendaNew.equals(idVendaOld)) {
                idVendaNew.getPagamentoList().add(pagamento);
                idVendaNew = em.merge(idVendaNew);
            }
            if (idFormaOld != null && !idFormaOld.equals(idFormaNew)) {
                idFormaOld.getPagamentoList().remove(pagamento);
                idFormaOld = em.merge(idFormaOld);
            }
            if (idFormaNew != null && !idFormaNew.equals(idFormaOld)) {
                idFormaNew.getPagamentoList().add(pagamento);
                idFormaNew = em.merge(idFormaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagamento.getIdPag();
                if (findPagamento(id) == null) {
                    throw new NonexistentEntityException("The pagamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pagamento pagamento;
            try {
                pagamento = em.getReference(Pagamento.class, id);
                pagamento.getIdPag();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagamento with id " + id + " no longer exists.", enfe);
            }
            Venda idVenda = pagamento.getIdVenda();
            if (idVenda != null) {
                idVenda.getPagamentoList().remove(pagamento);
                idVenda = em.merge(idVenda);
            }
            FormaPagamento idForma = pagamento.getIdForma();
            if (idForma != null) {
                idForma.getPagamentoList().remove(pagamento);
                idForma = em.merge(idForma);
            }
            em.remove(pagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pagamento> findPagamentoEntities() {
        return findPagamentoEntities(true, -1, -1);
    }

    public List<Pagamento> findPagamentoEntities(int maxResults, int firstResult) {
        return findPagamentoEntities(false, maxResults, firstResult);
    }

    private List<Pagamento> findPagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pagamento.class));
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

    public Pagamento findPagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pagamento> rt = cq.from(Pagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
