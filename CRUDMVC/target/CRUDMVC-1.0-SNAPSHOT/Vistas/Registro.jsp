<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Registro de Usuario</title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h1 class="text-center">Registro de Usuario</h1>
                    <% String error = request.getParameter("error"); %>
                    <% if (error != null) { %>
                        <div class="alert alert-danger">
                            <%= error %>
                        </div>
                    <% } %>

                <form action="<%= request.getContextPath() %>/UsuarioControlador?accion=registrar" method="post" class="mt-4 p-4 border rounded bg-white shadow">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Correo Electrónico:</label>
                        <input type="email" id="email" name="email" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="pais" class="form-label">País:</label>
                        <input type="text" id="pais" name="pais" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Contraseña:</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>

                    <button type="submit" class="btn btn-secondary w-100">Registrarse</button>
                    <a href="<%= request.getContextPath() %>/Vistas/Login.jsp" class="btn btn-danger w-100 mt-2">Cancelar</a>
                </form>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>