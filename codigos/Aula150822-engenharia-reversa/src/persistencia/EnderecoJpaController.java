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
import negocio.Endereco;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author iapereira
 */
public class EnderecoJpaController implements Serializable {

    public EnderecoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Endereco endereco) {
        if (endereco.getPessoaList() == null) {
            endereco.setPessoaList(new ArrayList<Pessoa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pessoa> attachedPessoaList = new ArrayList<Pessoa>();
            for (Pessoa pessoaListPessoaToAttach : endereco.getPessoaList()) {
                pessoaListPessoaToAttach = em.getReference(pessoaListPessoaToAttach.getClass(), pessoaListPessoaToAttach.getId());
                attachedPessoaList.add(pessoaListPessoaToAttach);
            }
            endereco.setPessoaList(attachedPessoaList);
            em.persist(endereco);
            for (Pessoa pessoaListPessoa : endereco.getPessoaList()) {
                Endereco oldEnderecoIdOfPessoaListPessoa = pessoaListPessoa.getEnderecoId();
                pessoaListPessoa.setEnderecoId(endereco);
                pessoaListPessoa = em.merge(pessoaListPessoa);
                if (oldEnderecoIdOfPessoaListPessoa != null) {
                    oldEnderecoIdOfPessoaListPessoa.getPessoaList().remove(pessoaListPessoa);
                    oldEnderecoIdOfPessoaListPessoa = em.merge(oldEnderecoIdOfPessoaListPessoa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Endereco endereco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Endereco persistentEndereco = em.find(Endereco.class, endereco.getId());
            List<Pessoa> pessoaListOld = persistentEndereco.getPessoaList();
            List<Pessoa> pessoaListNew = endereco.getPessoaList();
            List<Pessoa> attachedPessoaListNew = new ArrayList<Pessoa>();
            for (Pessoa pessoaListNewPessoaToAttach : pessoaListNew) {
                pessoaListNewPessoaToAttach = em.getReference(pessoaListNewPessoaToAttach.getClass(), pessoaListNewPessoaToAttach.getId());
                attachedPessoaListNew.add(pessoaListNewPessoaToAttach);
            }
            pessoaListNew = attachedPessoaListNew;
            endereco.setPessoaList(pessoaListNew);
            endereco = em.merge(endereco);
            for (Pessoa pessoaListOldPessoa : pessoaListOld) {
                if (!pessoaListNew.contains(pessoaListOldPessoa)) {
                    pessoaListOldPessoa.setEnderecoId(null);
                    pessoaListOldPessoa = em.merge(pessoaListOldPessoa);
                }
            }
            for (Pessoa pessoaListNewPessoa : pessoaListNew) {
                if (!pessoaListOld.contains(pessoaListNewPessoa)) {
                    Endereco oldEnderecoIdOfPessoaListNewPessoa = pessoaListNewPessoa.getEnderecoId();
                    pessoaListNewPessoa.setEnderecoId(endereco);
                    pessoaListNewPessoa = em.merge(pessoaListNewPessoa);
                    if (oldEnderecoIdOfPessoaListNewPessoa != null && !oldEnderecoIdOfPessoaListNewPessoa.equals(endereco)) {
                        oldEnderecoIdOfPessoaListNewPessoa.getPessoaList().remove(pessoaListNewPessoa);
                        oldEnderecoIdOfPessoaListNewPessoa = em.merge(oldEnderecoIdOfPessoaListNewPessoa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = endereco.getId();
                if (findEndereco(id) == null) {
                    throw new NonexistentEntityException("The endereco with id " + id + " no longer exists.");
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
            Endereco endereco;
            try {
                endereco = em.getReference(Endereco.class, id);
                endereco.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The endereco with id " + id + " no longer exists.", enfe);
            }
            List<Pessoa> pessoaList = endereco.getPessoaList();
            for (Pessoa pessoaListPessoa : pessoaList) {
                pessoaListPessoa.setEnderecoId(null);
                pessoaListPessoa = em.merge(pessoaListPessoa);
            }
            em.remove(endereco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Endereco> findEnderecoEntities() {
        return findEnderecoEntities(true, -1, -1);
    }

    public List<Endereco> findEnderecoEntities(int maxResults, int firstResult) {
        return findEnderecoEntities(false, maxResults, firstResult);
    }

    private List<Endereco> findEnderecoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Endereco.class));
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

    public Endereco findEndereco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Endereco.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnderecoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Endereco> rt = cq.from(Endereco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
