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
import dominio.Categoria;
import dominio.Marca;
import dominio.Estoque;
import dominio.Produto;
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
public class ProdutoJpaController implements Serializable {

    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) {
        if (produto.getEstoqueList() == null) {
            produto.setEstoqueList(new ArrayList<Estoque>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria idCat = produto.getIdCat();
            if (idCat != null) {
                idCat = em.getReference(idCat.getClass(), idCat.getIdCat());
                produto.setIdCat(idCat);
            }
            Marca idMarca = produto.getIdMarca();
            if (idMarca != null) {
                idMarca = em.getReference(idMarca.getClass(), idMarca.getIdMarca());
                produto.setIdMarca(idMarca);
            }
            List<Estoque> attachedEstoqueList = new ArrayList<Estoque>();
            for (Estoque estoqueListEstoqueToAttach : produto.getEstoqueList()) {
                estoqueListEstoqueToAttach = em.getReference(estoqueListEstoqueToAttach.getClass(), estoqueListEstoqueToAttach.getEstoquePK());
                attachedEstoqueList.add(estoqueListEstoqueToAttach);
            }
            produto.setEstoqueList(attachedEstoqueList);
            em.persist(produto);
            if (idCat != null) {
                idCat.getProdutoList().add(produto);
                idCat = em.merge(idCat);
            }
            if (idMarca != null) {
                idMarca.getProdutoList().add(produto);
                idMarca = em.merge(idMarca);
            }
            for (Estoque estoqueListEstoque : produto.getEstoqueList()) {
                Produto oldProdutoOfEstoqueListEstoque = estoqueListEstoque.getProduto();
                estoqueListEstoque.setProduto(produto);
                estoqueListEstoque = em.merge(estoqueListEstoque);
                if (oldProdutoOfEstoqueListEstoque != null) {
                    oldProdutoOfEstoqueListEstoque.getEstoqueList().remove(estoqueListEstoque);
                    oldProdutoOfEstoqueListEstoque = em.merge(oldProdutoOfEstoqueListEstoque);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produto produto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto persistentProduto = em.find(Produto.class, produto.getIdProd());
            Categoria idCatOld = persistentProduto.getIdCat();
            Categoria idCatNew = produto.getIdCat();
            Marca idMarcaOld = persistentProduto.getIdMarca();
            Marca idMarcaNew = produto.getIdMarca();
            List<Estoque> estoqueListOld = persistentProduto.getEstoqueList();
            List<Estoque> estoqueListNew = produto.getEstoqueList();
            List<String> illegalOrphanMessages = null;
            for (Estoque estoqueListOldEstoque : estoqueListOld) {
                if (!estoqueListNew.contains(estoqueListOldEstoque)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estoque " + estoqueListOldEstoque + " since its produto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCatNew != null) {
                idCatNew = em.getReference(idCatNew.getClass(), idCatNew.getIdCat());
                produto.setIdCat(idCatNew);
            }
            if (idMarcaNew != null) {
                idMarcaNew = em.getReference(idMarcaNew.getClass(), idMarcaNew.getIdMarca());
                produto.setIdMarca(idMarcaNew);
            }
            List<Estoque> attachedEstoqueListNew = new ArrayList<Estoque>();
            for (Estoque estoqueListNewEstoqueToAttach : estoqueListNew) {
                estoqueListNewEstoqueToAttach = em.getReference(estoqueListNewEstoqueToAttach.getClass(), estoqueListNewEstoqueToAttach.getEstoquePK());
                attachedEstoqueListNew.add(estoqueListNewEstoqueToAttach);
            }
            estoqueListNew = attachedEstoqueListNew;
            produto.setEstoqueList(estoqueListNew);
            produto = em.merge(produto);
            if (idCatOld != null && !idCatOld.equals(idCatNew)) {
                idCatOld.getProdutoList().remove(produto);
                idCatOld = em.merge(idCatOld);
            }
            if (idCatNew != null && !idCatNew.equals(idCatOld)) {
                idCatNew.getProdutoList().add(produto);
                idCatNew = em.merge(idCatNew);
            }
            if (idMarcaOld != null && !idMarcaOld.equals(idMarcaNew)) {
                idMarcaOld.getProdutoList().remove(produto);
                idMarcaOld = em.merge(idMarcaOld);
            }
            if (idMarcaNew != null && !idMarcaNew.equals(idMarcaOld)) {
                idMarcaNew.getProdutoList().add(produto);
                idMarcaNew = em.merge(idMarcaNew);
            }
            for (Estoque estoqueListNewEstoque : estoqueListNew) {
                if (!estoqueListOld.contains(estoqueListNewEstoque)) {
                    Produto oldProdutoOfEstoqueListNewEstoque = estoqueListNewEstoque.getProduto();
                    estoqueListNewEstoque.setProduto(produto);
                    estoqueListNewEstoque = em.merge(estoqueListNewEstoque);
                    if (oldProdutoOfEstoqueListNewEstoque != null && !oldProdutoOfEstoqueListNewEstoque.equals(produto)) {
                        oldProdutoOfEstoqueListNewEstoque.getEstoqueList().remove(estoqueListNewEstoque);
                        oldProdutoOfEstoqueListNewEstoque = em.merge(oldProdutoOfEstoqueListNewEstoque);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produto.getIdProd();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("The produto with id " + id + " no longer exists.");
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
            Produto produto;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getIdProd();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estoque> estoqueListOrphanCheck = produto.getEstoqueList();
            for (Estoque estoqueListOrphanCheckEstoque : estoqueListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the Estoque " + estoqueListOrphanCheckEstoque + " in its estoqueList field has a non-nullable produto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria idCat = produto.getIdCat();
            if (idCat != null) {
                idCat.getProdutoList().remove(produto);
                idCat = em.merge(idCat);
            }
            Marca idMarca = produto.getIdMarca();
            if (idMarca != null) {
                idMarca.getProdutoList().remove(produto);
                idMarca = em.merge(idMarca);
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
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

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
