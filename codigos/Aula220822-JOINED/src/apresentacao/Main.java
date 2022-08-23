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
import negocio.PessoaFisica;
import negocio.PessoaJuridica;

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
        
        List<Pessoa> vetPessoa = entityManager.createQuery("SELECT p FROM Pessoa p").getResultList();
        
        for (int i = 0; i < vetPessoa.size(); i++) {
            Pessoa p = vetPessoa.get(i);
            System.out.println(p.getNome());
        }
        
        
        
        
//        PessoaJuridica pessoaJuridica = new PessoaJuridica();
//        pessoaJuridica.setCnpj("00000/0000-1");
//        pessoaJuridica.setNome("google");
//        entityManager.persist(pessoaJuridica);

//        PessoaFisica pessoaFisica = entityManager.find(PessoaFisica.class, 1);
//        PessoaFisica pessoaFisica = new PessoaFisica();
//        pessoaFisica.setNome("Igor");
//        pessoaFisica.setCpf("111.111.111-11");
//        entityManager.persist(pessoaFisica);

//        System.out.println(pessoaFisica.getNome());
        entityManager.getTransaction().commit();

    }

}
