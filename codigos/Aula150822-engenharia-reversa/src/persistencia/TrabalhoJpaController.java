/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import negocio.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import negocio.Trabalho;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author iapereira
 */
public class TrabalhoJpaController implements Serializable {

    public TrabalhoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Trabalho trabalho) {
        if (trabalho.getPessoaList() == null) {
            trabalho.setPessoaList(new ArrayList<Pessoa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pessoa> attachedPessoaList = new ArrayList<Pessoa>();
            for (Pessoa pessoaListPessoaToAttach : trabalho.getPessoaList()) {
                pessoaListPessoaToAttach = em.getReference(pessoaListPessoaToAttach.getClass(), pessoaListPessoaToAttach.getId());
                attachedPessoaList.add(pessoaListPessoaToAttach);
            }
            trabalho.setPessoaList(attachedPessoaList);
            em.persist(trabalho);
            for (Pessoa pessoaListPessoa : trabalho.getPessoaList()) {
                pessoaListPessoa.getTrabalhoList().add(trabalho);
                pessoaListPessoa = em.merge(pessoaListPessoa);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Trabalho trabalho) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabalho persistentTrabalho = em.find(Trabalho.class, trabalho.getId());
            List<Pessoa> pessoaListOld = persistentTrabalho.getPessoaList();
            List<Pessoa> pessoaListNew = trabalho.getPessoaList();
            List<Pessoa> attachedPessoaListNew = new ArrayList<Pessoa>();
            for (Pessoa pessoaListNewPessoaToAttach : pessoaListNew) {
                pessoaListNewPessoaToAttach = em.getReference(pessoaListNewPessoaToAttach.getClass(), pessoaListNewPessoaToAttach.getId());
                attachedPessoaListNew.add(pessoaListNewPessoaToAttach);
            }
            pessoaListNew = attachedPessoaListNew;
            trabalho.setPessoaList(pessoaListNew);
            trabalho = em.merge(trabalho);
            for (Pessoa pessoaListOldPessoa : pessoaListOld) {
                if (!pessoaListNew.contains(pessoaListOldPessoa)) {
                    pessoaListOldPessoa.getTrabalhoList().remove(trabalho);
                    pessoaListOldPessoa = em.merge(pessoaListOldPessoa);
                }
            }
            for (Pessoa pessoaListNewPessoa : pessoaListNew) {
                if (!pessoaListOld.contains(pessoaListNewPessoa)) {
                    pessoaListNewPessoa.getTrabalhoList().add(trabalho);
                    pessoaListNewPessoa = em.merge(pessoaListNewPessoa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = trabalho.getId();
                if (findTrabalho(id) == null) {
                    throw new NonexistentEntityException("The trabalho with id " + id + " no longer exists.");
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
            Trabalho trabalho;
            try {
                trabalho = em.getReference(Trabalho.class, id);
                trabalho.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trabalho with id " + id + " no longer exists.", enfe);
            }
            List<Pessoa> pessoaList = trabalho.getPessoaList();
            for (Pessoa pessoaListPessoa : pessoaList) {
                pessoaListPessoa.getTrabalhoList().remove(trabalho);
                pessoaListPessoa = em.merge(pessoaListPessoa);
            }
            em.remove(trabalho);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Trabalho> findTrabalhoEntities() {
        return findTrabalhoEntities(true, -1, -1);
    }

    public List<Trabalho> findTrabalhoEntities(int maxResults, int firstResult) {
        return findTrabalhoEntities(false, maxResults, firstResult);
    }

    private List<Trabalho> findTrabalhoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Trabalho.class));
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

    public Trabalho findTrabalho(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Trabalho.class, id);
        } finally {
            em.close();
        }
    }

    public int getTrabalhoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Trabalho> rt = cq.from(Trabalho.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
