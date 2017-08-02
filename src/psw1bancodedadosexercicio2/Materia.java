/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psw1bancodedadosexercicio2;

/**
 *
 * @author alexandretorres
 */
public class Materia{
    private int id;
    private String descricao;

    public Materia(String descricao){
       this.descricao = descricao; 
    }
    public Materia( int id, String descricao){
        this(descricao);
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
   
}
