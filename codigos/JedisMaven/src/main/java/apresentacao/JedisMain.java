/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apresentacao;

/**
 *
 * @author iapereira
 */
import com.google.gson.Gson;
import java.util.ArrayList;
import negocio.Pessoa;
import redis.clients.jedis.Jedis;

public class JedisMain {

    public static void main(String[] args) {
//      1) conexao
        Jedis jedis = new Jedis();
//      2) conexao
//      Jedis jedis = new Jedis("localhost", 6379);

//      selecionar um bd especifico (0 a 15). Se n usar este metodo cai na 0
//      jedis.select(0);

//      gravacao
//      jedis.set("teste", "igor");

//      recuperacao
//      System.out.println(jedis.get("teste"));

//      deletar
//      jedis.del("teste");

//      deletar a chave do banco atual (Select)
//      jedis.flushDB()

//      deletar todas as chaves de todos os bd's
//      jedis.flushAll();


//      objeto para json
//      Pessoa p = new Pessoa();
//      p.setCpf("01763917038");
//      p.setNome("Igor");
//      Gson gson = new Gson();
//      String jsonString = gson.toJson(p);
//        System.out.println(jsonString);

//        json para objeto  
//        Pessoa x = gson.fromJson(jsonString, Pessoa.class);
//        System.out.println(x.getNome());

//        gravando no bd um arraylist
//        Gson gson = new Gson();
//        ArrayList<Pessoa> vetPessoa = new ArrayList<Pessoa>();
//        vetPessoa.add(new Pessoa("igor"));
//        vetPessoa.add(new Pessoa("nanah"));
//        jedis.set("vetPessoa",  gson.toJson(vetPessoa));

//      recuperando um vetor/arraylist
//        Gson gson = new Gson();
//        Pessoa[] vetPessoa = gson.fromJson(jedis.get("vetPessoa"), Pessoa[].class);  
//        System.out.println(vetPessoa[1].getNome());


//        ainda n testado
//        String cachedResponse = jedis.get("events/city/rome");
//        jedis.sadd("nicknames", "nickname#1");
//        jedis.sadd("nicknames", "nickname#2");
//        jedis.sadd("nicknames", "nickname#1");
//        Set<String> nicknames = jedis.smembers("nicknames");
//        while(nicknames.iterator().hasNext()){
//            String x = nicknames.iterator().next();
//            System.out.println(x);
//            nicknames.iterator().next();
//        }
//        boolean exists = jedis.sismember("teste", "igor");
//        System.out.println(exists);
    }
}
