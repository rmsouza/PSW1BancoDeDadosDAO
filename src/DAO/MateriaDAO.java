/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import psw1bancodedadosexercicio2.Materia;

/**
 *
 * @author alexandretorres
 */
public interface MateriaDAO {
   
    public boolean insert(Materia materia);    
    public boolean delete(Materia materia);
    public boolean update(Materia materia);
    public Materia findById(int idMateria);
    public ArrayList findAll();
    public int getNextId();
    
}    

