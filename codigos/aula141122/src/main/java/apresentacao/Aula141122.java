/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package apresentacao;

import negocio.Perfil;
import persistencia.PerfilDAO;

/**
 *
 * @author iapereira
 */
public class Aula141122 {

    public static void main(String[] args) {
        PerfilDAO perfilDAO = new PerfilDAO();

        Perfil a = new Perfil();
        a.setCpf("823479234792347892");
        a.setNome("a");
        perfilDAO.criar(a);

        Perfil b = new Perfil();
        b.setCpf("2384728934729");
        b.setNome("b");
        perfilDAO.criar(b);

//
//        Perfil p1 = new Perfil("44444444444");
//        Perfil p2 = new Perfil("11111111111");
//        perfilDAO.desfazer(p1, p2);
    }
}
