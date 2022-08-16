/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apresentacao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import negocio.Pessoa;
import persistencia.PessoaJpaController;

/**
 *
 * @author iapereira
 */
public class Main {
    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("Aula150822-engenharia-reversaPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EntityManager entityManager = emf.createEntityManager();
//        Pessoa p = new Pessoa();
//        p.setNome("fulano");
    
        List<Pessoa> vetPessoa = entityManager.createNamedQuery("Pessoa.x", Pessoa.class).setParameter("id", 1).getResultList();
       
//        PessoaJpaController pessoaJpaController = new PessoaJpaController(emf);
//        
//        List<Pessoa> vetPessoa = pessoaJpaController.findPessoaEntities();
        for (int i = 0; i < vetPessoa.size(); i++) {
            Pessoa px = vetPessoa.get(i);
            System.out.println("Nome:"+px.getEnderecoId().getRua());
            
        }
    }
    
}
