package cl.praxis.models.dao;

import java.sql.SQLException;

import cl.praxis.models.dto.UserDTO;

public interface UserDAO {
	
	void registrarUsuario(UserDTO usuario) throws SQLException;
	UserDTO getUsuarioPorCorreo(String correo) throws SQLException;

}
