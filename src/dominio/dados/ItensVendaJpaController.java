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
import dominio.Estoque;
import dominio.ItensVenda;
import dominio.dados.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author caioa
 */
public class ItensVendaJpaController implements Serializable {

    public ItensVendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItensVenda itensVenda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda idVenda = itensVenda.getIdVenda();
            if (idVenda != null) {
                idVenda = em.getReference(idVenda.getClass(), idVenda.getIdVenda());
                itensVenda.setIdVenda(idVenda);
            }
            Estoque idEst = itensVenda.getIdEst();
            if (idEst != null) {
                idEst = em.getReference(idEst.getClass(), idEst.getIdEst());
                itensVenda.setIdEst(idEst);
            }
            em.persist(itensVenda);
            if (idVenda != null) {
                idVenda.getItensVendaList().add(itensVenda);
                idVenda = em.merge(idVenda);
            }
            if (idEst != null) {
                idEst.getItensVendaList().add(itensVenda);
                idEst = em.merge(idEst);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void createWithList(List<ItensVenda> itens){
        for(ItensVenda item : itens){
            create(item);
        }
    }

    public void edit(ItensVenda itensVenda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensVenda persistentItensVenda = em.find(ItensVenda.class, itensVenda.getIditensvenda());
            Venda idVendaOld = persistentItensVenda.getIdVenda();
            Venda idVendaNew = itensVenda.getIdVenda();
            Estoque idEstOld = persistentItensVenda.getIdEst();
            Estoque idEstNew = itensVenda.getIdEst();
            if (idVendaNew != null) {
                idVendaNew = em.getReference(idVendaNew.getClass(), idVendaNew.getIdVenda());
                itensVenda.setIdVenda(idVendaNew);
            }
            if (idEstNew != null) {
                idEstNew = em.getReference(idEstNew.getClass(), idEstNew.getIdEst());
                itensVenda.setIdEst(idEstNew);
            }
            itensVenda = em.merge(itensVenda);
            if (idVendaOld != null && !idVendaOld.equals(idVendaNew)) {
                idVendaOld.getItensVendaList().remove(itensVenda);
                idVendaOld = em.merge(idVendaOld);
            }
            if (idVendaNew != null && !idVendaNew.equals(idVendaOld)) {
                idVendaNew.getItensVendaList().add(itensVenda);
                idVendaNew = em.merge(idVendaNew);
            }
            if (idEstOld != null && !idEstOld.equals(idEstNew)) {
                idEstOld.getItensVendaList().remove(itensVenda);
                idEstOld = em.merge(idEstOld);
            }
            if (idEstNew != null && !idEstNew.equals(idEstOld)) {
                idEstNew.getItensVendaList().add(itensVenda);
                idEstNew = em.merge(idEstNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itensVenda.getIditensvenda();
                if (findItensVenda(id) == null) {
                    throw new NonexistentEntityException("The itensVenda with id " + id + " no longer exists.");
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
            ItensVenda itensVenda;
            try {
                itensVenda = em.getReference(ItensVenda.class, id);
                itensVenda.getIditensvenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itensVenda with id " + id + " no longer exists.", enfe);
            }
            Venda idVenda = itensVenda.getIdVenda();
            if (idVenda != null) {
                idVenda.getItensVendaList().remove(itensVenda);
                idVenda = em.merge(idVenda);
            }
            Estoque idEst = itensVenda.getIdEst();
            if (idEst != null) {
                idEst.getItensVendaList().remove(itensVenda);
                idEst = em.merge(idEst);
            }
            em.remove(itensVenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItensVenda> findItensVendaEntities() {
        return findItensVendaEntities(true, -1, -1);
    }

    public List<ItensVenda> findItensVendaEntities(int maxResults, int firstResult) {
        return findItensVendaEntities(false, maxResults, firstResult);
    }

    private List<ItensVenda> findItensVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItensVenda.class));
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

    public ItensVenda findItensVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItensVenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getItensVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItensVenda> rt = cq.from(ItensVenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
