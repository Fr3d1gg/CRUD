/*
Primero que nada realizamos las importaciones necesarias en nuestro archivo de conexion
que nos permitira conectarnos en la base de datos de phpmysql
*/
package com.mycompany.crudmvc.modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String url = "jdbc:mysql://localhost:3306/crud_java"; // Es la ruta en donde tenemos la base de datos.
    private static final String usuario = "root"; // Usuario de MySQL
    private static final String contrasena = ""; // Contraseña de MySQL
    
    
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return con;
    }
}
