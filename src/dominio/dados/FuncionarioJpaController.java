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
import dominio.Cargo;
import dominio.Acesso;
import dominio.Funcionario;
import java.util.ArrayList;
import java.util.List;
import dominio.Venda;
import dominio.dados.exceptions.IllegalOrphanException;
import dominio.dados.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author caioa
 */
public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionario funcionario) {
        if (funcionario.getAcessoList() == null) {
            funcionario.setAcessoList(new ArrayList<Acesso>());
        }
        if (funcionario.getVendaList() == null) {
            funcionario.setVendaList(new ArrayList<Venda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo idCargo = funcionario.getIdCargo();
            if (idCargo != null) {
                idCargo = em.getReference(idCargo.getClass(), idCargo.getIdCargo());
                funcionario.setIdCargo(idCargo);
            }
            List<Acesso> attachedAcessoList = new ArrayList<Acesso>();
            for (Acesso acessoListAcessoToAttach : funcionario.getAcessoList()) {
                acessoListAcessoToAttach = em.getReference(acessoListAcessoToAttach.getClass(), acessoListAcessoToAttach.getIdAcesso());
                attachedAcessoList.add(acessoListAcessoToAttach);
            }
            funcionario.setAcessoList(attachedAcessoList);
            List<Venda> attachedVendaList = new ArrayList<Venda>();
            for (Venda vendaListVendaToAttach : funcionario.getVendaList()) {
                vendaListVendaToAttach = em.getReference(vendaListVendaToAttach.getClass(), vendaListVendaToAttach.getIdVenda());
                attachedVendaList.add(vendaListVendaToAttach);
            }
            funcionario.setVendaList(attachedVendaList);
            em.persist(funcionario);
            if (idCargo != null) {
                idCargo.getFuncionarioList().add(funcionario);
                idCargo = em.merge(idCargo);
            }
            for (Acesso acessoListAcesso : funcionario.getAcessoList()) {
                Funcionario oldMatFunOfAcessoListAcesso = acessoListAcesso.getMatFun();
                acessoListAcesso.setMatFun(funcionario);
                acessoListAcesso = em.merge(acessoListAcesso);
                if (oldMatFunOfAcessoListAcesso != null) {
                    oldMatFunOfAcessoListAcesso.getAcessoList().remove(acessoListAcesso);
                    oldMatFunOfAcessoListAcesso = em.merge(oldMatFunOfAcessoListAcesso);
                }
            }
            for (Venda vendaListVenda : funcionario.getVendaList()) {
                Funcionario oldMatFunOfVendaListVenda = vendaListVenda.getMatFun();
                vendaListVenda.setMatFun(funcionario);
                vendaListVenda = em.merge(vendaListVenda);
                if (oldMatFunOfVendaListVenda != null) {
                    oldMatFunOfVendaListVenda.getVendaList().remove(vendaListVenda);
                    oldMatFunOfVendaListVenda = em.merge(oldMatFunOfVendaListVenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario persistentFuncionario = em.find(Funcionario.class, funcionario.getMatFun());
            Cargo idCargoOld = persistentFuncionario.getIdCargo();
            Cargo idCargoNew = funcionario.getIdCargo();
            List<Acesso> acessoListOld = persistentFuncionario.getAcessoList();
            List<Acesso> acessoListNew = funcionario.getAcessoList();
            List<Venda> vendaListOld = persistentFuncionario.getVendaList();
            List<Venda> vendaListNew = funcionario.getVendaList();
            List<String> illegalOrphanMessages = null;
            for (Acesso acessoListOldAcesso : acessoListOld) {
                if (!acessoListNew.contains(acessoListOldAcesso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Acesso " + acessoListOldAcesso + " since its matFun field is not nullable.");
                }
            }
            for (Venda vendaListOldVenda : vendaListOld) {
                if (!vendaListNew.contains(vendaListOldVenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venda " + vendaListOldVenda + " since its matFun field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idCargoNew != null) {
                idCargoNew = em.getReference(idCargoNew.getClass(), idCargoNew.getIdCargo());
                funcionario.setIdCargo(idCargoNew);
            }
            List<Acesso> attachedAcessoListNew = new ArrayList<Acesso>();
            for (Acesso acessoListNewAcessoToAttach : acessoListNew) {
                acessoListNewAcessoToAttach = em.getReference(acessoListNewAcessoToAttach.getClass(), acessoListNewAcessoToAttach.getIdAcesso());
                attachedAcessoListNew.add(acessoListNewAcessoToAttach);
            }
            acessoListNew = attachedAcessoListNew;
            funcionario.setAcessoList(acessoListNew);
            List<Venda> attachedVendaListNew = new ArrayList<Venda>();
            for (Venda vendaListNewVendaToAttach : vendaListNew) {
                vendaListNewVendaToAttach = em.getReference(vendaListNewVendaToAttach.getClass(), vendaListNewVendaToAttach.getIdVenda());
                attachedVendaListNew.add(vendaListNewVendaToAttach);
            }
            vendaListNew = attachedVendaListNew;
            funcionario.setVendaList(vendaListNew);
            funcionario = em.merge(funcionario);
            if (idCargoOld != null && !idCargoOld.equals(idCargoNew)) {
                idCargoOld.getFuncionarioList().remove(funcionario);
                idCargoOld = em.merge(idCargoOld);
            }
            if (idCargoNew != null && !idCargoNew.equals(idCargoOld)) {
                idCargoNew.getFuncionarioList().add(funcionario);
                idCargoNew = em.merge(idCargoNew);
            }
            for (Acesso acessoListNewAcesso : acessoListNew) {
                if (!acessoListOld.contains(acessoListNewAcesso)) {
                    Funcionario oldMatFunOfAcessoListNewAcesso = acessoListNewAcesso.getMatFun();
                    acessoListNewAcesso.setMatFun(funcionario);
                    acessoListNewAcesso = em.merge(acessoListNewAcesso);
                    if (oldMatFunOfAcessoListNewAcesso != null && !oldMatFunOfAcessoListNewAcesso.equals(funcionario)) {
                        oldMatFunOfAcessoListNewAcesso.getAcessoList().remove(acessoListNewAcesso);
                        oldMatFunOfAcessoListNewAcesso = em.merge(oldMatFunOfAcessoListNewAcesso);
                    }
                }
            }
            for (Venda vendaListNewVenda : vendaListNew) {
                if (!vendaListOld.contains(vendaListNewVenda)) {
                    Funcionario oldMatFunOfVendaListNewVenda = vendaListNewVenda.getMatFun();
                    vendaListNewVenda.setMatFun(funcionario);
                    vendaListNewVenda = em.merge(vendaListNewVenda);
                    if (oldMatFunOfVendaListNewVenda != null && !oldMatFunOfVendaListNewVenda.equals(funcionario)) {
                        oldMatFunOfVendaListNewVenda.getVendaList().remove(vendaListNewVenda);
                        oldMatFunOfVendaListNewVenda = em.merge(oldMatFunOfVendaListNewVenda);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionario.getMatFun();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
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
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getMatFun();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Acesso> acessoListOrphanCheck = funcionario.getAcessoList();
            for (Acesso acessoListOrphanCheckAcesso : acessoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionario (" + funcionario + ") cannot be destroyed since the Acesso " + acessoListOrphanCheckAcesso + " in its acessoList field has a non-nullable matFun field.");
            }
            List<Venda> vendaListOrphanCheck = funcionario.getVendaList();
            for (Venda vendaListOrphanCheckVenda : vendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionario (" + funcionario + ") cannot be destroyed since the Venda " + vendaListOrphanCheckVenda + " in its vendaList field has a non-nullable matFun field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cargo idCargo = funcionario.getIdCargo();
            if (idCargo != null) {
                idCargo.getFuncionarioList().remove(funcionario);
                idCargo = em.merge(idCargo);
            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
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

    public Funcionario findFuncionario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
