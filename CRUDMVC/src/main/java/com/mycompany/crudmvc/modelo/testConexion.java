
package com.mycompany.crudmvc.modelo;
import java.sql.Connection;
/*
Esta clase fue creada con el proposito de verificar si la base de datos se conecto correctamente
*/
public class testConexion {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();//Creamos un objeto de tipo conexion,esto con el fin de obtener si la conexion a la bd fue exitosa
        Connection con = conexion.getConnection();//Nos debe devolver un objeto del tipo Connection si la conexión a la base de datos se establece correctamente.

        if (con != null) { //Si nos regresa algo,nos imprimira en consola lo siguiente:
            System.out.println("Conexión exitosa a la base de datos.");
        } else {//Si nos regresa un null la conexion,quiere decir que no se conecto correctamente a la base de datos.
            System.out.println("Error al conectar con la base de datos.");
        }
    }
}
