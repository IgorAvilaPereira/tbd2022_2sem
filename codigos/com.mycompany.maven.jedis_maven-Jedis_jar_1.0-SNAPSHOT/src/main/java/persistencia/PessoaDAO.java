/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import negocio.Pessoa;

/**
 *
 * @author iapereira
 */
public class PessoaDAO extends DAO {
    private ConexaoRedis conexao;

    public PessoaDAO() {
        this.conexao = new ConexaoRedis();
    }

    public void adicionar(Pessoa p) {
        this.conexao.getConexao().set(p.getCpf(), gson.toJson(p));
    }
    
    public void atualizar(Pessoa p){
        this.adicionar(p);
    }
    
    public void remover(String cpf){
       this.conexao.getConexao().del(cpf);
    }
    
    public ArrayList<Pessoa> listar(){
        ArrayList<Pessoa> vetPessoa = new ArrayList();
        Set<String> vetChave = this.conexao.getConexao().keys("*");
        Iterator<String> iteradorChave = vetChave.iterator();
        while(iteradorChave.hasNext()){
            String chave = iteradorChave.next();
            Pessoa p = this.gson.fromJson(this.conexao.getConexao().get(chave), Pessoa.class);
            vetPessoa.add(p);
        }
        return vetPessoa;
    }

}
