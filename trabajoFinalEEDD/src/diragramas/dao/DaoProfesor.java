package diragramas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import diragramas.model.Alumno;
import diragramas.model.Profesor;

public class DaoProfesor extends DaoLogin {

	private Profesor profe;

	public DaoProfesor() {
		super();
		this.setProfe(null);
	}

	public void cargaProfe(String dni) {

		this.setProfe(this.cargaProfe(this.getConector().getConexion(), dni));

		cargaAlumnos(dni);
		if (!this.profe.getAlumnos().isEmpty())
			cargaNotas();

	}

	private void cargaAlumnos(String dni) {
		String colsulta = "select AL.nombre,AL.apellido,AL.dni,CU.siglas,CA.xasignatura from Alumno as AL,"
				+ " Curso as CU, CUR_ASIG as CA " + "where AL.curso = CU.siglas and "
				+ "CA.xcurso = CU.siglas and CA.xprofesor=? order by CU.siglas";
		ArrayList<Alumno> listal = new ArrayList<Alumno>();
		try {
			PreparedStatement ps = this.getConector().getConexion().prepareStatement(colsulta);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			Alumno al;
			int i = 0;
			while (rs.next()) {
				al = new Alumno();
				al.setNombre(rs.getString(1));
				al.setApellido(rs.getString(2));
				al.setDni(rs.getString(3));
				al.setCurso(rs.getString(4));
				al.setAsignatura(rs.getString(5));
				listal.add(al);
			}

			this.profe.setAlumnos(listal);
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private Profesor cargaProfe(Connection con, String dni) {
		String consulta = "Select nombre, apellido from Profesor where dni=?";
		Profesor profe = null;

		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			rs.next();
			profe = new Profesor(rs.getString(1), rs.getString(2), dni);
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return profe;
	}

	private void cargaNotas() {

		String consulta = "select NO.nota from NotaAlumno as NO where NO.xalumno = ?" + " and NO.xasignatura=?";
		try {
			PreparedStatement ps = this.getConector().getConexion().prepareStatement(consulta);
			ResultSet rs = null;
			for (Alumno al : this.getProfe().getAlumnos()) {

				ps.setString(1, al.getDni());
				ps.setString(2, al.getAsignatura());
				rs = ps.executeQuery();
				if (rs.next())
					al.setNotaPuntual(rs.getInt(1));
				else
					al.setNotaPuntual(-1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void actualizaNota(Alumno x) {
		String consulta = "update NotaAlumno set nota=? where xasignatura=? and xalumno=?";

		PreparedStatement ps;
		try {
			ps = this.getConector().getConexion().prepareStatement(consulta);

			ps.setInt(1, x.getNotaPuntual());
			ps.setString(2, x.getAsignatura());
			ps.setString(3, x.getDni());
			ps.execute();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void ponerNuevaNota(Alumno x) {}
	

	public Profesor getProfe() {
		return profe;
	}

	public void setProfe(Profesor profe) {
		this.profe = profe;
	}

}
