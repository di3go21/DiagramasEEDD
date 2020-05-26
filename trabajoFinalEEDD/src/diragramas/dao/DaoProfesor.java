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

		// cargaNotas();

	}

	private void cargaAlumnos(String dni) {
		String colsulta = "select AL.nombre,AL.apellido,AL.dni,CU.siglas,CA.xasignatura from Alumno as AL,"
				+ " Curso as CU, CUR_ASIG as CA " + "where AL.curso = CU.siglas and "
				+ "CA.xcurso = CU.siglas and CA.xprofesor=? order by CU.siglas";
		 ArrayList<Alumno> listal= new ArrayList<Alumno>();
		try {
			PreparedStatement ps = this.getConector().getConexion().prepareStatement(colsulta);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			Alumno al= new Alumno();
			int i=0;
			while (rs.next()) {
				al.setNombre(rs.getString(1));
				al.setNombre(rs.getString(2));
				al.setDni(rs.getString(3));
				al.setCurso(rs.getString(4));
				al.setAsignatura(rs.getString(5));
				listal.add(al);
				System.out.println("alumno "+i+al);
				i++;
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

	private void carganotas(String dni) {
		String consulta = "select CU.siglas,ASI.nombre,AL.nombre,NO.nota "
				+ "from Alumno as AL, Curso as CU, CUR_ASIG as CURA, Asignatura as ASI, NotaAlumno as NO "
				+ "where CURA.xprofesor = ? and CURA.xasignatura = ASI.siglas "
				+ "and NO.xasignatura = ASI.siglas and CURA.xcurso = CU.siglas "
				+ "and AL.curso = CU.siglas	and AL.dni = NO.xalumno";

	}

	public Profesor getProfe() {
		return profe;
	}

	public void setProfe(Profesor profe) {
		this.profe = profe;
	}

}
