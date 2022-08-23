/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apresentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.PessoaFisica;

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
        // TODO code application logic here
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Igor");
        pessoaFisica.setCpf("111.111.111-11");
        entityManager.persist(pessoaFisica);
        entityManager.getTransaction().commit();
    }
    
}
