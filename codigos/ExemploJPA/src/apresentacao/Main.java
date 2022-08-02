/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apresentacao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import negocio.Pessoa;

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

        List<Pessoa> vetPessoa = entityManager.createQuery("select p from Pessoa p", Pessoa.class).getResultList();
        for (int i = 0; i < vetPessoa.size(); i++) {
            Pessoa p = vetPessoa.get(i);
            System.out.println(p.getId());
            System.out.println(p.getNome());
            System.out.println(p.getSobrenome());

        }

//        insert
//        Pessoa igor = new Pessoa();
//        igor.setNome("Igor");
//        igor.setSobrenome("Pereira");
//        entityManager.persist(igor);
////        
//        Pessoa igorf = new Pessoa();
//        igorf.setNome("Igor");
//        igorf.setSobrenome("Farias");
//        entityManager.persist(igorf);
        // delete
//        Pessoa igorP = entityManager.find(Pessoa.class, 1);
//        entityManager.remove(igorP);
        entityManager.getTransaction().commit();
        entityManager.close();

//        System.out.println("Hello:"+igorP.getNome());
    }

}
