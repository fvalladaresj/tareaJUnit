package tareaJUnit;

import java.util.Scanner;

public class CoffeeMachine {
	public static void Instructions() {
		System.out.print("Ingrese numero de accion a realizar: \n" +
				"1. Agregar inventario \n" +
				"2. Verificar inventario \n" +
				"3. Comprar bebida \n"
		);
	}

	public static void main(String[] args) {
		Instructions();
		while (true) {
		    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		    int action = 0;
			try { 
				action = myObj.nextInt();  // Read user input
				switch (action) {
					case 1:
						System.out.print("Agregar inventario  \n");
						break;
					case 2:
						System.out.print("Verificar inventario  \n");
						break;
					case 3:
						System.out.print("Comprar bebida \n");
						break;
					default:
						System.out.print("Accion no es un numero valido \n");
						Instructions();
						break;
				}
			} catch (Exception e){
				System.out.print("Accion no es un numero \n");
				Instructions();
			}		
		}

	}

}
