/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import tx.Tx;
import vo.Persona;

/**
 *
 * @author LabingXEON
 */
public class PersonaDAO {
    private Tx tx;
    
    public PersonaDAO() {
        this.tx = new Tx();
    }
    
    public void insertar(Persona p) throws IllegalArgumentException, IllegalAccessException{
        this.tx.insertar(p);
    }
    
//    public void create_Statement(Persona p) throws IllegalArgumentException, IllegalAccessException{
//        this.tx.create_Statement(p);
//    }
    
    public ArrayList buscar(Persona p, String campo, String valor){
        ArrayList arr =this.tx.buscar(p, campo, valor);
        return arr;
    }
}