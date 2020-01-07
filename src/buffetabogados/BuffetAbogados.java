/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buffetabogados;

import vista.Principal;
import controlador.Conexion;

/**
 *
 * @author hs1
 */
public class BuffetAbogados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Principal ventana=new Principal();
        ventana.setTitle("Buffet de Abogados");
        ventana.setLocation(200,200);
        ventana.setVisible(true);
        
    }
}
