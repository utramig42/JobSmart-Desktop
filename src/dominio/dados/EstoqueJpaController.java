/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.dados;

import dominio.Estoque;
import dominio.EstoquePK;
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
import dominio.dados.exceptions.PreexistingEntityException;
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

    public void create(Estoque estoque) throws PreexistingEntityException, Exception {
        if (estoque.getEstoquePK() == null) {
            estoque.setEstoquePK(new EstoquePK());
        }
        if (estoque.getItensVendaList() == null) {
            estoque.setItensVendaList(new ArrayList<ItensVenda>());
        }
        estoque.getEstoquePK().setIdProd(estoque.getProduto().getIdProd());
        estoque.getEstoquePK().setIdFor(estoque.getFornecedor().getIdFor());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor fornecedor = estoque.getFornecedor();
            if (fornecedor != null) {
                fornecedor = em.getReference(fornecedor.getClass(), fornecedor.getIdFor());
                estoque.setFornecedor(fornecedor);
            }
            Produto produto = estoque.getProduto();
            if (produto != null) {
                produto = em.getReference(produto.getClass(), produto.getIdProd());
                estoque.setProduto(produto);
            }
            List<ItensVenda> attachedItensVendaList = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListItensVendaToAttach : estoque.getItensVendaList()) {
                itensVendaListItensVendaToAttach = em.getReference(itensVendaListItensVendaToAttach.getClass(), itensVendaListItensVendaToAttach.getItensVendaPK());
                attachedItensVendaList.add(itensVendaListItensVendaToAttach);
            }
            estoque.setItensVendaList(attachedItensVendaList);
            em.persist(estoque);
            if (fornecedor != null) {
                fornecedor.getEstoqueList().add(estoque);
                fornecedor = em.merge(fornecedor);
            }
            if (produto != null) {
                produto.getEstoqueList().add(estoque);
                produto = em.merge(produto);
            }
            for (ItensVenda itensVendaListItensVenda : estoque.getItensVendaList()) {
                Estoque oldEstoqueOfItensVendaListItensVenda = itensVendaListItensVenda.getEstoque();
                itensVendaListItensVenda.setEstoque(estoque);
                itensVendaListItensVenda = em.merge(itensVendaListItensVenda);
                if (oldEstoqueOfItensVendaListItensVenda != null) {
                    oldEstoqueOfItensVendaListItensVenda.getItensVendaList().remove(itensVendaListItensVenda);
                    oldEstoqueOfItensVendaListItensVenda = em.merge(oldEstoqueOfItensVendaListItensVenda);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstoque(estoque.getEstoquePK()) != null) {
                throw new PreexistingEntityException("Estoque " + estoque + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estoque estoque) throws IllegalOrphanException, NonexistentEntityException, Exception {
        estoque.getEstoquePK().setIdProd(estoque.getProduto().getIdProd());
        estoque.getEstoquePK().setIdFor(estoque.getFornecedor().getIdFor());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estoque persistentEstoque = em.find(Estoque.class, estoque.getEstoquePK());
            Fornecedor fornecedorOld = persistentEstoque.getFornecedor();
            Fornecedor fornecedorNew = estoque.getFornecedor();
            Produto produtoOld = persistentEstoque.getProduto();
            Produto produtoNew = estoque.getProduto();
            List<ItensVenda> itensVendaListOld = persistentEstoque.getItensVendaList();
            List<ItensVenda> itensVendaListNew = estoque.getItensVendaList();
            List<String> illegalOrphanMessages = null;
            for (ItensVenda itensVendaListOldItensVenda : itensVendaListOld) {
                if (!itensVendaListNew.contains(itensVendaListOldItensVenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ItensVenda " + itensVendaListOldItensVenda + " since its estoque field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fornecedorNew != null) {
                fornecedorNew = em.getReference(fornecedorNew.getClass(), fornecedorNew.getIdFor());
                estoque.setFornecedor(fornecedorNew);
            }
            if (produtoNew != null) {
                produtoNew = em.getReference(produtoNew.getClass(), produtoNew.getIdProd());
                estoque.setProduto(produtoNew);
            }
            List<ItensVenda> attachedItensVendaListNew = new ArrayList<ItensVenda>();
            for (ItensVenda itensVendaListNewItensVendaToAttach : itensVendaListNew) {
                itensVendaListNewItensVendaToAttach = em.getReference(itensVendaListNewItensVendaToAttach.getClass(), itensVendaListNewItensVendaToAttach.getItensVendaPK());
                attachedItensVendaListNew.add(itensVendaListNewItensVendaToAttach);
            }
            itensVendaListNew = attachedItensVendaListNew;
            estoque.setItensVendaList(itensVendaListNew);
            estoque = em.merge(estoque);
            if (fornecedorOld != null && !fornecedorOld.equals(fornecedorNew)) {
                fornecedorOld.getEstoqueList().remove(estoque);
                fornecedorOld = em.merge(fornecedorOld);
            }
            if (fornecedorNew != null && !fornecedorNew.equals(fornecedorOld)) {
                fornecedorNew.getEstoqueList().add(estoque);
                fornecedorNew = em.merge(fornecedorNew);
            }
            if (produtoOld != null && !produtoOld.equals(produtoNew)) {
                produtoOld.getEstoqueList().remove(estoque);
                produtoOld = em.merge(produtoOld);
            }
            if (produtoNew != null && !produtoNew.equals(produtoOld)) {
                produtoNew.getEstoqueList().add(estoque);
                produtoNew = em.merge(produtoNew);
            }
            for (ItensVenda itensVendaListNewItensVenda : itensVendaListNew) {
                if (!itensVendaListOld.contains(itensVendaListNewItensVenda)) {
                    Estoque oldEstoqueOfItensVendaListNewItensVenda = itensVendaListNewItensVenda.getEstoque();
                    itensVendaListNewItensVenda.setEstoque(estoque);
                    itensVendaListNewItensVenda = em.merge(itensVendaListNewItensVenda);
                    if (oldEstoqueOfItensVendaListNewItensVenda != null && !oldEstoqueOfItensVendaListNewItensVenda.equals(estoque)) {
                        oldEstoqueOfItensVendaListNewItensVenda.getItensVendaList().remove(itensVendaListNewItensVenda);
                        oldEstoqueOfItensVendaListNewItensVenda = em.merge(oldEstoqueOfItensVendaListNewItensVenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EstoquePK id = estoque.getEstoquePK();
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

    public void destroy(EstoquePK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estoque estoque;
            try {
                estoque = em.getReference(Estoque.class, id);
                estoque.getEstoquePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estoque with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ItensVenda> itensVendaListOrphanCheck = estoque.getItensVendaList();
            for (ItensVenda itensVendaListOrphanCheckItensVenda : itensVendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estoque (" + estoque + ") cannot be destroyed since the ItensVenda " + itensVendaListOrphanCheckItensVenda + " in its itensVendaList field has a non-nullable estoque field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Fornecedor fornecedor = estoque.getFornecedor();
            if (fornecedor != null) {
                fornecedor.getEstoqueList().remove(estoque);
                fornecedor = em.merge(fornecedor);
            }
            Produto produto = estoque.getProduto();
            if (produto != null) {
                produto.getEstoqueList().remove(estoque);
                produto = em.merge(produto);
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

    public Estoque findEstoque(EstoquePK id) {
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
