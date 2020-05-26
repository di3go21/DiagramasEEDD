package diragramas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {

	private String usuario = "usuarioEEDD";
	private String pass = "javiprofe";
	private String baseDatos = "eedd";
	private String url = "jdbc:mysql://localhost/" + baseDatos;

	private Connection conex = null;

	public Conector() {

		try {
			conex = DriverManager.getConnection(url, usuario, pass);

			if (conex != null) {
		//		System.out.println("conectando con la BBDD: " + baseDatos + " ....OK");
			}

		} catch (SQLException e) {
	//		System.out.println("Problemas de conexion con la BBDD");
		}
	}

	public Connection getConexion() {
		return conex;
	}
	
	public void desconecta() {
		//System.out.print("cerrando conexion con DB... ");
		try {
			conex.close();
//			System.out.println("OK");
		} catch (SQLException e) {
	//		System.out.println("FAIL");
		}
	}

}
