/*
Cada controlador se va a enfocar en un conjunto especifico de tareas
Asi evitamos que se vuelva dificil de entender.
Los servlets son fundamentales en este proyecto porque actúan como el puente entre
las solicitudes del cliente (navegador) y la lógica del servidor
*/
package com.mycompany.crudmvc.controlador;

import com.mycompany.crudmvc.modelo.Usuario;
import com.mycompany.crudmvc.modelo.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
 //Antes de iniciar cabe recalcar que Los servlets procesan las solicitudes y determinan qué acción ejecutar
//Determinamos la URL a la cual estara asociada este controlador,ya que se enviaran
//las solicitudes hacia esta ruta
@WebServlet("/LoginControlador")
public class LoginControlador extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    
    /*
    Este metodo nos permite manejar la logica para cerrar la sesion de nuestrro
    usuario
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Manejar logout
        String logout = request.getParameter("logout");

        if (logout != null && logout.equals("true")) {
            HttpSession session = request.getSession(false); // Obtener sesión existente sin crear una nueva
            if (session != null) {
                session.invalidate(); // Invalidar la sesión actual
                System.out.println("Sesión cerrada exitosamente.");
            }
            response.sendRedirect("Vistas/Login.jsp"); // Redirigir al login
        }
    }

    /*
    Tiene como objetivo,procesar la solicitud de inicio de sesion,
    redirigir segun el rol que tengan los usuarios y gestionar la sesion del usuario
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Obtener parámetros del formulario de inicio de sesión
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Validar usuario con el DAO
    Usuario usuario = usuarioDAO.validarUsuario(email, password);

    if (usuario != null) {
        // Credenciales válidas
        System.out.println("Usuario encontrado: " + usuario.getNombre() + ", Rol: " + usuario.getRol());

        // Crear sesión y guardar atributos del usuario
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario.getEmail());
        session.setAttribute("rol", usuario.getRol());

        // Redirigir según el rol del usuario
        if ("admin".equals(usuario.getRol())) {
            response.sendRedirect("UsuarioControlador?accion=listar"); // Vista de administrador
        } else if ("user".equals(usuario.getRol())) {
            response.sendRedirect("Vistas/UsuarioNormal.jsp"); // Vista de usuario normal
        } else {
            // Si el rol no coincide con ninguno esperado
            System.out.println("Rol desconocido: " + usuario.getRol());
            response.sendRedirect("Vistas/Login.jsp?error=rol");
        }
    } else {
        // Credenciales inválidas
        System.out.println("Usuario o contraseña incorrectos.");
        request.setAttribute("error", "Usuario o contraseña incorrectos.");
        request.getRequestDispatcher("Vistas/Login.jsp").forward(request, response);
    }
}
}
