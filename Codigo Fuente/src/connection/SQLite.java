package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {

	// Instancia de la Conexion
	private Connection cx = null;

	/**
	 * Crea la conexion a SQLite
	 * @return
	 */
	public Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			cx = DriverManager.getConnection("jdbc:sqlite:datebase.db");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cx;
	}

	/**
	 * Cierra la conexion a la base de datos.
	 */
	public void close() {
		try {
			cx.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
