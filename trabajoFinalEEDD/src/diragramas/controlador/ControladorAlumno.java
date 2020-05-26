package diragramas.controlador;

import diragramas.dao.Controlador;
import diragramas.dao.DaoAlumno;
import diragramas.model.Alumno;

public class ControladorAlumno implements Controlador {

	private final String TABLA="Alumno";
	private DaoAlumno daoAl;
	private String dni;
	private String pass;

	public ControladorAlumno(String dni, String pass) {
		this.dni = dni;
		this.pass = pass;
	}

	@Override
	public boolean logOK() {
		boolean logued = false;
		this.daoAl = new DaoAlumno();
		logued = this.daoAl.estaLogueado(this.dni, this.pass, this.TABLA);
		this.daoAl.getConector().desconecta();
		return logued;
	}

	public void cargaDatos() {

		this.daoAl.getConector().reconecta();
		this.daoAl.cargaAlumno(dni);
		this.daoAl.getConector().desconecta();

	}

	public Alumno getAlumno() {
		return daoAl.getAlum();
	}

	

}
