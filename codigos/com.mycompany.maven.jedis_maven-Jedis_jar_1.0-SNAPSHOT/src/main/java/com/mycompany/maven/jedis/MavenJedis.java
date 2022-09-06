/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.maven.jedis;

import com.google.gson.Gson;
import java.util.ArrayList;
import negocio.Endereco;
import negocio.Pessoa;
import persistencia.PessoaDAO;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author iapereira
 */
public class MavenJedis {

    public static void main(String[] args) {
//        JedisPool pool = new JedisPool("localhost", 6379);
//        try ( Jedis jedis = pool.getResource()) {
//            jedis.select(0);
//            System.out.println(jedis.ping());
//            Pessoa p = new Pessoa();
//            p.setCpf("111.111.111-30");
//            p.setNome("Igor");         
//            Endereco endereco = new Endereco();
//            endereco.setNro(102);
//            endereco.setRua("Alfredo huch");
//            p.setEndereco(endereco);

//            Gson gson = new Gson();
//            salvando no formato json
//            jedis.set(p.getCpf(), gson.toJson(p));
//              recuperando o json
//
//            Pessoa igor = gson.fromJson(jedis.get("111.111.111-30"), Pessoa.class);
//        Pessoa igor = new Pessoa();
//        igor.setCpf("12121213");
//        igor.setNome("igor");
//        new PessoaDAO().adicionar(igor);
//        System.out.println("Nome da rua:" + igor.getNome());

        ArrayList<Pessoa> vetPessoa = new PessoaDAO().listar();
        for (int i = 0; i < vetPessoa.size(); i++) {
            Pessoa aux = vetPessoa.get(i);
            System.out.println(aux);
            
        }
    }

}
