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
import util.Conexion;
import vo.Persona;

/**
 *
 * @author LabingXEON
 */
public class Tx {
    private Connection conexion;

    public Tx() {
        Conexion db = Conexion.getConexion();
        this.conexion = db.getConnection();
    }
    
    public String create_Statement(Persona p) throws IllegalArgumentException, IllegalAccessException{
        String query = "INSERT INTO ";
        Field f [] = p.getClass().getDeclaredFields();
        query += p.getClass().getSimpleName()+" values (";
        for (int i = 0; i < f.length; i++) {
            if (i != f.length-1) {
                query += "'"+f[i].get(p)+"',";
            }else{
                query += "'"+f[i].get(p)+"')";
            }
        }
        
//        System.out.println("?------->"+f[0].getName()+": "+f[0].get(p)); //Obtenemos el nombre del atributo y el valor de la tabla SQL
//        System.out.println("2: "+p.getClass().getSimpleName()); //Obtenemos el nombre de la tabla
        System.out.println(query);
        return query;
    }
    
    public boolean insertar(Persona p) throws IllegalArgumentException, IllegalAccessException{
        boolean res = false;
        Field f [] = p.getClass().getDeclaredFields();
        String consulta = create_Statement(p);
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
}