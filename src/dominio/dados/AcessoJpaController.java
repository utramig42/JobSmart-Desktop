/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.dados;

import dominio.Acesso;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dominio.Funcionario;
import dominio.dados.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author caioa
 */
public class AcessoJpaController implements Serializable {

    public AcessoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acesso acesso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario matFun = acesso.getMatFun();
            if (matFun != null) {
                matFun = em.getReference(matFun.getClass(), matFun.getMatFun());
                acesso.setMatFun(matFun);
            }
            em.persist(acesso);
            if (matFun != null) {
                matFun.getAcessoList().add(acesso);
                matFun = em.merge(matFun);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acesso acesso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acesso persistentAcesso = em.find(Acesso.class, acesso.getIdAcesso());
            Funcionario matFunOld = persistentAcesso.getMatFun();
            Funcionario matFunNew = acesso.getMatFun();
            if (matFunNew != null) {
                matFunNew = em.getReference(matFunNew.getClass(), matFunNew.getMatFun());
                acesso.setMatFun(matFunNew);
            }
            acesso = em.merge(acesso);
            if (matFunOld != null && !matFunOld.equals(matFunNew)) {
                matFunOld.getAcessoList().remove(acesso);
                matFunOld = em.merge(matFunOld);
            }
            if (matFunNew != null && !matFunNew.equals(matFunOld)) {
                matFunNew.getAcessoList().add(acesso);
                matFunNew = em.merge(matFunNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = acesso.getIdAcesso();
                if (findAcesso(id) == null) {
                    throw new NonexistentEntityException("The acesso with id " + id + " no longer exists.");
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
            Acesso acesso;
            try {
                acesso = em.getReference(Acesso.class, id);
                acesso.getIdAcesso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acesso with id " + id + " no longer exists.", enfe);
            }
            Funcionario matFun = acesso.getMatFun();
            if (matFun != null) {
                matFun.getAcessoList().remove(acesso);
                matFun = em.merge(matFun);
            }
            em.remove(acesso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acesso> findAcessoEntities() {
        return findAcessoEntities(true, -1, -1);
    }

    public List<Acesso> findAcessoEntities(int maxResults, int firstResult) {
        return findAcessoEntities(false, maxResults, firstResult);
    }

    private List<Acesso> findAcessoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acesso.class));
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

    public Acesso findAcesso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acesso.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcessoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acesso> rt = cq.from(Acesso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
