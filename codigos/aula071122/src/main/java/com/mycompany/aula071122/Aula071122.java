/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.aula071122;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import static org.neo4j.driver.Values.parameters;

/**
 *
 * @author iapereira
 */
public class Aula071122 {

    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j", "password"));
        
        Pessoa p = new Pessoa();
        p.setNome("ciclano");
        p.setCpf("9999999");
        p.setDataNascimento(LocalDate.now());
        

        try ( Session session = driver.session()) {
    
            // inserir
//            session.run("CREATE(p:Pessoa{cpf:$cpf, nome:$nome,nascimento:$nascimento})", 
//                                parameters("cpf", p.getCpf(), "nome", p.getNome(), 
//                                        "nascimento", p.getDataNascimento()));
         

// tanto fulano como ciclano assistiram titanic (logo precisavam de uma aresta até titanic)
//session.run("MATCH (p: Pessoa) WHERE p.nome = \"ciclano\" OR p.nome = \"fulano\"\n" +
//"MATCH (f: Filme) WHERE f.titulo = \"Titanic\"\n" +
//"CREATE (p)-[:assitiu]->(f)\n" +
//"RETURN *");

            ArrayList<Pessoa> vetPessoa = new ArrayList();
            Result result = session.run("MATCH(n:Pessoa) RETURN n");
            while(result.hasNext()){
                Pessoa aux = new Pessoa();
//                System.out.println(result.next().get("n").asNode().id());
//                System.exit(0);
                Value v  = result.next().get("n");
                
                aux.setId(v.asNode().id());
                aux.setCpf(v.get("cpf").asString());
                aux.setNome(v.get("nome").asString());                
                LocalDate d = v.get("nascimento").asLocalDate();
                aux.setDataNascimento(d);
                vetPessoa.add(aux);
            }
            
            for (int i = 0; i < vetPessoa.size(); i++) {
                System.out.println(vetPessoa.get(i));
                
            }

        } finally {
            driver.close();
        }
    }
}
