/*
Este basicamente es el nucleo que nos permite gestionar todas las operaciones 
de nuestro CRUD y como todo este sistema forma parte de el patron MVC
A grandes rasgos recibe las solicitudes del cliente,interactua con el modelo
para hacer consultas y ademas enruta hacia las vistas correspondientes
*/
package com.mycompany.crudmvc.controlador;

import com.mycompany.crudmvc.modelo.Usuario;
import com.mycompany.crudmvc.modelo.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UsuarioControlador")//Ruta de acceso
public class UsuarioControlador extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

 //Maneja las solicitudes http,la usamos para mostrar paginas,vistas o los formularios
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "listar":
                listarUsuarios(request, response);
                break;
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminarUsuario(request, response);
                break;
            case "registro": // Mostrar el formulario de registro
                mostrarFormularioRegistro(request, response);
                break;
            default:
                listarUsuarios(request, response);
                break;
        }
    }
//e usa cuando hay datos enviados por formularios que deben ser procesados (por ejemplo, agregar, actualizar o eliminar un usuario).
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            case "agregar":
                registrarUsuario(request, response);
                break;
            case "actualizar":
                actualizarUsuario(request, response);
                break;
            case "eliminar":
                eliminarUsuario(request, response); // Procesar eliminación desde POST
                break;
            case "registrar": // Nueva acción para registrar usuarios
                registrarDesdeFormulario(request, response);
                break;
            default:
                listarUsuarios(request, response);
                break;
        }
    }
    
//Recupera la lista de usuarios desde la base de datos y la envía a la vista
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.listar();
        String mensajeExito = request.getParameter("exito"); // Capturar el mensaje de éxito
        String mensajeError = request.getParameter("error"); // Capturar el mensaje de error

        request.setAttribute("usuarios", usuarios);
        if (mensajeExito != null) {
            request.setAttribute("mensajeExito", mensajeExito); // Pasar el mensaje de éxito a la vista
        }
        if (mensajeError != null) {
            request.setAttribute("mensajeError", mensajeError); // Pasar el mensaje de error a la vista
        }

        request.getRequestDispatcher("Vistas/Lista.jsp").forward(request, response);
    }
//edirige al formulario de registro de un nuevo usuario
    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Vistas/Agregar.jsp").forward(request, response);
    }
//Muestra el formulario para editar un usuario existente
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Usuario usuario = usuarioDAO.obtenerPorId(id);
        request.setAttribute("usuario", usuario);
        request.getRequestDispatcher("Vistas/Editar.jsp").forward(request, response);
    }
//Muestra el formulario de el registro
    private void mostrarFormularioRegistro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Vistas/Registro.jsp").forward(request, response);
    }
//Procesa el registro de un nuevo usuario enviado desde el formulario
   private void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String nombre = request.getParameter("nombre");
    String email = request.getParameter("email");
    String pais = request.getParameter("pais");
    String password = request.getParameter("password");
    String rol = request.getParameter("rol"); // Captura el rol correctamente

    // Verificar si el email ya está registrado
    if (usuarioDAO.existeUsuarioPorEmail(email)) {
        // Redirigir a la vista de agregar con un mensaje de error
        response.sendRedirect("UsuarioControlador?accion=nuevo&error=El+email+ya+está+registrado");
        return;
    }

    Usuario usuario = new Usuario();
    usuario.setNombre(nombre);
    usuario.setEmail(email);
    usuario.setPais(pais);
    usuario.setRol(rol); // Asignar el rol desde el formulario
    usuario.setPassword(password);

    if (usuarioDAO.agregar(usuario)) {
        // Redirigir a la lista de usuarios con un mensaje de éxito
        response.sendRedirect("UsuarioControlador?accion=listar&exito=Usuario+registrado+correctamente");
    } else {
        // Redirigir de vuelta a la vista de agregar con un mensaje de error
        response.sendRedirect("UsuarioControlador?accion=nuevo&error=No+se+pudo+registrar+el+usuario");
    }
}

        private void registrarDesdeFormulario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String pais = request.getParameter("pais");
            String password = request.getParameter("password");

                // Check if email is already registered
                if (usuarioDAO.existeUsuarioPorEmail(email)) {
                    response.sendRedirect(request.getContextPath() + "/Vistas/Registro.jsp?error=El+correo+ya+está+registrado");
                    return; // Stop execution if email is already registered
                }

                    // Create user object
                    Usuario usuario = new Usuario();
                    usuario.setNombre(nombre);
                    usuario.setEmail(email);
                    usuario.setPais(pais);
                    usuario.setRol("user"); // Default role is 'user'
                    usuario.setPassword(password);

                            // Add the user
                            if (usuarioDAO.agregar(usuario)) {
                                // Redirect to login with success message
                                response.sendRedirect(request.getContextPath() + "/Vistas/Login.jsp?registro=exitoso");
                            } else {
                                // Redirect to registration form with error message
                                response.sendRedirect(request.getContextPath() + "/Vistas/Registro.jsp?error=No+se+pudo+registrar+el+usuario");
                            }
}

//Actualiza los datos de un usuario existente
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String email = request.getParameter("email");
                String pais = request.getParameter("pais");
                String rol = request.getParameter("rol");
                String password = request.getParameter("password");

                    Usuario usuario = new Usuario();
                    usuario.setId(id);
                    usuario.setNombre(nombre);
                    usuario.setEmail(email);
                    usuario.setPais(pais);
                    usuario.setRol(rol);
                    usuario.setPassword(password);

                            if (usuarioDAO.actualizar(usuario)) {
                                response.sendRedirect("UsuarioControlador?accion=listar&exito=Usuario actualizado correctamente");
                            } else {
                                response.sendRedirect("Vistas/Editar.jsp?error=No se pudo actualizar el usuario");
                            }
    }
//Elimina un usuario según su ID
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id")); // Obtener el ID del usuario
            System.out.println("ID recibido para eliminar: " + id);

            boolean eliminado = usuarioDAO.eliminar(id); // Llamar al método eliminar del DAO
            if (eliminado) {
                System.out.println("El usuario con ID " + id + " ha sido eliminado.");
                response.sendRedirect("UsuarioControlador?accion=listar&exito=Usuario eliminado correctamente");
            } else {
                System.out.println("No se pudo eliminar el usuario con ID: " + id);
                response.sendRedirect("UsuarioControlador?accion=listar&error=No se pudo eliminar el usuario");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir ID a entero: " + e.getMessage());
            response.sendRedirect("UsuarioControlador?accion=listar&error=ID inválido.");
        }
    }
}