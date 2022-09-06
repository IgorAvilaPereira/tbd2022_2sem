/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 * @author iapereira
 */
class ConexaoRedis {    
    private int selectDB;
    private String host;
    private int port;
    
    public ConexaoRedis(){
        this.port = 6379;
        this.host = "localhost";
        this.selectDB = 0;
        
    }
    
    public ConexaoRedis(String host, int port, int selectDB){
        this.host = host;
        this.port = port;
        this.selectDB = selectDB;
    }
    
    public Jedis getConexao(){
        JedisPool pool = new JedisPool(this.host, this.port);
        try ( Jedis jedis = pool.getResource()) {
            jedis.select(this.selectDB);
            return jedis;
        }
    }
}
