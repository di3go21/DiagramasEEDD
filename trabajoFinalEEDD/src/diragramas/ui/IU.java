package diragramas.ui;

import java.util.Scanner;

import diragramas.controlador.ControladorAlumno;

public class IU {

	public static Scanner teclado = new Scanner(System.in);

	public static void home() {

		System.out.println("BIENVENIDO");

		System.out.println("eres profesor o alumno?");

		String persona = teclado.next();

		if (persona.equalsIgnoreCase("alumno"))
			IUAlumno();

		else if(persona.equalsIgnoreCase("profesor"))
			IUProfesor();
		else
			System.out.println("input no válido!");
	}

	private static void IUProfesor() {
		// TODO Auto-generated method stub

	}

	private static void IUAlumno() {

		// TODO hay que hacer las validaciones de los imputs
		String pass;
		System.out.println("dime tu dni");
		String persona = teclado.nextLine();
		persona = teclado.nextLine();
		System.out.println("dime tu contaseña");
		pass = teclado.nextLine();

		ControladorAlumno ctrAl = new ControladorAlumno(persona, pass);
		if (ctrAl.LogOK()) {
			ctrAl.carga();
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
			System.out.println("credenciales erroneas!");

	}

}
