package diragramas.model;

import java.util.HashMap;

public class Alumno extends Persona{
	
	private HashMap<Asignatura,Integer> notas;//no se si hay qinicializarla si se le asigna un objeto ya hecho
	private String curso;
	

	public Alumno() {
		super();
		
	}

	public Alumno(String nombre, String apellido, String dni) {
		super(nombre, apellido, dni);
	}
	public Alumno(String nombre, String apellido, String dni,String curso) {
		super(nombre, apellido, dni);
		this.curso=curso;
	}

	public HashMap<Asignatura, Integer> getNotas() {
		return notas;
	}

	public void setNotas(HashMap<Asignatura, Integer> notas) {
		this.notas = notas;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
	@Override
	public String toString() {
		return "Alumno ["+super.toString()+ ", curso=" + curso +", \nnotas=" + notas + "]";
	}
	
	
	
	
	
	

}
