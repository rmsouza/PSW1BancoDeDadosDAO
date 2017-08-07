/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import psw1bancodedadosexercicio2.Aluno;

/**
 *
 * @author alexandretorres
 */
public class DerbyAlunoDAO extends AlunoDAO{
    
    public DerbyAlunoDAO(){
        DAOFactory dao = DAOFactory.getDAOFactory( DAOFactory.DERBY );
        setConn(dao.getConnection());
    }
      
}
