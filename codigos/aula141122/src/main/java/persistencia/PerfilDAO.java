/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import negocio.Perfil;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import static org.neo4j.driver.GraphDatabase.driver;
import org.neo4j.driver.Session;
import static org.neo4j.driver.Values.parameters;

/**
 *
 * @author iapereira
 */
public class PerfilDAO {

    private Driver driver;

    public PerfilDAO() {
       
    }
    
    public void initDriver(){
         this.driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j", "password"));
    }

    public void criar(Perfil perfil) {
        initDriver();
        try ( Session session = driver.session()) {

            // inserir
            session.run("CREATE (p: Perfil {cpf: $cpf, nome: $nome});",
                    parameters("cpf", perfil.getCpf(), "nome", perfil.getNome()));

        } finally {
            driver.close();
        }
    }

    public void criarAmizade(Perfil p1, Perfil p2) {
        this.initDriver();
        try ( Session session = driver.session()) {

            session.run("MATCH(p1: Perfil) WHERE p1.cpf = $cpf1 "
                    + " MATCH(p2: Perfil) WHERE p2.cpf = $cpf2 "
                    + "                   CREATE (p1)-[:EH_AMIGO]->(p2)",
                    parameters("cpf1", p1.getCpf(), "cpf2", p2.getCpf()));

        } finally {
            driver.close();
        }
    }

    public void desfazer(Perfil p1, Perfil p2) {
        this.initDriver();
        try ( Session session = driver.session()) {
            session.run("match (r:Perfil{cpf:$cpf1})-[d:EH_AMIGO]->(x:Perfil{cpf:$cpf2}) DELETE d", parameters("cpf1", p1.getCpf(), "cpf2", p2.getCpf()));
        } finally {
            driver.close();
        }
    }
}
