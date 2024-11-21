<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Proteger la vista para que solo accedan los administradores
    String rol = (String) session.getAttribute("rol");
    if (rol == null || !rol.equals("admin")) {
        response.sendRedirect(request.getContextPath() + "/Vistas/Login.jsp?error=No tienes acceso");
        return;
    }
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Lista de Usuarios</title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <h1 class="text-center mb-4">Lista de Usuarios</h1>

        <!-- Mostrar mensaje de éxito si existe -->
        <c:if test="${not empty param.exito}">
            <div class="alert alert-success" role="alert">
                ${param.exito}
            </div>
        </c:if>

        <!-- Mostrar mensaje de error si existe -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

        <!-- Botón para agregar un nuevo usuario -->
        <div class="mb-3 text-end">
            <form action="UsuarioControlador" method="get" class="d-inline">
                <input type="hidden" name="accion" value="nuevo">
                <button type="submit" class="btn btn-warning">Agregar Usuario</button>
            </form>
        </div>

        <!-- Tabla de usuarios -->
        <div class="table-responsive">
            <table class="table table-bordered table-striped text-center">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Email</th>
                        <th>País</th>
                        <th>Rol</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iterar sobre la lista de usuarios -->
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.email}</td>
                            <td>${usuario.pais}</td>
                            <td>${usuario.rol}</td>
                            <td>
                                <!-- Botón para Editar -->
                                <form action="UsuarioControlador" method="get" class="d-inline">
                                    <input type="hidden" name="accion" value="editar">
                                    <input type="hidden" name="id" value="${usuario.id}">
                                    <button type="submit" class="btn btn-warning btn-sm">Editar</button>
                                </form>

                                <!-- Botón para Eliminar -->
                                <form action="UsuarioControlador" method="post" class="d-inline">
                                    <input type="hidden" name="accion" value="eliminar">
                                    <input type="hidden" name="id" value="${usuario.id}">
                                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de eliminar este usuario?');">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Enlace para cerrar sesión -->
        <div class="text-center mt-4">
            <a href="<%= request.getContextPath() %>/LoginControlador?logout=true" class="btn btn-danger">Cerrar sesión</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>