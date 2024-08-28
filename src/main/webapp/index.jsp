<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StartUp</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4 text-center">StartUp Page</h1>
        <div class="row justify-content-center">
            <div class="col-md-4">
                <form action="RegisterServlet" method="get">
                    <button type="submit" class="btn btn-primary btn-block">Registrar</button>
                </form>
            </div>
            <div class="col-md-4">
                <form action="LoginServlet" method="get">
                    <button type="submit" class="btn btn-success btn-block">Logear</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
