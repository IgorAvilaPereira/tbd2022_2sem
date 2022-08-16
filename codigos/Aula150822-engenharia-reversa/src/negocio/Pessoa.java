/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author iapereira
 */
@Entity
@Table(name = "pessoa")
@NamedQueries({
     @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p"),
        @NamedQuery(name = "Pessoa.x", query = "SELECT p FROM Pessoa p where p.id = :id")
})
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nome")
    private String nome;
    @JoinTable(name = "trabalhos_pessoas", joinColumns = {
        @JoinColumn(name = "funcionarios_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "trabalhos_id", referencedColumnName = "id")})
    @ManyToMany
    private List<Trabalho> trabalhoList;
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @ManyToOne
    private Endereco enderecoId;
    @OneToMany(mappedBy = "pessoaId")
    private List<Dependente> dependenteList;

    public Pessoa() {
    }

    public Pessoa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Trabalho> getTrabalhoList() {
        return trabalhoList;
    }

    public void setTrabalhoList(List<Trabalho> trabalhoList) {
        this.trabalhoList = trabalhoList;
    }

    public Endereco getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Endereco enderecoId) {
        this.enderecoId = enderecoId;
    }

    public List<Dependente> getDependenteList() {
        return dependenteList;
    }

    public void setDependenteList(List<Dependente> dependenteList) {
        this.dependenteList = dependenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "negocio.Pessoa[ id=" + id + " ]";
    }
    
}
