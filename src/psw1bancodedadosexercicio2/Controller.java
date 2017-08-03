/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psw1bancodedadosexercicio2;

import DAO.DAOFactory;
import DAO.DerbyMateriaDAO;
import DAO.MateriaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author alexandretorres
 */
public class Controller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MateriaDAO dao = 
                MateriaDAO.MateriaDAOFactory( 
                        MateriaDAO.DERBY );
        
        Materia materia1 = new Materia( "PSW 1");
        Materia materia2 = new Materia( "PSW 2");
        
        dao.insert(materia1);
        dao.insert(materia2);
        
        System.out.println("Mostrando matérias ------------");
        ArrayList<Materia> materias = dao.findAll();
        
        for (Materia m : materias)
            System.out.println(m.getId() + "\t" + m.getDescricao());
        
        
        Materia mat = dao.findById(7);
        if (mat != null)
            dao.delete(mat);
        else
            System.out.println("Não encontrado");
        
        System.out.println("Mostrando matérias ------------");
        materias = dao.findAll();
        
        for (Materia m : materias)
            System.out.println(m.getId() + "\t" + m.getDescricao());
       
    }
    
}
