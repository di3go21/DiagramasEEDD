package diragramas.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoLogin {
	
	protected Conexion conexion;

	public DaoLogin() {
		super();
		conexion= new Conexion(); 
	}
	
	public boolean estaLogueado(String dni,String pass) {
		String consulta= "select count(*) from Alumno where dni =? and pass = md5(?)";
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
	
	

}
