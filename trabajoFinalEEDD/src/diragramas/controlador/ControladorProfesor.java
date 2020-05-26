package diragramas.controlador;

import diragramas.dao.Controlador;
import diragramas.dao.DaoProfesor;
import diragramas.model.Alumno;
import diragramas.model.Profesor;

public class ControladorProfesor implements Controlador{
	
	private final String TABLA="Profesor";
	DaoProfesor daoProfe;
	private String dni;
	private String pass;

	public ControladorProfesor(String dni, String pass) {
		this.dni = dni;
		this.pass = pass;
	}
	

	@Override
	public boolean logOK() {
		boolean logued=false;
		this.daoProfe= new DaoProfesor();//podemos hacer un contecta tipo factory
		logued=this.daoProfe.estaLogueado(dni, pass,TABLA);
		this.daoProfe.getConector().desconecta();
		return logued;
	}

	@Override
	public void cargaDatos() {
		this.daoProfe.getConector().reconecta();
		this.daoProfe.cargaProfe(dni);
		this.daoProfe.getConector().desconecta();
		
	}
	
	public Profesor getProfe() {
		return daoProfe.getProfe();
	}


	public void actualizaNotas(Alumno x) {
		this.daoProfe.getConector().reconecta();
		this.daoProfe.actualizaNota(x);
		this.daoProfe.getConector().desconecta();
	}


	public void insertaNota(Alumno x) {
		this.daoProfe.getConector().reconecta();
		this.daoProfe.ponerNuevaNota(x);
		this.daoProfe.getConector().desconecta();
		
	}


	
	
	

	

}
