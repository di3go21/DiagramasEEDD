package diragramas.model;

public class Asignatura {
	private String nombre;
	private String abreviatura;
	private int nota;
	
	public Asignatura() {
		super();
	}

	

	public Asignatura(String nombre, String abreviatura) {
		super();
		this.nombre = nombre;
		this.abreviatura = abreviatura;
	}



	public Asignatura(String nombre, String abreviatura, int nota) {
		super();
		this.nombre = nombre;
		this.abreviatura = abreviatura;
		this.nota = nota;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAbreviatura() {
		return abreviatura;
	}
	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}



	@Override
	public String toString() {
		return "Asignatura [nombre=" + nombre + "]";
	}
	

	
}
