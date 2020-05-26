package diragramas.controlador;

import diragramas.dao.DaoAlumno;
import diragramas.model.Alumno;

public class ControladorAlumno {

	private DaoAlumno daoAl;
	private String dni;
	private String pass;

	public ControladorAlumno(String dni, String pass) {
		this.dni = dni;
		this.pass = pass;
	}

	public boolean LogOK() {
		boolean logued=false;
		this.daoAl= new DaoAlumno();
		logued=this.daoAl.estaLogueado(this.dni, this.pass);
		this.daoAl.getConector().desconecta();
		return logued;
	}
	
	public void carga() {

		this.daoAl= new DaoAlumno();
		this.daoAl.cargaAlumno(dni);
		this.daoAl.getConector().desconecta();
		
		
	}
	
	public Alumno getAlumno() {
		return daoAl.getAlum();
	}

}
