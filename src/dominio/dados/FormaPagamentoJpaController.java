/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.dados;

import dominio.FormaPagamento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import dominio.Pagamento;
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
public class FormaPagamentoJpaController implements Serializable {

    public FormaPagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormaPagamento formaPagamento) throws PreexistingEntityException, Exception {
        if (formaPagamento.getPagamentoList() == null) {
            formaPagamento.setPagamentoList(new ArrayList<Pagamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pagamento> attachedPagamentoList = new ArrayList<Pagamento>();
            for (Pagamento pagamentoListPagamentoToAttach : formaPagamento.getPagamentoList()) {
                pagamentoListPagamentoToAttach = em.getReference(pagamentoListPagamentoToAttach.getClass(), pagamentoListPagamentoToAttach.getIdPag());
                attachedPagamentoList.add(pagamentoListPagamentoToAttach);
            }
            formaPagamento.setPagamentoList(attachedPagamentoList);
            em.persist(formaPagamento);
            for (Pagamento pagamentoListPagamento : formaPagamento.getPagamentoList()) {
                FormaPagamento oldIdFormaOfPagamentoListPagamento = pagamentoListPagamento.getIdForma();
                pagamentoListPagamento.setIdForma(formaPagamento);
                pagamentoListPagamento = em.merge(pagamentoListPagamento);
                if (oldIdFormaOfPagamentoListPagamento != null) {
                    oldIdFormaOfPagamentoListPagamento.getPagamentoList().remove(pagamentoListPagamento);
                    oldIdFormaOfPagamentoListPagamento = em.merge(oldIdFormaOfPagamentoListPagamento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormaPagamento(formaPagamento.getIdForma()) != null) {
                throw new PreexistingEntityException("FormaPagamento " + formaPagamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormaPagamento formaPagamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormaPagamento persistentFormaPagamento = em.find(FormaPagamento.class, formaPagamento.getIdForma());
            List<Pagamento> pagamentoListOld = persistentFormaPagamento.getPagamentoList();
            List<Pagamento> pagamentoListNew = formaPagamento.getPagamentoList();
            List<String> illegalOrphanMessages = null;
            for (Pagamento pagamentoListOldPagamento : pagamentoListOld) {
                if (!pagamentoListNew.contains(pagamentoListOldPagamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pagamento " + pagamentoListOldPagamento + " since its idForma field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pagamento> attachedPagamentoListNew = new ArrayList<Pagamento>();
            for (Pagamento pagamentoListNewPagamentoToAttach : pagamentoListNew) {
                pagamentoListNewPagamentoToAttach = em.getReference(pagamentoListNewPagamentoToAttach.getClass(), pagamentoListNewPagamentoToAttach.getIdPag());
                attachedPagamentoListNew.add(pagamentoListNewPagamentoToAttach);
            }
            pagamentoListNew = attachedPagamentoListNew;
            formaPagamento.setPagamentoList(pagamentoListNew);
            formaPagamento = em.merge(formaPagamento);
            for (Pagamento pagamentoListNewPagamento : pagamentoListNew) {
                if (!pagamentoListOld.contains(pagamentoListNewPagamento)) {
                    FormaPagamento oldIdFormaOfPagamentoListNewPagamento = pagamentoListNewPagamento.getIdForma();
                    pagamentoListNewPagamento.setIdForma(formaPagamento);
                    pagamentoListNewPagamento = em.merge(pagamentoListNewPagamento);
                    if (oldIdFormaOfPagamentoListNewPagamento != null && !oldIdFormaOfPagamentoListNewPagamento.equals(formaPagamento)) {
                        oldIdFormaOfPagamentoListNewPagamento.getPagamentoList().remove(pagamentoListNewPagamento);
                        oldIdFormaOfPagamentoListNewPagamento = em.merge(oldIdFormaOfPagamentoListNewPagamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formaPagamento.getIdForma();
                if (findFormaPagamento(id) == null) {
                    throw new NonexistentEntityException("The formaPagamento with id " + id + " no longer exists.");
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
            FormaPagamento formaPagamento;
            try {
                formaPagamento = em.getReference(FormaPagamento.class, id);
                formaPagamento.getIdForma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formaPagamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pagamento> pagamentoListOrphanCheck = formaPagamento.getPagamentoList();
            for (Pagamento pagamentoListOrphanCheckPagamento : pagamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This FormaPagamento (" + formaPagamento + ") cannot be destroyed since the Pagamento " + pagamentoListOrphanCheckPagamento + " in its pagamentoList field has a non-nullable idForma field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(formaPagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormaPagamento> findFormaPagamentoEntities() {
        return findFormaPagamentoEntities(true, -1, -1);
    }

    public List<FormaPagamento> findFormaPagamentoEntities(int maxResults, int firstResult) {
        return findFormaPagamentoEntities(false, maxResults, firstResult);
    }

    private List<FormaPagamento> findFormaPagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormaPagamento.class));
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

    public FormaPagamento findFormaPagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormaPagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormaPagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormaPagamento> rt = cq.from(FormaPagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
