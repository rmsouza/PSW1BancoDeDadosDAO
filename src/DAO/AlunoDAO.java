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
public abstract class AlunoDAO {
    private Connection conn;
    public static final int DERBY = 1;
    
    public static AlunoDAO AlunoDAOFactory(int tipo){
        
        switch(tipo){
            case DERBY:
                return new DerbyAlunoDAO();      
        }
        return null;
    }
    
    public void setConn(Connection conn) {
        this.conn = conn;
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
   
    public boolean insert(Aluno aluno) {
        int linhas = 0;
        PreparedStatement stat = null;
        
        try{
            String sql = "INSERT INTO ALUNOS(ID, NOME, RA) VALUES (?,?,?)";
            stat = conn.prepareStatement(sql);
            aluno.setId( this.getNextId());
            stat.setInt(1, aluno.getId());
            stat.setString(2, aluno.getNome());
            stat.setInt(3, aluno.getRa());

            linhas = stat.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("Erro ao inserir aluno");
        }finally{
            close(stat);
        }
        return linhas>0;
    }

    public boolean delete(Aluno aluno) {
        PreparedStatement stat = null;
        int linhas = 0;
        
        try{
            String sql = "DELETE FROM ALUNOS WHERE ID = ?";
            stat = conn.prepareStatement(sql);

            stat.setInt(1, aluno.getId());
            linhas = stat.executeUpdate();

        }catch (SQLException e ){
            System.out.println("Erro na exclusão");
        } finally {
            close(stat);    
        }
        
        return linhas > 0;
    }

    public boolean update(Aluno aluno) {
        PreparedStatement stat = null;
        int linhas = 0;
        try{
            String sql = "UPDATE ALUNOS SET NOME = ?, RA = ? WHERE ID = ?";
            stat = conn.prepareStatement(sql);

            stat.setString(1, aluno.getNome());
            stat.setInt(2, aluno.getRa());
            stat.setInt(3, aluno.getId());

            linhas = stat.executeUpdate();
        }catch(SQLException e){
            System.out.println("Erro na atualização");
        }finally{
            close(stat);
        }
        return linhas > 0;
    }

    public Aluno findById(int idAluno) {
        Statement stat = null;
        ResultSet rs = null;
        String select = "SELECT * FROM ALUNOS WHERE ID = " + idAluno;
        Aluno m = null;
        
        try{
            stat = conn.createStatement();
            rs = stat.executeQuery(select);

            while (rs.next()) {
                String nome = rs.getString("NOME");
                int ra = rs.getInt("RA");
                m = new Aluno(idAluno, nome, ra);
                break;
            }   
            
        }catch(SQLException e){
            System.out.println("Erro ao buscar aluno por Id");
        }finally{
            close(stat, rs);
        }
        
        return m; 
    }

    public ArrayList findAll() {
        Statement stat = null;
        ResultSet rs = null;
        
        String select = "SELECT * FROM ALUNOS";
        ArrayList<Aluno> alunos = new ArrayList<>();
        
        try{
            stat = conn.createStatement();
            rs = stat.executeQuery(select);

            while (rs.next()) {
                alunos.add( 
                        new Aluno(rs.getInt("ID"), rs.getString("NOME"), rs.getInt("RA"))
                );
            }
           
        }catch(SQLException e){
            System.out.println("Erro ao buscar todos os alunos");
        }finally{
            close(stat, rs);
        }
        
        return alunos; 
    }

    public int getNextId() {
        Statement stat = null;
        ResultSet rs = null;
        
        String select = "SELECT MAX(ID) FROM ALUNOS";
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

