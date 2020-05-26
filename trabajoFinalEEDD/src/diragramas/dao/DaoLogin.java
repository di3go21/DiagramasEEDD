package diragramas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoLogin {
	
	private Conector conexion;

	public DaoLogin() {
		super();
		conexion= new Conector(); 
	}
	
	public boolean estaLogueado(String dni,String pass,String tabla) {
		String consulta= "select count(*) from TABLA where dni =? and pass = md5(?)";
		consulta=consulta.replaceFirst("TABLA", tabla);
		
		boolean logued=false;
		try {
			PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
			ps.setString(1, dni);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1)==1)
				logued=true;
			rs.close();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return logued;
		}
		return logued;
	
	}

	public Conector getConector() {
		return conexion;
	}

	
	
	

}
