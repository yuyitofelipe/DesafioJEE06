package cl.praxis.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cl.praxis.models.database.Conn;
import cl.praxis.models.dto.UserDTO;
import cl.praxis.models.dao.UserDAO;
import cl.praxis.models.dao.UserDAOImpl;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        try {
            UserDTO usuario = null;

            try (Connection conn = Conn.getConnect() ) {
                UserDAO usuarioDAO = new UserDAOImpl(conn);
                usuario = usuarioDAO.getUsuarioPorCorreo(correo);

                if (usuario != null && usuario.getPassword().equals(password)) {
                	HttpSession session = request.getSession();
                	session.setAttribute("usuario", usuario);
                    response.sendRedirect("home.jsp");
                    
                } else {
                    request.setAttribute("mensaje", "usuario o contraseña incorrecta");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                
            } catch (SQLException e) {
            	
                throw new ServletException("Error en la autenticación", e);
            }
            
        } catch (Exception e) {
        	
            throw new ServletException("Error en la autenticación", e);
        }
    }
}