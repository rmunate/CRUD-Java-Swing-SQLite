package controller;

import model.User;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.DAOUser;

public class UserController {
	
	/**
	 * Este metodo crea el usuario en la base de datos.
	 * 
	 * @param cedula
	 * @param nombres
	 * @param correo
	 * @param cargo
	 * @param direccion
	 * @param telefono
	 * @return
	 * @throws SQLException
	 */
	public String insertUser(String cedula, String nombres, String correo, String cargo, String direccion, String telefono) throws SQLException {
		
		//Validar si el usuario ya existe
		DAOUser daoUserValidate = new DAOUser();
		boolean existUser = daoUserValidate.uniqueValidation(cedula);
		if (existUser) {
			return "Ya existe un usuario con el mismo número de Cedula";
		}
		
		// Obtener la fecha y hora actual.
        LocalDateTime now = LocalDateTime.now();

        // Definir el formato deseado.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Formatear la fecha y hora actual.
        String formattedDateTime = now.format(formatter);
        
        // Crear Objeto Usuario.
		User user = new User();
		user.setDni(cedula);
		user.setName(nombres);
		user.setEmail(correo);
		user.setRol(cargo);
		user.setAddress(direccion);
		user.setPhone(telefono);
		user.setCreatedAt(formattedDateTime);
		
		// insertar el valor en la base de datos.
		DAOUser daoUser= new DAOUser();
		boolean created = daoUser.insert(user);
		if (created) {
			return "Se creó el usuario en la base de datos.";
		}
		
		// Retorno por defecto.
		return "Error Al Crear El Usuario";
		
	}
	
	/**
	 * Este metodo retorna los datos de la tabla requeridos en la vista.
	 * @return
	 */
	public ArrayList<User> datatable() {
		DAOUser daoUser= new DAOUser();
		return daoUser.table();
	}
	
	/**
	 * Este metodo actuala el usuario en la base de datos.
	 * 
	 * @param cedula
	 * @param nombres
	 * @param correo
	 * @param cargo
	 * @param direccion
	 * @param telefono
	 * @return
	 * @throws SQLException
	 */
	public String updateUser(String cedula, String nombres, String correo, String cargo, String direccion, String telefono) throws SQLException {
		
        // Crear Objeto Usuario.
		User user = new User();
		user.setDni(cedula);
		user.setName(nombres);
		user.setEmail(correo);
		user.setRol(cargo);
		user.setAddress(direccion);
		user.setPhone(telefono);
		
		// insertar el valor en la base de datos.
		DAOUser daoUser= new DAOUser();
		boolean created = daoUser.update(user);
		if (created) {
			return "Se actualizó el usuario en la base de datos.";
		}
		
		// Retorno por defecto.
		return "Error Al Actualizar El Usuario";
		
	}
	
	/**
	 * Este metodo elimina un registro de la base de datos.
	 * 
	 * @param cedula
	 * @return
	 * @throws SQLException
	 */
	public String deleteUser(String cedula) throws SQLException {
		
        // Crear Objeto Usuario.
		User user = new User();
		user.setDni(cedula);
		
		// insertar el valor en la base de datos.
		DAOUser daoUser= new DAOUser();
		boolean created = daoUser.delete(user);
		if (created) {
			return "Se eliminó el usuario en la base de datos.";
		}
		
		// Retorno por defecto.
		return "Error Al Eliminar El Usuario";
		
	}
	
	/**
	 * Este metodo retorna los datos de la tabla requeridos en la vista filtrados por Rol.
	 * @return
	 */
	public ArrayList<User> search(String role) {
		DAOUser daoUser= new DAOUser();
		return daoUser.searchRol(role);
	}

}
