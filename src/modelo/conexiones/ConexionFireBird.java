package modelo.conexiones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionFireBird {
    String URL = "jdbc:firebirdsql:192.168.5.202/3050:D:/sysplus/Datos/LIB/sysplus.fdb?lc_ctype=ISO8859_1";
    //String URL = "jdbc:firebirdsql:localhost/3050:d:/firebird/sysplus.fdb?lc_ctype=ISO8859_1";
    String Usuario = "SYSDBA";
    String Contraseña = "masterkey";
    String Driver = "org.firebirdsql.jdbc.FBDriver";
    Connection con;

    public ConexionFireBird(Connection con) {
        this.con = con;
    }

    public ConexionFireBird(String URL){
        con = null;
     try {
        Class.forName(Driver);
        con = DriverManager.getConnection(URL, Usuario, Contraseña);
        
       if (con !=null){
                    System.out.println("Conexion Establecida");
                }
            } catch (ClassNotFoundException | SQLException e){
               e.printStackTrace();
                
            }
    }
    public ConexionFireBird(String Usuario, String Contraseña){
        con = null;
         //Realizar conexion
     try {
        Class.forName(Driver);
        con = DriverManager.getConnection(URL, Usuario, Contraseña);
       if (con !=null){
                    System.out.println("Conexion Establecida");
                }
            } catch (ClassNotFoundException | SQLException e){
               System.out.println("error " + e);
                
            }
         }

        // este metodo nos retorna la conexion
    public Connection getConnection (){
        return con;
    }
        
   // con este metodo nos desconectamos de la Base de Datos
    public void desconectar(){
        con= null;
        if (con ==null){
                    System.out.println("Conexion Terminada");
                }
    }
   
    
}   
    
  

