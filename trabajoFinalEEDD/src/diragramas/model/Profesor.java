package diragramas.model;


import java.util.ArrayList;

public class Profesor extends Persona{
	
	private  ArrayList<Alumno> alumnos;
	
	public Profesor(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
	}

	public ArrayList<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(ArrayList<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	

	

	@Override
	public String toString() {
		return "Profesor [alumnos=" + alumnos + "]";
	}

	public String imprimeAlumnos() {
		return "hola";
	}




	
	
}
