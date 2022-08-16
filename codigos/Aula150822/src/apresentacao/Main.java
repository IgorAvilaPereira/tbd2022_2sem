/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apresentacao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.Endereco;
import negocio.Pessoa;
import negocio.Trabalho;

/**
 *
 * @author iapereira
 */
public class Main {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MeuPU");
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        
        Pessoa p = new Pessoa();
        p.setNome("betito");
        p.setEndereco(new Endereco("cassino"));
        entityManager.persist(p);
        
        
//        Trabalho trabalho = entityManager.find(Trabalho.class, 1);
//        Pessoa igor = entityManager.find(Pessoa.class, 1);
//        trabalho.getFuncionarios().remove(igor);
//        igor.getTrabalhos().remove(trabalho);
//        entityManager.merge(igor);
//        entityManager.merge(trabalho);
        
//        List<Pessoa> vetPessoa = entityManager.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
//        for (int i = 0; i < vetPessoa.size(); i++) {
//            Pessoa x = vetPessoa.get(i);
//            System.out.println(x);
//        }

        
        
//        Pessoa igor = entityManager.find(Pessoa.class, 1);
//        Trabalho trabalhoLegal = entityManager.find(Trabalho.class, 1);
//        igor.getTrabalhos().add(trabalhoLegal);
//        trabalhoLegal.getFuncionarios().add(igor);
//        entityManager.merge(igor);
//        entityManager.merge(trabalhoLegal);
//        Pessoa p = new Pessoa();
//        p.setNome("Igor");
//        entityManager.persist(p);
//        Trabalho t = new Trabalho();
//        t.setDescricao("Trabalho Legal...");
//        entityManager.persist(t);
        entityManager.getTransaction().commit();
        
        
       
    }
    
}
