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
import dominio.Estoque;
import dominio.Fornecedor;
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
public class FornecedorJpaController implements Serializable {

    public FornecedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fornecedor fornecedor) {
        if (fornecedor.getEstoqueList() == null) {
            fornecedor.setEstoqueList(new ArrayList<Estoque>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estoque> attachedEstoqueList = new ArrayList<Estoque>();
            for (Estoque estoqueListEstoqueToAttach : fornecedor.getEstoqueList()) {
                estoqueListEstoqueToAttach = em.getReference(estoqueListEstoqueToAttach.getClass(), estoqueListEstoqueToAttach.getEstoquePK());
                attachedEstoqueList.add(estoqueListEstoqueToAttach);
            }
            fornecedor.setEstoqueList(attachedEstoqueList);
            em.persist(fornecedor);
            for (Estoque estoqueListEstoque : fornecedor.getEstoqueList()) {
                Fornecedor oldFornecedorOfEstoqueListEstoque = estoqueListEstoque.getFornecedor();
                estoqueListEstoque.setFornecedor(fornecedor);
                estoqueListEstoque = em.merge(estoqueListEstoque);
                if (oldFornecedorOfEstoqueListEstoque != null) {
                    oldFornecedorOfEstoqueListEstoque.getEstoqueList().remove(estoqueListEstoque);
                    oldFornecedorOfEstoqueListEstoque = em.merge(oldFornecedorOfEstoqueListEstoque);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fornecedor fornecedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor persistentFornecedor = em.find(Fornecedor.class, fornecedor.getIdFor());
            List<Estoque> estoqueListOld = persistentFornecedor.getEstoqueList();
            List<Estoque> estoqueListNew = fornecedor.getEstoqueList();
            List<String> illegalOrphanMessages = null;
            for (Estoque estoqueListOldEstoque : estoqueListOld) {
                if (!estoqueListNew.contains(estoqueListOldEstoque)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estoque " + estoqueListOldEstoque + " since its fornecedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estoque> attachedEstoqueListNew = new ArrayList<Estoque>();
            for (Estoque estoqueListNewEstoqueToAttach : estoqueListNew) {
                estoqueListNewEstoqueToAttach = em.getReference(estoqueListNewEstoqueToAttach.getClass(), estoqueListNewEstoqueToAttach.getEstoquePK());
                attachedEstoqueListNew.add(estoqueListNewEstoqueToAttach);
            }
            estoqueListNew = attachedEstoqueListNew;
            fornecedor.setEstoqueList(estoqueListNew);
            fornecedor = em.merge(fornecedor);
            for (Estoque estoqueListNewEstoque : estoqueListNew) {
                if (!estoqueListOld.contains(estoqueListNewEstoque)) {
                    Fornecedor oldFornecedorOfEstoqueListNewEstoque = estoqueListNewEstoque.getFornecedor();
                    estoqueListNewEstoque.setFornecedor(fornecedor);
                    estoqueListNewEstoque = em.merge(estoqueListNewEstoque);
                    if (oldFornecedorOfEstoqueListNewEstoque != null && !oldFornecedorOfEstoqueListNewEstoque.equals(fornecedor)) {
                        oldFornecedorOfEstoqueListNewEstoque.getEstoqueList().remove(estoqueListNewEstoque);
                        oldFornecedorOfEstoqueListNewEstoque = em.merge(oldFornecedorOfEstoqueListNewEstoque);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fornecedor.getIdFor();
                if (findFornecedor(id) == null) {
                    throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.");
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
            Fornecedor fornecedor;
            try {
                fornecedor = em.getReference(Fornecedor.class, id);
                fornecedor.getIdFor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estoque> estoqueListOrphanCheck = fornecedor.getEstoqueList();
            for (Estoque estoqueListOrphanCheckEstoque : estoqueListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fornecedor (" + fornecedor + ") cannot be destroyed since the Estoque " + estoqueListOrphanCheckEstoque + " in its estoqueList field has a non-nullable fornecedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(fornecedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fornecedor> findFornecedorEntities() {
        return findFornecedorEntities(true, -1, -1);
    }

    public List<Fornecedor> findFornecedorEntities(int maxResults, int firstResult) {
        return findFornecedorEntities(false, maxResults, firstResult);
    }

    private List<Fornecedor> findFornecedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fornecedor.class));
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

    public Fornecedor findFornecedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getFornecedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fornecedor> rt = cq.from(Fornecedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
