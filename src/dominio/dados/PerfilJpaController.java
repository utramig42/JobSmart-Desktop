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
import dominio.Perfil;
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
public class PerfilJpaController implements Serializable {

    public PerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perfil perfil) {
        if (perfil.getCargoList() == null) {
            perfil.setCargoList(new ArrayList<Cargo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cargo> attachedCargoList = new ArrayList<Cargo>();
            for (Cargo cargoListCargoToAttach : perfil.getCargoList()) {
                cargoListCargoToAttach = em.getReference(cargoListCargoToAttach.getClass(), cargoListCargoToAttach.getIdCargo());
                attachedCargoList.add(cargoListCargoToAttach);
            }
            perfil.setCargoList(attachedCargoList);
            em.persist(perfil);
            for (Cargo cargoListCargo : perfil.getCargoList()) {
                Perfil oldIdPerfilOfCargoListCargo = cargoListCargo.getIdPerfil();
                cargoListCargo.setIdPerfil(perfil);
                cargoListCargo = em.merge(cargoListCargo);
                if (oldIdPerfilOfCargoListCargo != null) {
                    oldIdPerfilOfCargoListCargo.getCargoList().remove(cargoListCargo);
                    oldIdPerfilOfCargoListCargo = em.merge(oldIdPerfilOfCargoListCargo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfil perfil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perfil persistentPerfil = em.find(Perfil.class, perfil.getIdPerfil());
            List<Cargo> cargoListOld = persistentPerfil.getCargoList();
            List<Cargo> cargoListNew = perfil.getCargoList();
            List<String> illegalOrphanMessages = null;
            for (Cargo cargoListOldCargo : cargoListOld) {
                if (!cargoListNew.contains(cargoListOldCargo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cargo " + cargoListOldCargo + " since its idPerfil field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cargo> attachedCargoListNew = new ArrayList<Cargo>();
            for (Cargo cargoListNewCargoToAttach : cargoListNew) {
                cargoListNewCargoToAttach = em.getReference(cargoListNewCargoToAttach.getClass(), cargoListNewCargoToAttach.getIdCargo());
                attachedCargoListNew.add(cargoListNewCargoToAttach);
            }
            cargoListNew = attachedCargoListNew;
            perfil.setCargoList(cargoListNew);
            perfil = em.merge(perfil);
            for (Cargo cargoListNewCargo : cargoListNew) {
                if (!cargoListOld.contains(cargoListNewCargo)) {
                    Perfil oldIdPerfilOfCargoListNewCargo = cargoListNewCargo.getIdPerfil();
                    cargoListNewCargo.setIdPerfil(perfil);
                    cargoListNewCargo = em.merge(cargoListNewCargo);
                    if (oldIdPerfilOfCargoListNewCargo != null && !oldIdPerfilOfCargoListNewCargo.equals(perfil)) {
                        oldIdPerfilOfCargoListNewCargo.getCargoList().remove(cargoListNewCargo);
                        oldIdPerfilOfCargoListNewCargo = em.merge(oldIdPerfilOfCargoListNewCargo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perfil.getIdPerfil();
                if (findPerfil(id) == null) {
                    throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.");
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
            Perfil perfil;
            try {
                perfil = em.getReference(Perfil.class, id);
                perfil.getIdPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cargo> cargoListOrphanCheck = perfil.getCargoList();
            for (Cargo cargoListOrphanCheckCargo : cargoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perfil (" + perfil + ") cannot be destroyed since the Cargo " + cargoListOrphanCheckCargo + " in its cargoList field has a non-nullable idPerfil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(perfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfil> findPerfilEntities() {
        return findPerfilEntities(true, -1, -1);
    }

    public List<Perfil> findPerfilEntities(int maxResults, int firstResult) {
        return findPerfilEntities(false, maxResults, firstResult);
    }

    private List<Perfil> findPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfil.class));
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

    public Perfil findPerfil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfil> rt = cq.from(Perfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
