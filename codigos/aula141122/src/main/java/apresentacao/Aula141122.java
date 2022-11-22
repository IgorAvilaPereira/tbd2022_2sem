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
//        Perfil renan = new Perfil();
//        renan.setCpf("44444444444");
//        renan.setNome("Renan");
//        perfilDAO.criar(renan);

            Perfil p1 = new Perfil("44444444444");
            Perfil p2 = new Perfil("11111111111");
            perfilDAO.criarAmizade(p1, p2);


    }
}
