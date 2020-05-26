package diragramas.model;


import java.util.HashMap;

public class Profesor extends Persona{
	
	private HashMap<String, Alumno> alumXCurso;
	
	public Profesor(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
	}

	public HashMap<String, Alumno> getAlumXCurso() {
		return alumXCurso;
	}

	public void setAlumXCurso(HashMap<String, Alumno> alumXCurso) {
		this.alumXCurso = alumXCurso;
	}
}
