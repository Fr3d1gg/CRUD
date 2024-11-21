<%
    String rol = (String) session.getAttribute("rol");
    if (rol == null || !rol.equals("user")) {
        response.sendRedirect(request.getContextPath() + "/Vistas/Login.jsp?error=No tienes acceso");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Panel de Usuario</title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <!-- Card principal -->
        <div class="card shadow">
            <div class="card-header bg-secondary text-white text-center">
                <h1>Panel de Usuario</h1>
            </div>
            <div class="card-body">
                <p class="h4 text-center">¡Hola, bienvenido a tu área de usuario!</p>
                <hr>
                <!-- Mostrar datos del usuario -->
                <div class="mb-3">
                    <p class="mb-1"><strong>Email registrado:</strong> ${sessionScope.usuario}</p>
                    <p class="mb-1"><strong>Rol:</strong> ${sessionScope.rol}</p>
                </div>

                <!-- Botón de cerrar sesión -->
                <div class="text-center">
                    <a href="<%= request.getContextPath() %>/LoginControlador?logout=true" class="btn btn-danger btn-lg">Cerrar sesión</a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>