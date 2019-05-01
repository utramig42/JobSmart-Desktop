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
import dominio.ItensVendaPK;
import dominio.dados.exceptions.NonexistentEntityException;
import dominio.dados.exceptions.PreexistingEntityException;
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

    public void create(ItensVenda itensVenda) throws PreexistingEntityException, Exception {
        if (itensVenda.getItensVendaPK() == null) {
            itensVenda.setItensVendaPK(new ItensVendaPK());
        }
        itensVenda.getItensVendaPK().setIdFor(itensVenda.getEstoque().getEstoquePK().getIdFor());
        itensVenda.getItensVendaPK().setIdEst(itensVenda.getEstoque().getEstoquePK().getIdEst());
        itensVenda.getItensVendaPK().setIdProd(itensVenda.getEstoque().getEstoquePK().getIdProd());
        itensVenda.getItensVendaPK().setIdVenda(itensVenda.getVenda().getIdVenda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda venda = itensVenda.getVenda();
            if (venda != null) {
                venda = em.getReference(venda.getClass(), venda.getIdVenda());
                itensVenda.setVenda(venda);
            }
            Estoque estoque = itensVenda.getEstoque();
            if (estoque != null) {
                estoque = em.getReference(estoque.getClass(), estoque.getEstoquePK());
                itensVenda.setEstoque(estoque);
            }
            em.persist(itensVenda);
            if (venda != null) {
                venda.getItensVendaList().add(itensVenda);
                venda = em.merge(venda);
            }
            if (estoque != null) {
                estoque.getItensVendaList().add(itensVenda);
                estoque = em.merge(estoque);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findItensVenda(itensVenda.getItensVendaPK()) != null) {
                throw new PreexistingEntityException("ItensVenda " + itensVenda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItensVenda itensVenda) throws NonexistentEntityException, Exception {
        itensVenda.getItensVendaPK().setIdFor(itensVenda.getEstoque().getEstoquePK().getIdFor());
        itensVenda.getItensVendaPK().setIdEst(itensVenda.getEstoque().getEstoquePK().getIdEst());
        itensVenda.getItensVendaPK().setIdProd(itensVenda.getEstoque().getEstoquePK().getIdProd());
        itensVenda.getItensVendaPK().setIdVenda(itensVenda.getVenda().getIdVenda());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensVenda persistentItensVenda = em.find(ItensVenda.class, itensVenda.getItensVendaPK());
            Venda vendaOld = persistentItensVenda.getVenda();
            Venda vendaNew = itensVenda.getVenda();
            Estoque estoqueOld = persistentItensVenda.getEstoque();
            Estoque estoqueNew = itensVenda.getEstoque();
            if (vendaNew != null) {
                vendaNew = em.getReference(vendaNew.getClass(), vendaNew.getIdVenda());
                itensVenda.setVenda(vendaNew);
            }
            if (estoqueNew != null) {
                estoqueNew = em.getReference(estoqueNew.getClass(), estoqueNew.getEstoquePK());
                itensVenda.setEstoque(estoqueNew);
            }
            itensVenda = em.merge(itensVenda);
            if (vendaOld != null && !vendaOld.equals(vendaNew)) {
                vendaOld.getItensVendaList().remove(itensVenda);
                vendaOld = em.merge(vendaOld);
            }
            if (vendaNew != null && !vendaNew.equals(vendaOld)) {
                vendaNew.getItensVendaList().add(itensVenda);
                vendaNew = em.merge(vendaNew);
            }
            if (estoqueOld != null && !estoqueOld.equals(estoqueNew)) {
                estoqueOld.getItensVendaList().remove(itensVenda);
                estoqueOld = em.merge(estoqueOld);
            }
            if (estoqueNew != null && !estoqueNew.equals(estoqueOld)) {
                estoqueNew.getItensVendaList().add(itensVenda);
                estoqueNew = em.merge(estoqueNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ItensVendaPK id = itensVenda.getItensVendaPK();
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

    public void destroy(ItensVendaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItensVenda itensVenda;
            try {
                itensVenda = em.getReference(ItensVenda.class, id);
                itensVenda.getItensVendaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The itensVenda with id " + id + " no longer exists.", enfe);
            }
            Venda venda = itensVenda.getVenda();
            if (venda != null) {
                venda.getItensVendaList().remove(itensVenda);
                venda = em.merge(venda);
            }
            Estoque estoque = itensVenda.getEstoque();
            if (estoque != null) {
                estoque.getItensVendaList().remove(itensVenda);
                estoque = em.merge(estoque);
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

    public ItensVenda findItensVenda(ItensVendaPK id) {
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
