/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alexandretorres
 */
public class DerbyDAOFactory extends DAOFactory{
    
        public static final String url = "jdbc:derby://localhost:1527/BD";
        public static final String user = "usuario";
        public static final String password = "senha";
        private Connection connection;
        
        public DerbyDAOFactory(){
            try{
                connection = DriverManager.getConnection(url, user, password);
            }catch(SQLException e){
                System.out.println("Erro ao conectar no banco de dados.");
            }
        }
        
        @Override
        public Connection getConnection(){    
            return connection;
        }
        
}
