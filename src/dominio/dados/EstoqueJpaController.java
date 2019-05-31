/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.dados;

import dominio.Estoque;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dominio.Fornecedor;
import dominio.Produto;
import dominio.ItensVenda;
import dominio.dados.exceptions.IllegalOrphanException;
import dominio.dados.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author caioa
 */
public class EstoqueJpaController implements Serializable {

    public EstoqueJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estoque estoque) {
        if (estoque.getItensVendaList() == null) {
            estoque.setItensVendaList(new ArrayList<ItensVenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor idFor = estoque.getIdFor();
            if (idFor != null) {
                idFor = em.getReference(idFor.getClass(), idFor.getIdFor());
                estoque.setIdFor(idFor);
            }
            Produto idProd = estoque.getIdProd();
            if (idProd != null) {
                idProd = em.getReference(idProd.getClass(), idProd.getIdProd());
                estoque.setIdProd(idProd);
            }
            List<ItensVenda> attachedItensVendaList = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListItensVendaToAttach : estoque.getItensVendaList()) {
                itensVendaListItensVendaToAttach = em.getReference(itensVendaListItensVendaToAttach.getClass(), itensVendaListItensVendaToAttach.getIditensvenda());
                attachedItensVendaList.add(itensVendaListItensVendaToAttach);
            }
            estoque.setItensVendaList(attachedItensVendaList);
            em.persist(estoque);
            if (idFor != null) {
                idFor.getEstoqueList().add(estoque);
                idFor = em.merge(idFor);
            }
            if (idProd != null) {
                idProd.getEstoqueList().add(estoque);
                idProd = em.merge(idProd);
            }
            for (ItensVenda itensVendaListItensVenda : estoque.getItensVendaList()) {
                Estoque oldIdEstOfItensVendaListItensVenda = itensVendaListItensVenda.getIdEst();
                itensVendaListItensVenda.setIdEst(estoque);
                itensVendaListItensVenda = em.merge(itensVendaListItensVenda);
                if (oldIdEstOfItensVendaListItensVenda != null) {
                    oldIdEstOfItensVendaListItensVenda.getItensVendaList().remove(itensVendaListItensVenda);
                    oldIdEstOfItensVendaListItensVenda = em.merge(oldIdEstOfItensVendaListItensVenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estoque estoque) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estoque persistentEstoque = em.find(Estoque.class, estoque.getIdEst());
            Fornecedor idForOld = persistentEstoque.getIdFor();
            Fornecedor idForNew = estoque.getIdFor();
            Produto idProdOld = persistentEstoque.getIdProd();
            Produto idProdNew = estoque.getIdProd();
            List<ItensVenda> itensVendaListOld = persistentEstoque.getItensVendaList();
            List<ItensVenda> itensVendaListNew = estoque.getItensVendaList();
            List<String> illegalOrphanMessages = null;
            for (ItensVenda itensVendaListOldItensVenda : itensVendaListOld) {
                if (!itensVendaListNew.contains(itensVendaListOldItensVenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ItensVenda " + itensVendaListOldItensVenda + " since its idEst field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idForNew != null) {
                idForNew = em.getReference(idForNew.getClass(), idForNew.getIdFor());
                estoque.setIdFor(idForNew);
            }
            if (idProdNew != null) {
                idProdNew = em.getReference(idProdNew.getClass(), idProdNew.getIdProd());
                estoque.setIdProd(idProdNew);
            }
            List<ItensVenda> attachedItensVendaListNew = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListNewItensVendaToAttach : itensVendaListNew) {
                itensVendaListNewItensVendaToAttach = em.getReference(itensVendaListNewItensVendaToAttach.getClass(), itensVendaListNewItensVendaToAttach.getIditensvenda());
                attachedItensVendaListNew.add(itensVendaListNewItensVendaToAttach);
            }
            itensVendaListNew = attachedItensVendaListNew;
            estoque.setItensVendaList(itensVendaListNew);
            estoque = em.merge(estoque);
            if (idForOld != null && !idForOld.equals(idForNew)) {
                idForOld.getEstoqueList().remove(estoque);
                idForOld = em.merge(idForOld);
            }
            if (idForNew != null && !idForNew.equals(idForOld)) {
                idForNew.getEstoqueList().add(estoque);
                idForNew = em.merge(idForNew);
            }
            if (idProdOld != null && !idProdOld.equals(idProdNew)) {
                idProdOld.getEstoqueList().remove(estoque);
                idProdOld = em.merge(idProdOld);
            }
            if (idProdNew != null && !idProdNew.equals(idProdOld)) {
                idProdNew.getEstoqueList().add(estoque);
                idProdNew = em.merge(idProdNew);
            }
            for (ItensVenda itensVendaListNewItensVenda : itensVendaListNew) {
                if (!itensVendaListOld.contains(itensVendaListNewItensVenda)) {
                    Estoque oldIdEstOfItensVendaListNewItensVenda = itensVendaListNewItensVenda.getIdEst();
                    itensVendaListNewItensVenda.setIdEst(estoque);
                    itensVendaListNewItensVenda = em.merge(itensVendaListNewItensVenda);
                    if (oldIdEstOfItensVendaListNewItensVenda != null && !oldIdEstOfItensVendaListNewItensVenda.equals(estoque)) {
                        oldIdEstOfItensVendaListNewItensVenda.getItensVendaList().remove(itensVendaListNewItensVenda);
                        oldIdEstOfItensVendaListNewItensVenda = em.merge(oldIdEstOfItensVendaListNewItensVenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estoque.getIdEst();
                if (findEstoque(id) == null) {
                    throw new NonexistentEntityException("The estoque with id " + id + " no longer exists.");
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
            Estoque estoque;
            try {
                estoque = em.getReference(Estoque.class, id);
                estoque.getIdEst();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estoque with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ItensVenda> itensVendaListOrphanCheck = estoque.getItensVendaList();
            for (ItensVenda itensVendaListOrphanCheckItensVenda : itensVendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estoque (" + estoque + ") cannot be destroyed since the ItensVenda " + itensVendaListOrphanCheckItensVenda + " in its itensVendaList field has a non-nullable idEst field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Fornecedor idFor = estoque.getIdFor();
            if (idFor != null) {
                idFor.getEstoqueList().remove(estoque);
                idFor = em.merge(idFor);
            }
            Produto idProd = estoque.getIdProd();
            if (idProd != null) {
                idProd.getEstoqueList().remove(estoque);
                idProd = em.merge(idProd);
            }
            em.remove(estoque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estoque> findEstoqueEntities() {
        return findEstoqueEntities(true, -1, -1);
    }

    public List<Estoque> findEstoqueEntities(int maxResults, int firstResult) {
        return findEstoqueEntities(false, maxResults, firstResult);
    }

    private List<Estoque> findEstoqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estoque.class));
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

    public Estoque findEstoque(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estoque.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstoqueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estoque> rt = cq.from(Estoque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
