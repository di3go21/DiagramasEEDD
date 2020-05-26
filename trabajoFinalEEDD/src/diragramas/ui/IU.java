package diragramas.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import diragramas.controlador.ControladorAlumno;
import diragramas.controlador.ControladorProfesor;
import diragramas.model.Alumno;

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
			System.out.println(
					"bienvenido! " + ctrProfe.getProfe().getNombre() + " " + ctrProfe.getProfe().getApellido());
			// if()
			System.out.println("tus alumnos son:");
			System.out.printf("%-10s %-13s %-8s %-5s %-5s %n", "Nombre", "Apellido", "DNI", "CURSO", "ASIG");
			ctrProfe.getProfe().getAlumnos().forEach(x -> {
				System.out.printf("%-10s %-13s %-8s %-5s %-5s %n", x.getNombre(), x.getApellido(), x.getDni(),
						x.getCurso(), x.getAsignatura());
			});

			System.out.println("Las notas de los alumnos son:");
			ctrProfe.getProfe().getAlumnos().forEach(x -> {
				if (x.getNotaPuntual() >= 0)
					System.out.println(
							x.getNombre() + " " + x.getApellido() + " " + x.getAsignatura() + " " + x.getNotaPuntual());
			});
			int opc = 0;
			while (opc != 2) {
				System.out.println("quieres poner notas???");
				System.out.println("1.- SI        2.-NO");
				opc = pideint();
				boolean chivato;
				if (opc == 1) {
					System.out.println("dime el dni del alumno");
					String dniAl = teclado.next();
					System.out.println("dime el primer apellido del alumno");
					String apell = teclado.next();
					System.out.println("las siglas de la asignatura");
					String siglas = teclado.next();
					System.out.println("por último la nota");
					int nota = pideint();
					chivato = false;

					
					//###############################
					//TODO hay que cambiar el arraylist por hashcode y podemos ahorrarnos mucho codigo
					//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
					
					
					ctrProfe.getProfe().getAlumnos().forEach(x -> {

						if (x.getDni().contentEquals(dniAl))
							if (x.getApellido().contains(apell))
								if (x.getAsignatura().contentEquals(siglas))
									if (x.getNotaPuntual() >= 0) {
										x.setNotaPuntual(nota);
										ctrProfe.actualizaNotas(x); // ESTO NO SE COMO RESOLVERLO
									} else {
										x.setNotaPuntual(nota);
										ctrProfe.insertaNota(x);
									}
								
					});

				} else if (opc == 2) {
					System.out.println("adios");
				} else
					System.out.println("opcion no válida");
			}

		} else
			credencialesErroneas();

	}

	private static int pideint() {
		// TODO
		return teclado.nextInt();
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
