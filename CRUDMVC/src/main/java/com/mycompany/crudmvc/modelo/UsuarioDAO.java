/*
Con que finalidad creamos la clase UsuarioDAO
Con esta misma podemos manejar cada una de las operaciones relacionadas
con la base de datos en un solo lugar,basicamente separamos la logica
de sql con el resto de nuestro proyecto.


*/
package com.mycompany.crudmvc.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    //Establecemos conexion con nuestra base de datos
    private Conexion conexion = new Conexion();

    // Método para listar todos los usuarios
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPais(rs.getString("pais"));
                usuario.setRol(rs.getString("rol"));
                usuario.setPassword(rs.getString("password"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Método para agregar un nuevo usuario
    //Insertar un nuevo usuario en nuestra base de datos
    public boolean agregar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, email, pais, rol, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPais());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getPassword());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un usuario mediante el id
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, email = ?, pais = ?, rol = ?, password = ? WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPais());
            ps.setString(4, usuario.getRol());
            ps.setString(5, usuario.getPassword());
            ps.setInt(6, usuario.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un usuario segun su id
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            System.out.println("Filas afectadas por la eliminación: " + filasAfectadas);
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario con ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener un usuario por ID
    public Usuario obtenerPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPais(rs.getString("pais"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }

    // Método para validar un usuario (inicio de sesión)
    public Usuario validarUsuario(String email, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE email = ? AND password = ?";

        try (Connection con = conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setPais(rs.getString("pais"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuario;
    }
    
    //Verifica si es que un correo ya existe  y con esto no se permiten los duplicados
         public boolean existeUsuarioPorEmail(String email) {
    String sql = "SELECT COUNT(*) FROM usuarios WHERE email = ?";
    try (Connection con = conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, email);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Devuelve true si el email ya existe
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
}