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

/**
 *
 * @author iapereira
 */
public class Main1To1 {
    
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MeuPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //        insert
        entityManager.getTransaction().begin();
        Pessoa igor = new Pessoa();
        igor.setNome("Igor");
        igor.setSobrenome("Pereira");
        Endereco endereco = new Endereco();
        endereco.setNumero("100");
        endereco.setRua("Alfredo huch");
        igor.setEndereco(endereco);
        entityManager.persist(igor);
        
        Pessoa fulano = new Pessoa();
        fulano.setNome("Fulano");
        fulano.setSobrenome("Beltrano");
        Endereco endereco_fulano = new Endereco();
        endereco_fulano.setNumero("559");
        endereco_fulano.setRua("24 de maio");
        fulano.setEndereco(endereco);
        entityManager.persist(fulano);
        
        
        entityManager.getTransaction().commit();
        
        
//        List<Pessoa> vetPessoa = entityManager.createQuery("select p from Pessoa p").getResultList();
                List<Pessoa> vetPessoa = entityManager.createNamedQuery("Pessoa.all").getResultList();


        for (int i = 0; i < vetPessoa.size(); i++) {
            Pessoa y = vetPessoa.get(i);
            System.out.println(y.getNome());
            
        }
        

        // find
//        Pessoa p = entityManager.find(Pessoa.class, 1);
//        System.out.println(p.getEndereco().getRua());
//
//        // update
//        entityManager.getTransaction().begin();
//        p.getEndereco().setRua("presidente vargas");
//        entityManager.merge(p);
//        entityManager.getTransaction().commit();

        // remover o endereco do igor
//        entityManager.getTransaction().begin();
//        Pessoa x = entityManager.find(Pessoa.class, 1);
//        x.setEndereco(null);
//        entityManager.merge(x);
//        entityManager.getTransaction().commit();
        // remover pessoa 
//        entityManager.getTransaction().begin();
//        entityManager.remove(p);
//        entityManager.getTransaction().commit();

        entityManager.close();

//        System.out.println("Hello:"+igorP.getNome());
    }
    
}
