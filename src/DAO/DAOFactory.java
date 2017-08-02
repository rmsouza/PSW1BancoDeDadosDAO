/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author alexandretorres
 */
public abstract class DAOFactory {
    public static final int DERBY = 1;

    public abstract Connection getConnection();

    public static DAOFactory getDAOFactory(int tipo){
        switch (tipo){
            case 1: return new DerbyDAOFactory();
        }
        
        return null;
    }
}
