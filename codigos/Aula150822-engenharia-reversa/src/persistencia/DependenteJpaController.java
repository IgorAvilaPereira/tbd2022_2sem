/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.Dependente;
import negocio.Pessoa;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author iapereira
 */
public class DependenteJpaController implements Serializable {

    public DependenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dependente dependente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pessoa pessoaId = dependente.getPessoaId();
            if (pessoaId != null) {
                pessoaId = em.getReference(pessoaId.getClass(), pessoaId.getId());
                dependente.setPessoaId(pessoaId);
            }
            em.persist(dependente);
            if (pessoaId != null) {
                pessoaId.getDependenteList().add(dependente);
                pessoaId = em.merge(pessoaId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dependente dependente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dependente persistentDependente = em.find(Dependente.class, dependente.getId());
            Pessoa pessoaIdOld = persistentDependente.getPessoaId();
            Pessoa pessoaIdNew = dependente.getPessoaId();
            if (pessoaIdNew != null) {
                pessoaIdNew = em.getReference(pessoaIdNew.getClass(), pessoaIdNew.getId());
                dependente.setPessoaId(pessoaIdNew);
            }
            dependente = em.merge(dependente);
            if (pessoaIdOld != null && !pessoaIdOld.equals(pessoaIdNew)) {
                pessoaIdOld.getDependenteList().remove(dependente);
                pessoaIdOld = em.merge(pessoaIdOld);
            }
            if (pessoaIdNew != null && !pessoaIdNew.equals(pessoaIdOld)) {
                pessoaIdNew.getDependenteList().add(dependente);
                pessoaIdNew = em.merge(pessoaIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dependente.getId();
                if (findDependente(id) == null) {
                    throw new NonexistentEntityException("The dependente with id " + id + " no longer exists.");
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
            Dependente dependente;
            try {
                dependente = em.getReference(Dependente.class, id);
                dependente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dependente with id " + id + " no longer exists.", enfe);
            }
            Pessoa pessoaId = dependente.getPessoaId();
            if (pessoaId != null) {
                pessoaId.getDependenteList().remove(dependente);
                pessoaId = em.merge(pessoaId);
            }
            em.remove(dependente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dependente> findDependenteEntities() {
        return findDependenteEntities(true, -1, -1);
    }

    public List<Dependente> findDependenteEntities(int maxResults, int firstResult) {
        return findDependenteEntities(false, maxResults, firstResult);
    }

    private List<Dependente> findDependenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dependente.class));
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

    public Dependente findDependente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dependente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDependenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dependente> rt = cq.from(Dependente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
