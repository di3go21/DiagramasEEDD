package diragramas.ui;

import java.util.Scanner;

import diragramas.controlador.ControladorAlumno;
import diragramas.controlador.ControladorProfesor;

public class IU {

	public static Scanner teclado;
	public static String[] cred;

	public static void home() {

		teclado = new Scanner(System.in);
		System.out.println("BIENVENIDO");

		System.out.println("eres profesor o alumno?");

		String persona = teclado.nextLine();

		if (persona.equalsIgnoreCase("alumno"))
			IUAlumno();

		else if (persona.equalsIgnoreCase("profesor"))
			IUProfesor();
		else
			System.out.println("input no válido!");
	}

	private static void IUProfesor() {
		cred = pideDatos();

		ControladorProfesor ctrProfe = new ControladorProfesor(cred[0], cred[1]);
		if (ctrProfe.logOK()) {
			ctrProfe.cargaDatos();
			System.out.println("bienvenido! "+ctrProfe.getProfe().getNombre()+" "+ctrProfe.getProfe().getApellido());
			//if()
			System.out.println("tus alumnos son:");
			ctrProfe.getProfe().getAlumnos().forEach(x->System.out.println(x));
			
						

		} else
			credencialesErroneas();

	}

	private static void IUAlumno() {

		// TODO hay que hacer las validaciones de los imputs
		cred = pideDatos();

		ControladorAlumno ctrAl = new ControladorAlumno(cred[0], cred[1]);
		if (ctrAl.logOK()) {
			ctrAl.cargaDatos();
			System.out.println("bienvenido! " + ctrAl.getAlumno().getNombre() + " " + ctrAl.getAlumno().getApellido());
			if (ctrAl.getAlumno().getNotas().isEmpty())
				System.out.println("Aún no tienes ninguna nota registrada");
			else {
				System.out.println("Las notas que tienes registradas son:");

				ctrAl.getAlumno().getNotas().forEach((x, v) -> {
					System.out.printf("%-35s : %s %n", x.getNombre(), v.toString());
				});

			}
			System.out.println("adios!");
		} else
			credencialesErroneas();
	}

	private static String[] pideDatos() {
		String[] datos = new String[2];
		System.out.println("dime tu dni");
		datos[0] = teclado.nextLine();

		System.out.println("dime tu contaseña");
		datos[1] = teclado.nextLine();

		return datos;

	}

	private static void credencialesErroneas() {

		System.out.println("credenciales erroneas!");

	}

}
