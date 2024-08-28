package cl.praxis.controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cl.praxis.models.database.Conn;
import cl.praxis.models.dto.UserDTO;
import cl.praxis.models.dao.UserDAO;
import cl.praxis.models.dao.UserDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registro.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String correo = request.getParameter("correo");
        String nick = request.getParameter("nick");
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        int peso = Integer.parseInt(request.getParameter("peso"));

        try {
            UserDTO usuario = new UserDTO();
            usuario.setCorreo(correo);
            usuario.setNick(nick);
            usuario.setNombre(nombre);
            usuario.setPassword(password);
            usuario.setPeso(peso);

            try (Connection connection = Conn.getConnect()) {
                UserDAO usuarioDAO = new UserDAOImpl(connection);
                UserDTO existente = usuarioDAO.getUsuarioPorCorreo(correo);

                if (existente != null) {
                    request.setAttribute("mensaje", "Correo Existente");
                    request.getRequestDispatcher("registro.jsp").forward(request, response);
                } else {
                    usuarioDAO.registrarUsuario(usuario);
                    response.sendRedirect("home.jsp");
                }
            } catch (SQLException e) {
                throw new ServletException("Error al registrar usuario", e);
            }
        } catch (Exception e) {
            throw new ServletException("Error al registrar usuario", e);
        }
    }
}