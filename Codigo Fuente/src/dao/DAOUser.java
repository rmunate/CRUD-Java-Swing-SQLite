package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import connection.SQLite;
import model.User;

public class DAOUser {
	
	//Propiedad para SQLite
	private SQLite sqlite;

	/**
	 * Contructor con inyeccion de la clase SQLite.
	 */
	public DAOUser() {
		sqlite = new SQLite();
	}
	
	/**
	 * Este metodo valida que el numero de cedula a agregar aun no este en la base de datos.
	 * 
	 * @param dni
	 * @return
	 */
	public boolean uniqueValidation(String dni) {
		
        // Variables para la conexión y para el Query
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // Definir el Query de Selección
        String sql = "SELECT COUNT(*) FROM users WHERE dni = ?";
        
        try {
            // Crear conexión con SQLite
            connection = sqlite.conectar();
            pstmt = connection.prepareStatement(sql);
            
            // Establecer el parámetro del PreparedStatement
            pstmt.setString(1, dni);
            
            // Ejecutar el Query
            rs = pstmt.executeQuery();
            
            // Verificar si el DNI ya existe
            if (rs.next()) {
                int count = rs.getInt(1);
                
                //Cerrar Conexion
                sqlite.close();
                
                // Si el conteo es mayor que 0, el DNI ya existe
                return count > 0;
            }
            
            //Cerrar Conexion
            sqlite.close();
            
            // Retornar false si el DNI no existe
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error al consultar la base de datos.");
            e.printStackTrace();
            return false;
        }   
    }
	
	/**
	 * Este metodo inserta valores en la base de datos.
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(User user) throws SQLException {
		
		//Variables para la conexion y para el Query.
	    Connection connection = null;
	    Statement statement = null;
	    
	    // Obtener la fecha y hora actual
        Date now = new Date();

        // Definir el formato deseado
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Formatear la fecha y hora actual
        String formattedDateTime = formatter.format(now);
	    
	    try {
	    	
	        // Crear conexion con SQLite
	        connection = sqlite.conectar();
	        statement = connection.createStatement();
	        
	        // Crear El Query De Creación.
	        String sql = String.format("INSERT INTO users (dni, name, email, rol, address, phone, created_at) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
	                                    user.getDni(), user.getName(), user.getEmail(), user.getRol(), user.getAddress(), user.getPhone(), formattedDateTime);
	        
	        // Ejecutar el Script y cerrar conexion.
	        statement.executeUpdate(sql);
	        
	        //Cerrar Conexion
	        sqlite.close();

	        // Retornar la bandera.
	        return true;
	        
	    } catch (SQLException e) {
	    	
	        System.out.println("Error de SQL: No se pudo crear el usuario en la base de datos. " + e.getMessage());
        
	        return false;
	    }
	}
	
	/**
	 * Este metodo retorna los datos de la tabla de la vista.
	 * @return
	 */
	public ArrayList<User> table(){
		
		// Lista donde estaran todos los datos del usuario.
		ArrayList<User> lista = new ArrayList<User>();
		
		// Variables para le ejecucion de la consulta.
		Connection connection = null;
		PreparedStatement statement =  null;
		ResultSet result = null;
		
		try {
			
			// Ejecucion del Query.
			connection = sqlite.conectar();
			statement = connection.prepareStatement("SELECT * FROM users");
			result = statement.executeQuery();
			
			// Alojar todos los valores en la lista.
			while(result.next()) {
				
				// Crear el objeto de usuario.
				User user = new User();
				user.setDni(result.getString("dni"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setRol(result.getString("rol"));
				user.setAddress(result.getString("address"));
				user.setPhone(result.getString("phone"));
				user.setCreatedAt(result.getString("created_at"));
				
				// Agregarlo a la lista.
				lista.add(user);
			}
			
			sqlite.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista; 
		
	}
	
	/**
	 * Este metodo actualiza los registros en la base de datos.
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public boolean update(User user) throws SQLException {
		
		//Variables para la conexion y para el Query.
	    Connection connection = null;
	    Statement statement = null;
	    
	    try {
	    	
	        // Crear conexion con SQLite
	        connection = sqlite.conectar();
	        statement = connection.createStatement();
	        
	        // Crear El Query De Actuializacion.
	        String sql = String.format("UPDATE users SET name = '%s', email = '%s', rol = '%s', address = '%s', phone = '%s' WHERE dni = '%s'",
	                                    user.getName(), user.getEmail(), user.getRol(), user.getAddress(), user.getPhone(), user.getDni());
	        
	        // Ejecutar el Script y cerrar conexion.
	        statement.executeUpdate(sql);
	        
	        //Cerrar Conexion
	        sqlite.close();

	        // Retornar la bandera.
	        return true;
	        
	    } catch (SQLException e) {
	    	
	        System.out.println("Error de SQL: No se pudo crear el usuario en la base de datos. " + e.getMessage());
        
	        return false;
	    }
	}
	
	/**
	 * Este metodo elimina un usuario de la base de datos.
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	public boolean delete(User user) throws SQLException {
		
		//Variables para la conexion y para el Query.
	    Connection connection = null;
	    Statement statement = null;
	    
	    try {
	    	
	        // Crear conexion con SQLite
	        connection = sqlite.conectar();
	        statement = connection.createStatement();
	        
	        // Crear El Query De Actuializacion.
	        String sql = String.format("DELETE FROM users WHERE dni = '%s'", user.getDni());
	        
	        // Ejecutar el Script y cerrar conexion.
	        statement.executeUpdate(sql);
	        
	        //Cerrar Conexion
	        sqlite.close();

	        // Retornar la bandera.
	        return true;
	        
	    } catch (SQLException e) {
	    	
	        System.out.println("Error de SQL: No se pudo eliminar el usuario en la base de datos. " + e.getMessage());
        
	        return false;
	    }
	}
	
	/**
	 * Este metodo retorna los datos que concuerden con el filtro realizado por rol.
	 * @return
	 */
	public ArrayList<User> searchRol(String role){
		
		// Lista donde estaran todos los datos del usuario.
		ArrayList<User> lista = new ArrayList<User>();
		
		// Variables para le ejecucion de la consulta.
		Connection connection = null;
		PreparedStatement statement =  null;
		ResultSet result = null;
		
		try {
			
			// Ejecucion del Query.
			connection = sqlite.conectar();
			statement = connection.prepareStatement("SELECT * FROM users WHERE rol = '" + role + "'");
			result = statement.executeQuery();
			
			// Alojar todos los valores en la lista.
			while(result.next()) {
				
				// Crear el objeto de usuario.
				User user = new User();
				user.setDni(result.getString("dni"));
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setRol(result.getString("rol"));
				user.setAddress(result.getString("address"));
				user.setPhone(result.getString("phone"));
				user.setCreatedAt(result.getString("created_at"));
				
				// Agregarlo a la lista.
				lista.add(user);
			}
			
			sqlite.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista; 
		
	}

}
