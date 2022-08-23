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
//        PessoaFisica pf = new PessoaFisica();
//        pf.setCpf("111.111.111-11");
//        pf.setNome("Igor");
//        entityManager.persist(pf);
        PessoaJuridica pj = new PessoaJuridica();
        pj.setCnpj("111.111.111-11");
        pj.setNome("Google");
        entityManager.persist(pj);
        entityManager.getTransaction().commit();
    }

}
