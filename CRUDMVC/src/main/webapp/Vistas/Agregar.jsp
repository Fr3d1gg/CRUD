<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Agregar Usuario</title>
</head>
<body>
  <div class="card-header bg-secondary text-white text-center">
    <h1>Agregar un Usuario</h1>
  </div>
    <!-- sI ESQUE HAY ERROR EN EL PARAMETRO -->
             <% String error = request.getParameter("error"); %>
                     <% if (error != null) { %>
                   <div class="alert alert-danger">
                       <%= error %>
                   </div>
               <% } %>
                <!-- Formulario de registro -->
            <form action="<%= request.getContextPath() %>/UsuarioControlador?accion=agregar"method="post" class="p-4 border rounded bg-white shadow">
                <!-- Campo Nombre -->
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Ingrese su nombre" required>
                </div>

                <!-- Campo Email -->
                <div class="mb-3">
                    <label for="email" class="form-label">Correo Electr�nico:</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="Ingrese su email" required>
                </div>

                <!-- Campo Pa�s -->
                <div class="mb-3">
                    <label for="pais" class="form-label">Pa�s:</label>
                    <input type="text" id="pais" name="pais" class="form-control" placeholder="Ingrese su pa�s" required>
                </div>

                <!-- Campo Contrase�a -->
                <div class="mb-3">
                    <label for="password" class="form-label">Contrase�a:</label>
                    <input type="password" id="password" name="password" class="form-control" placeholder="Ingrese su contrase�a" required>
                </div>

                <!-- Campo Rol -->
             <div class="mb-3">
                <label for="rol" class="form-label">Rol:</label>
                <select id="rol" name="rol" class="form-select" required>
                    <option value="user">Usuario</option>
                    <option value="admin">Administrador</option>
                </select>
            </div>

                <!-- Botones -->
                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-success">>Registrar</button>
                    <a href="${pageContext.request.contextPath}/UsuarioControlador?accion=listar" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>