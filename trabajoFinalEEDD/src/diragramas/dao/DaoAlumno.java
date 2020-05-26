package diragramas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import diragramas.model.Alumno;
import diragramas.model.Asignatura;

public class DaoAlumno extends DaoLogin {

	private Alumno alum;

	public DaoAlumno() {
		super();
		alum = null;
	}

	public void cargaAlumno(String dni) {

		this.alum = this.cargaAlumno(this.conexion.getConexion(), dni);

		cargaMapNotas(dni);

	}

	public Alumno getAlum() {
		return alum;
	}

	public void setAlum(Alumno alum) {
		this.alum = alum;
	}

	private Alumno cargaAlumno(Connection con, String dni) {

		String consulta = "select nombre,apellido,curso from Alumno where dni =?";
		Alumno al = null;
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			al = new Alumno();
			rs.next();
			al.setNombre(rs.getString(1));
			al.setApellido(rs.getString(2));
			al.setDni(dni);
			al.setCurso(rs.getString(3));
			rs.close();
			ps.close();
			return al;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	private void cargaMapNotas(String dni) {

		HashMap<Asignatura, Integer> notas = new HashMap<Asignatura, Integer>();
		String consulta = "Select ASIG.nombre,ASIG.siglas, NA.nota "
				+ "from Alumno as A, NotaAlumno as NA, Asignatura as ASIG " + "where A.dni=NA.xalumno "
				+ "and ASIG.siglas = NA.xasignatura " + "and A.dni=?";

		try {
			PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notas.put(new Asignatura(rs.getString(1), rs.getString(2)), rs.getInt(3));
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.alum.setNotas(notas);

	}
	
	public static void main(String[] args) {
		
		DaoAlumno dao = new DaoAlumno();
		
		dao.cargaAlumno("112233");
		dao.cargaMapNotas("112233");
		System.out.println(dao.getAlum().toString());
		
	}

}
