/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apresentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.PessoaFisica;
import negocio.PessoaJuridica;

/**
 *
 * @author iapereira
 */
public class Main {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MeuPU");
    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Igor");
        pessoaFisica.setCpf("111.111.111-11");
        entityManager.persist(pessoaFisica);
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome("Google");
        pessoaJuridica.setCnpj("111.11116/000-1");
        entityManager.persist(pessoaJuridica);
        entityManager.getTransaction().commit();
    }
    
}
