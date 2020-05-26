package diragramas.ui;

import java.util.Scanner;

import diragramas.controlador.ControladorAlumno;

public class Main {
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		System.out.println("BIENVENIDO");

		System.out.println("eres profesor o alumno?");

		String persona = teclado.next();
		String pass;

		
		//TODO hay que hacer las validaciones de los imputs
		if (persona.equalsIgnoreCase("alumno")) {
			System.out.println("dime tu dni");
			persona = teclado.nextLine();
			persona = teclado.nextLine();
			System.out.println("dime tu contaseña");
			pass = teclado.nextLine();

			ControladorAlumno ctrAl = new ControladorAlumno(persona, pass);
			if (ctrAl.LogOK()) {
				ctrAl.carga();
				System.out
						.println("bienvenido! " + ctrAl.getAlumno().getNombre() + " " + ctrAl.getAlumno().getApellido());
				if (ctrAl.getAlumno().getNotas().isEmpty())
					System.out.println("Aún no tienes ninguna nota registrada");
				else {
					System.out.println("Las notas que tienes registradas son:");
					
					ctrAl.getAlumno().getNotas().forEach((x,v)->{
						System.out.printf("%-35s : %s %n",x.getNombre(),v.toString());
					});
					
				}
				System.out.println("adios!");
			} else
				System.out.println("credenciales erroneas!");

		}

		else
			System.out.println("no eres alumno");
		//TODO  toda la parte del profesor
	}

}
