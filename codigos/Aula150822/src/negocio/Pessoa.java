/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author iapereira
 */
@Entity
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;
    @ManyToMany
    @JoinTable(name = "trabalhos_pessoas")
    private List<Trabalho> trabalhos;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Endereco endereco;    
    @OneToMany (mappedBy = "pessoa")
    private List<Dependente> dependentes;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Trabalho> getTrabalhos() {
        return trabalhos;
    }

    public void setTrabalhos(List<Trabalho> trabalhos) {
        this.trabalhos = trabalhos;
    }
    
    public Pessoa(){
        this.trabalhos = new ArrayList();
        this.endereco = new Endereco();
    }
    
    
    @Override
    public String toString(){
        String resultado = this.getNome()+"\n";
        for (int i = 0; i < trabalhos.size(); i++) {
            Trabalho x = trabalhos.get(i);
            resultado += x.getDescricao() + "\n";            
        }
        return resultado;
    }
    
    
    
    
}
