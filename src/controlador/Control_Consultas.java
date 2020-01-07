/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import consultas.ConsultarConver;
import static consultas.ConsultarConver.tableMoneda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.*;
/**
 *
 * @author diego
 * En esta clase se exponen todos los métodos para ejercer control sobre los
 * datos que van desde y hacia la conversion. En esta clase se hace la
 * validación y organizacion de los datos.
 */
public class Control_Consultas {
    
     int cont=0;
     //modelo para la tabla
    DefaultTableModel modelo;
    //vector con los titulos de cada columna
    
    //matriz donde se almacena los datos de cada celda de la tabla
    String info[][] = {};
    // Conectar Base de Datos
    Conexion conexion = new Conexion();
    
    // lastdate
    String lastDate="";
    
    // crecion map
    
    Map<Double,String> FECHAS = new HashMap<Double,String>();
    
            
       
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
    
    public void listarTodoMoneda() {
        
        String[] titulosColumnas = {"No Conversion", "VALOR", "FECHA"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
        ConsultarConver.tableMoneda.setModel(modelo);

        //ejecuta una consulta a la BD
        ejecutarConsultaTodaTablaMonedas();

    }//cierra metodo listarTodosFacturas
    /*
    * metodo limpiar todo
    */
    
    public void limpiarTodo() {
        
        String[] titulosColumnas = {"No Conversion", "VALOR", "FECHA"};

        modelo = new DefaultTableModel(info, titulosColumnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
      //le asigna el modelo al jtable
        ConsultarConver.tableMoneda.setModel(modelo);

              

    }//cierra metodo listarTodosFacturas
    
    
    
    
     /* Metodo para consultar todos los regsitros de la base de datos de clientes
     * y luego ser mostrados en una tabla.
     */
   
    Statement sentencia = null;
    ResultSet resultado = null;
    PreparedStatement ps = null;
    public void ejecutarConsultaTodaTablaMonedas() {

        try {
            conexion = new Conexion();

            sentencia = conexion.conexion().createStatement();
            String consultaSQL = "SELECT * FROM conversion ORDER BY fecha DESC";
            resultado = sentencia.executeQuery(consultaSQL);


            //mientras haya datos en la BD ejecutar eso...
            while (resultado.next()) {


                int id = resultado.getInt("id");
                double valor = resultado.getDouble("valor");
                String fecha = resultado.getString("fecha");
                
               // String fecha1 = lastDate(valor);
               //String fecha1 = "2020-10-08";
                
              // System.out.println(" fecha "+ fecha1);
                // agregamos al map
               // FECHAS.put(valor,fecha1);
                    

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {id,valor,fecha};

                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }//cierra while (porque no hay mas datos en la BD)
              ///

            
              
              
              // fin ciclo
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error SQL:\n" + e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            conexion = null;
        } finally {
            //CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

    }//cierra metodo ejecutarConsulta
    
    
    /**
     * Método para buscar un registro en la base de datos dentro de la tabla
     * clientes, se puede buscar por la cedula o por el nombre.
     */
    public void buscarMonedaPorValor(double parametroBusqueda) {

        Object[] info1 = {};
        modelo.addRow(info1);
        System.out.println("hola entro hasta aqui");
        try {

            conexion = new Conexion();
            
            String selectSQL;
            resultado = null;
                
                System.out.print("buscando por valor en Conversion");
                selectSQL = "SELECT * FROM conversion WHERE valor = ? ORDER BY fecha "+ "DESC LIMIT 10" ;
                ps = conexion.conexion().prepareStatement(selectSQL);
                ps.setDouble(1, parametroBusqueda );
            
            resultado = ps.executeQuery();

            while (resultado.next()) {
                int num = resultado.getInt("id");
                double valor= resultado.getDouble("valor");
                String fecha = resultado.getString("fecha");
                

                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {num,valor,fecha};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            //CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }


    }//cierra metodo buscarRegistro
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
      
    /*
    * METODO PARA DEVOLVER LA ultima Fecha del valor...
    *
    */

public void lastDate() {

        Object[] info1 = {};
        modelo.addRow(info1);
        String fecha="";
        System.out.println("hola entro hasta aqui");
        try {

            conexion = new Conexion();
            
            String selectSQL;
            resultado = null;
                
                System.out.print("buscando por valor en Conversion");
                selectSQL = "SELECT  valor, max(fecha) as fecha FROM conversion GROUP BY valor ORDER BY fecha DESC " ;
                ps = conexion.conexion().prepareStatement(selectSQL);
                
            
            resultado = ps.executeQuery();
           
            while (resultado.next()) {
               cont++;
                double valor= resultado.getDouble("valor");
                 fecha = resultado.getString("fecha");
                
                   FECHAS.put(valor,fecha);
                //crea un vector donde los está la informacion (se crea una fila)
                Object[] info = {cont,valor,fecha};
                //al modelo de la tabla le agrega una fila
                //con los datos que están en info
                modelo.addRow(info);
                cont++;
                System.out.println(" entro "+cont);

            }
            
              Set set = FECHAS.entrySet();
              Iterator itr = set.iterator();
              System.out.println("datos ");
              while(itr.hasNext()){
              Map.Entry entry = (Map.Entry)itr.next();
                  
                  System.out.println(entry.getKey() + "   " + entry.getValue());
              }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error\n Por la Causa" + e);
        } finally {
            //CerrarConexiones.metodoCerrarConexiones(conexion, sentencia, resultado, ps);
        }

        
    }
    //cierra metodo buscarRegistro
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     
    // METODO PARA MAX FECHA POR VALOR

    


}
    
    
    

