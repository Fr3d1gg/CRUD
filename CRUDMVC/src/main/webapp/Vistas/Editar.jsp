<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Editar Usuario</title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow">
                    <div class="card-header bg-secondary text-white text-center">
                        <h2>Editar Usuario</h2>
                    </div>
                    <div class="card-body">
                        <form action="UsuarioControlador?accion=actualizar" method="post">
                            <!-- Campo oculto para el ID -->
                            <input type="hidden" name="id" value="${usuario.id}">
                            
                            <!-- Campo Nombre -->
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre:</label>
                                <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" class="form-control" required>
                            </div>
                            
                            <!-- Campo Email -->
                            <div class="mb-3">
                                <label for="email" class="form-label">Email:</label>
                                <input type="email" id="email" name="email" value="${usuario.email}" class="form-control" required>
                            </div>
                            
                            <!-- Campo País -->
                            <div class="mb-3">
                                <label for="pais" class="form-label">País:</label>
                                <input type="text" id="pais" name="pais" value="${usuario.pais}" class="form-control" required>
                            </div>
                            
                            <!-- Campo Rol -->
                            <div class="mb-3">
                                <label for="rol" class="form-label">Rol:</label>
                                <select name="rol" id="rol" class="form-select">
                                    <option value="user" ${usuario.rol == 'user' ? 'selected' : ''}>Usuario</option>
                                    <option value="admin" ${usuario.rol == 'admin' ? 'selected' : ''}>Administrador</option>
                                </select>
                            </div>
                            
                            <!-- Campo Contraseña -->
                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña:</label>
                                <input type="password" id="password" name="password" value="${usuario.password}" class="form-control">
                            </div>
                            
                            <!-- Botones -->
                            <div class="d-flex justify-content-between">
                                <button type="submit" class="btn btn-success">Guardar Cambios</button>
                                <a href="UsuarioControlador?accion=listar" class="btn btn-danger">Cancelar</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>