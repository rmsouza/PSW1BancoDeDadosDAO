/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psw1bancodedadosexercicio2;

/**
 *
 * @author rafaelmenezes
 */
public class Aluno{
    private int id;
    private String nome;
    private int ra;

    public Aluno(String nome, int ra){
       this.nome = nome; 
       this.ra = ra;
    }
    public Aluno( int id, String nome, int ra){
        this(nome, ra);
        this.id = id;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * @return the ra
     */
    public int getRa() {
        return ra;
    }

    /**
     * @param ra the ra to set
     */
    public void setRa(int ra) {
        this.ra = ra;
    }
   
}
