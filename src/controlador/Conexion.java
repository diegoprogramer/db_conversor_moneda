/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hs1
 */
public class Conexion {
    private Connection con=null;
    public Connection conexion() throws SQLException{
    try{
    Class.forName("com.mysql.jdbc.Driver");
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abogados","root","");
    System.out.println("Conexion exitosa");
    }catch(ClassNotFoundException ex){
      Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null,ex);
    } 
    catch(SQLException e){}
    return con;}
    public void cerrarConexion(Connection con){
    try{con.close();}
    catch(SQLException e){}
    }
}
