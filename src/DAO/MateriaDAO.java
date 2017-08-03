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
import psw1bancodedadosexercicio2.Materia;

/**
 *
 * @author alexandretorres
 */
public abstract class MateriaDAO {
    private Connection conn;
    public static final int DERBY = 1;
    
    public static MateriaDAO MateriaDAOFactory(int tipo){
        
        switch(tipo){
            case DERBY:
                return new DerbyMateriaDAO();      
        }
    }
   
    public void close(){
        try{
            conn.close();
        } catch(SQLException e){
            System.out.println("Erro ao fechar Conexão");
        }
        
    }
    
    public void close(PreparedStatement stat){
        try{
            stat.close();
        } catch(SQLException e){
            System.out.println("Erro ao fechar PreparedStatement");
        }
    }
    
    public void close(Statement stat, ResultSet rs){
        try{
            rs.close();
            stat.close();
            
        } catch(SQLException e){
            System.out.println("Erro ao fechar Statemente e ResultSet");
        }
    }
   
    public boolean insert(Materia materia) {
        int linhas = 0;
        PreparedStatement stat = null;
        
        try{
            String sql = "INSERT INTO MATERIAS(ID, DESCRICAO) VALUES (?,?)";
            stat = conn.prepareStatement(sql);
            materia.setId( this.getNextId());
            stat.setInt(1, materia.getId());
            stat.setString(2, materia.getDescricao());

            linhas = stat.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Erro ao inserir matéria");
        }finally{
            close(stat);
        }
        return linhas>0;
    }

    public boolean delete(Materia materia) {
        PreparedStatement stat = null;
        int linhas = 0;
        
        try{
            String sql = "DELETE FROM MATERIAS WHERE ID = ?";
            stat = conn.prepareStatement(sql);

            stat.setInt(1, materia.getId());
            linhas = stat.executeUpdate();

        }catch (SQLException e ){
            System.out.println("Erro na exclusão");
        } finally {
            close(stat);    
        }
        
        return linhas > 0;
    }

    public boolean update(Materia materia) {
        PreparedStatement stat = null;
        int linhas = 0;
        try{
            String sql = "UPDATE MATERIAS SET DESCRICAO = ? WHERE ID = ?";
            stat = conn.prepareStatement(sql);

            stat.setString(1, materia.getDescricao());
            stat.setInt(2, materia.getId());

            linhas = stat.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro na atualização");
        }finally{
            close(stat);
        }
        return linhas > 0;
    }

    public Materia findById(int idMateria) {
        Statement stat = null;
        ResultSet rs = null;
        String select = "SELECT * FROM MATERIAS WHERE ID = " + idMateria;
        Materia m = null;
        
        try{
            stat = conn.createStatement();
            rs = stat.executeQuery(select);

            while (rs.next()) {
                String descricao = rs.getString("DESCRICAO");
                m = new Materia(idMateria, descricao);
                break;
            }   
            
        }catch(SQLException e){
            System.out.println("Erro ao buscar matéria por Id");
        }finally{
            close(stat, rs);
        }
        
        return m; 
    }

    public ArrayList findAll() {
        Statement stat = null;
        ResultSet rs = null;
        
        String select = "SELECT * FROM MATERIAS";
        ArrayList<Materia> materias = new ArrayList<>();
        
        try{
            stat = conn.createStatement();
            rs = stat.executeQuery(select);

            while (rs.next()) {
                materias.add( 
                        new Materia(rs.getInt("ID"), rs.getString("DESCRICAO"))
                );
            }
           
        }catch(SQLException e){
            System.out.println("Erro ao buscar todas as matérias");
        }finally{
            close(stat, rs);
        }
        
        return materias; 
    }

    public int getNextId() {
        Statement stat = null;
        ResultSet rs = null;
        
        String select = "SELECT MAX(ID) FROM MATERIAS";
        int id = 0;
        
        try{   
            stat = conn.createStatement();
            rs = stat.executeQuery(select);
 
            if (rs.next())
                id = rs.getInt(1);

            id++;
            
        }catch(SQLException e){
            System.out.println("Erro ao buscar próximo id");
        }finally{
            close(stat, rs);
        }
        
        return id;     
    }
    
    
}    

