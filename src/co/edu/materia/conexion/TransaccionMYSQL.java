package co.edu.materia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import co.edu.materia.repositorio.Itransaccion;

public class TransaccionMYSQL implements Itransaccion{

	private String url = "jdbc:mysql://localhost:3306/ejemplo2";
	private String usuario = "root";
	private String clave = "12345";
	private String driver = "com.mysql.jdbc.Driver";
	 
	
	public TransaccionMYSQL() {
		//constructor
		url = "jdbc:mysql://localhost:3306/ejemplo2"; 
		usuario = "root";
		clave = "12345";
		driver = "com.mysql.jdbc.Driver";
	}

	@Override
	public Connection conectar() {
		// TODO Auto-generated method stub
		Connection conexion;
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(url,usuario,clave);
			return conexion;
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error Clase no Existe" +e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error al conectar SQL:" +e.getMessage());
		} catch (Exception e) {
			System.out.println("Error General" +e.getMessage());
			e.printStackTrace();
		}
		return null;
		
	}

	public static void main(String[] args) {
		PreparedStatement ps = null;
		try {
			
		Itransaccion transaccion = new TransaccionMYSQL();
		//Obtengo la conexion a MYSQL
		Connection con = transaccion.conectar();
		//Hago la consulta al documento 
		String sql = "select * from materia where idMateria = '4' ";
		//Valido si el sql es correcto para enviar a la BD.
		ps = con.prepareStatement(sql);
		//EJECUTO CONSULTA EN BD
		ResultSet rs= ps.executeQuery();
		//Recupero los datos de la BD
		
		if (rs.next()) {
			//Muestro los datos recuperados
			System.out.println("nmateria =" + rs.getString(2));
		}
			rs.close();
		//cierro cursor
			ps.close();
		//cierro estructura preparada
			con.close();
		//cierro la conexion
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}

}