/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apresentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.*;

/**
 *
 * @author iapereira
 */
public class Main {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MeuPU");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setCpf("111.111.111-11");
        pessoaFisica.setNome("Igor");
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome("Google");
        pessoaJuridica.setCnpj("363636/0001-32");
        entityManager.persist(pessoaFisica);
        entityManager.persist(pessoaJuridica);
        entityManager.getTransaction().commit();
    }
    
}
