/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tx;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import vo.Persona;

/**
 *
 * @author LabingXEON
 */
public class Tx<T> {
    private Connection conexion;

    public Tx() {
        Conexion db = Conexion.getConexion();
        this.conexion = db.getConnection();
    }
    
    public String create_Statement(T t) throws IllegalArgumentException, IllegalAccessException{
        String query = "INSERT INTO ";
        Field f [] = t.getClass().getDeclaredFields();
        query += t.getClass().getSimpleName()+" values (";
        for (int i = 0; i < f.length; i++) {
            if (i != f.length-1) {
                query += "'"+f[i].get(t)+"',";
            }else{
                query += "'"+f[i].get(t)+"')";
            }
        }
        
//        System.out.println("?------->"+f[0].getName()+": "+f[0].get(p)); //Obtenemos el nombre del atributo y el valor de la tabla SQL
//        System.out.println("2: "+p.getClass().getSimpleName()); //Obtenemos el nombre de la tabla
        System.out.println(query);
        return query;
    }
    
    public boolean insertar(T t) throws IllegalArgumentException, IllegalAccessException{
        boolean res = false;
        
        String consulta = create_Statement(t);
        try {
            //----------------------------
            //Statement
            Statement statement =
                    this.conexion.createStatement();
            //Ejecucion
            boolean resultado = 
                    statement.execute(consulta);
            //----------------------------
            //Recorrido sobre el resultado
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return res;
    }
    public String consultaBuscar(T t, String campo, String valor){
        Field f [] = t.getClass().getDeclaredFields();
        String query = "SELECT * FROM "+t.getClass().getSimpleName()+" where "+campo+" = '"+valor+"'";
        
        return query;
    }
    
    public ArrayList<T> buscar(T t, String campo, String valor) {
        ArrayList<T> arreglo = new ArrayList<T>();
        try {
            String consulta = consultaBuscar(t, campo, valor);
            PreparedStatement statement
                    = this.conexion.prepareStatement(consulta);

            
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                
//                String nombre = resultado.getString("nombre");
//                String apellido = resultado.getString("apellido");
//                int edad = resultado.getInt("edad");
                arreglo.add((T) resultado);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(Tx.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arreglo;
    }
}