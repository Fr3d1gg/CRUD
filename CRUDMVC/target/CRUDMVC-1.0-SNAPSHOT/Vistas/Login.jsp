<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <title>Login</title>
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <!-- Card con el formulario -->
                <div class="card shadow-lg border border-dark">
                    <div class="card-header bg-secondary text-white text-center">
                        <h4>Iniciar Sesión</h4>
                    </div>
                    <div class="card-body">
                        <form action="<%= request.getContextPath() %>/LoginControlador" method="post">
                            <!-- Campo de Email -->
                            <div class="mb-3">
                                <label for="email" class="form-label">Email:</label>
                                <input type="email" id="email" name="email" class="form-control" placeholder="Ingrese su email" required>
                            </div>
                            <!-- Campo de Contraseña -->
                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña:</label>
                                <input type="password" id="password" name="password" class="form-control" placeholder="Ingrese su contraseña" required>
                            </div>
                            <!-- Botón de Iniciar Sesión -->
                            <div class="d-grid">
                                <button type="submit" class="btn btn-secondary">Iniciar Sesión</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer text-center">
                        <p class="mb-0">¿No tienes una cuenta? <a href="<%= request.getContextPath() %>/UsuarioControlador?accion=registro" class="text-primary">Regístrate aquí</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>