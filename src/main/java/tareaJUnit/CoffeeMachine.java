package tareaJUnit;

import java.util.Scanner;

public class CoffeeMachine {

	// Ingredients stock
	// coffee chocolate milk sugar
	// Max 10 10 80 40
	static int[] Stock = { 0, 0, 0, 0 };
	static int[] maxStock = { 10, 10, 80, 40 };

	// Initial configuration
	static Recipe[] recipes = {
			// name price coffee chocolate milk sugar
			new Recipe("Mocha", 500, 2, 2, 4, 1), new Recipe("Espresso", 200, 1, 0, 0, 0),
			new Recipe("Late", 400, 2, 0, 5, 2) };

	public static void Instructions() {
		System.out.print("Modo de espera, ingrese numero de accion a realizar: \n" + "1. Agregar inventario \n"
				+ "2. Verificar inventario \n" + "3. Comprar bebida \n");
	}

	public static void PrintCoffeeOptions() {
		System.out.print("Indique numero de la bebida que desea comprar \n");
		for (int i = 0; i < recipes.length; i++) {
			System.out
					.print(String.format("%d: %s, Precio: %d \n", i + 1, recipes[i].getName(), recipes[i].getPrice()));
		}
	}

	public static boolean isParsable(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

	public static String checkInventoryInput(String[] input) {
		for (int i = 0; i < input.length; i++) {
			if (!isParsable(input[i])) {
				return "numero no entero";
			} else if (Integer.parseInt(input[i]) < 0) {
				return "numero negativo";
			}
		}
		return "";
	}

	public static void updateInventory(String[] input) {
		for (int i = 0; i < input.length; i++) {
			Stock[i] = Math.min(Stock[i] + Integer.parseInt(input[i]), maxStock[i]);
		}
		return;
	}

	public static void checkPayment(int payment, Recipe recipe) {
		// Check inventory
		for (int i = 0; i < Stock.length; i++) {
			if (Stock[i] < recipe.getIngredients()[i]) {
				System.out.print(String.format("No hay suficientes ingredientes para realizar la bebida, devolviendo %d \n", payment));
				return;
			}
		}
	
		// Check payment
		int change = payment - recipe.getPrice();
		if (change < 0) {
			System.out.print(String.format("Pago insuficiente, devolviendo %d \n", payment));
			return;
		} else {
			// Update inventory
			for (int i = 0; i < Stock.length; i++) {
				Stock[i] -= recipe.getIngredients()[i];
			}
			
			System.out.print(String.format("Pago realizado correctamente, vuelto: %d \n", change));
			System.out.print(String.format("Su bebida %s esta lista. \n", recipe.getName() ));
		}
	}
	
	public static void printInventoryStatus() {
		System.out.print("Inventario actual:  \n");
		System.out.print(String.format("Cafe: %d \n", Stock[0]));
		System.out.print(String.format("Chocolate: %d \n", Stock[1]));
		System.out.print(String.format("Leche: %d \n", Stock[2]));
		System.out.print(String.format("Azucar: %d \n", Stock[3]));
	}

	public static void main(String[] args) {
		Instructions();
		while (true) {
			Scanner myObj = new Scanner(System.in); // Create a Scanner object
			int action = 0;
			try {
				action = Integer.parseInt(myObj.nextLine()); // Read user input
				switch (action) {

				case 1:
					boolean inventoryInputValid = false;
					String[] auxStock = {};
					while (!inventoryInputValid) {
						System.out.print(
								"Para agregar inventario indique cada unidad a agregar en el siguiente orden separadas por un espacio: \n Cafe Chocolate Leche Azucar \n");
						auxStock = myObj.nextLine().trim().split(" ");
						if (auxStock.length == 4) {
							inventoryInputValid = checkInventoryInput(auxStock) == "";
						}
					}
					updateInventory(auxStock);
					System.out.print("\n");
					break;

				case 2:
					printInventoryStatus();
					System.out.print("\n");
					break;
					
				case 3:
					int auxCoffee = 0;
					int auxPayment = -1;

					// Select coffee
					while (!(auxCoffee == 1 || auxCoffee == 2 || auxCoffee == 3)) {
						PrintCoffeeOptions();
						try {
							auxCoffee = Integer.parseInt(myObj.nextLine());
							if (!(auxCoffee == 1 || auxCoffee == 2 || auxCoffee == 3)) {
								System.out.print("No es un numero valido \n");
							}
						} catch (Exception e) {
							System.out.print("No es un numero  \n");
						}
					}

					// Insert Payment
					while (auxPayment <= 0) {
						System.out.print("Ingrese pago: \n");
						try {
							auxPayment = Integer.parseInt(myObj.nextLine());
							if (auxPayment <= 0) {
								System.out.print("No es un numero valido \n");
							}
						} catch (Exception e) {
							System.out.print("No es un numero  \n");
						}
					}

					// Pay
					checkPayment(auxPayment, recipes[auxCoffee - 1]);
					System.out.print("\n");

					break;
					
				default:
					System.out.print("Accion no es un numero valido \n");
					System.out.print("\n");
					Instructions();
					break;
					
				}
				Instructions();
			} catch (Exception e) {
				System.out.print("Accion no es un numero valido \n");
				System.out.print("\n");
				Instructions();
			}
		}

	}

}
