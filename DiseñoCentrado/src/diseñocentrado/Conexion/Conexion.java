/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diseñocentrado.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class Conexion{
    
     Connection cn;
    
    
    
    public  Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/diseñocentrado", "root", "");
            System.out.println("Estamos conectados con exito.");
            
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error en la conexion :" + ex);
        }
        return cn;
    }    


}    

