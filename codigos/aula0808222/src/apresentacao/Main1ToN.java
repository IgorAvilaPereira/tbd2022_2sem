/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apresentacao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.Dependente;
import negocio.Endereco;
import negocio.Pessoa;

/**
 *
 * @author iapereira
 */
public class Main1ToN {
    
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
        Dependente d = new Dependente();
        d.setNome("dependente do igor");
        d.setPessoa(igor);
        igor.getDependentes().add(d);
//        Endereco endereco = new Endereco();
//        endereco.setNumero("100");
//        endereco.setRua("Alfredo huch");
//        igor.setEndereco(endereco);
        entityManager.persist(igor);
        
       
        
        entityManager.getTransaction().commit();
        
        


        entityManager.close();

//        System.out.println("Hello:"+igorP.getNome());
    }
    
}
