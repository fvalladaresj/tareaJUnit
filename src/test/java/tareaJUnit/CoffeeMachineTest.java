package tareaJUnit;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CoffeeMachineTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void UC1() {
		// GIVEN
		// Todos enteros positivos
		CoffeeMachine.Stock[0] = 0;
		CoffeeMachine.Stock[1] = 0;
		CoffeeMachine.Stock[2] = 0;
		CoffeeMachine.Stock[3] = 0;
		
		// WHEN
		String[] addStock = {"10", "6", "20", "15"};
		
		// THEN
		assertTrue(CoffeeMachine.checkInventoryInput(addStock));
		int arrExpected[] = { 10, 6, 20, 15};
		assertArrayEquals(CoffeeMachine.Stock, arrExpected);
		
		
		// GIVEN
		// Uno negativo
		CoffeeMachine.Stock[0] = 0;
		CoffeeMachine.Stock[1] = 0;
		CoffeeMachine.Stock[2] = 0;
		CoffeeMachine.Stock[3] = 0;
		
		// WHEN
		String[] addStock1 = {"10", "6", "20", "-15"};
		
		// THEN
		assertFalse(CoffeeMachine.checkInventoryInput(addStock1));
		int arrExpected2[] = { 0, 0, 0, 0};
		assertArrayEquals(CoffeeMachine.Stock, arrExpected2);
		
		
		// GIVEN
		// Uno no entero
		CoffeeMachine.Stock[0] = 0;
		CoffeeMachine.Stock[1] = 0;
		CoffeeMachine.Stock[2] = 0;
		CoffeeMachine.Stock[3] = 0;
		
		// WHEN
		String[] addStock2 = {"10", "6", "20", "1.5"};
		
		// THEN
		assertFalse(CoffeeMachine.checkInventoryInput(addStock2));
		int arrExpected3[] = { 0, 0, 0, 0};
		assertArrayEquals(CoffeeMachine.Stock, arrExpected3);
	}
	
	@Test
	public void UC2() {
		// GIVEN
		// Inventario con estos datos
		CoffeeMachine.Stock[0] = 10;
		CoffeeMachine.Stock[1] = 6;
		CoffeeMachine.Stock[2] = 20;
		CoffeeMachine.Stock[3] = 15;
		
		// WHEN
		// Se pide ver el inventario
		
		// THEN
		assertEquals(CoffeeMachine.printInventoryStatus(), "Inventario actual: \n Cafe: 10 \n Chocolate: 6 \n Leche: 20 \n Azucar: 15 \n");
		
	}
	
	@Test
	public void UC3() {
		// GIVEN 
		//Inventario suficiente sin vuelto
		CoffeeMachine.Stock[0] = 10;
		CoffeeMachine.Stock[1] = 10;
		CoffeeMachine.Stock[2] = 10;
		CoffeeMachine.Stock[3] = 10;
		
		// WHEN
		// Bebida solicitada: 1. ("Mocha", 500, 2, 2, 4, 1)
		int coffeeSelected = 0;
		// Pago: 500
		int payment = 500;
		
		// THEN
		assertEquals(CoffeeMachine.checkPayment(payment, CoffeeMachine.recipes[coffeeSelected]), "Su bebida Mocha esta lista. \n Pago realizado correctamente, vuelto: 0 \n");
		int arrExpected[] = { 8, 8, 6, 9};
		assertArrayEquals(CoffeeMachine.Stock, arrExpected);
		
		
		// GIVEN 
		// Inventario suficiente con vuelto
		CoffeeMachine.Stock[0] = 10;
		CoffeeMachine.Stock[1] = 10;
		CoffeeMachine.Stock[2] = 10;
		CoffeeMachine.Stock[3] = 10;
		
		// WHEN
		// Bebida solicitada: 1. Mocha ($500)
		coffeeSelected = 0;
		// Pago: 1000
		payment = 1000;
		
		// THEN
		assertEquals(CoffeeMachine.checkPayment(payment, CoffeeMachine.recipes[coffeeSelected]), "Su bebida Mocha esta lista. \n Pago realizado correctamente, vuelto: 500 \n");
		int arrExpected2[] = { 8, 8, 6, 9};
		assertArrayEquals(CoffeeMachine.Stock, arrExpected2);
		
		
		// GIVEN 
		// Inventario insuficiente
		CoffeeMachine.Stock[0] = 0;
		CoffeeMachine.Stock[1] = 0;
		CoffeeMachine.Stock[2] = 0;
		CoffeeMachine.Stock[3] = 0;
		
		// WHEN
		// Bebida solicitada: 1. Mocha ($500)
		coffeeSelected = 0;
		// Pago: 500
		payment = 500;
		
		// THEN
		assertEquals(CoffeeMachine.checkPayment(payment, CoffeeMachine.recipes[coffeeSelected]), "No hay suficientes ingredientes para realizar la bebida, devolviendo 500 \n");
		int arrExpected3[] = { 0, 0, 0, 0};
		assertArrayEquals(CoffeeMachine.Stock, arrExpected3);
		
		
		// GIVEN 
		// Pago insuficiente
		CoffeeMachine.Stock[0] = 10;
		CoffeeMachine.Stock[1] = 10;
		CoffeeMachine.Stock[2] = 10;
		CoffeeMachine.Stock[3] = 10;
		
		// WHEN
		// Bebida solicitada: 1. Mocha ($500)
		coffeeSelected = 0;
		// Pago: 200
		payment = 200;
		
		// THEN
		assertEquals(CoffeeMachine.checkPayment(payment, CoffeeMachine.recipes[coffeeSelected]), "Pago insuficiente, devolviendo 200 \n");
		int arrExpected4[] = { 10, 10, 10, 10};
		assertArrayEquals(CoffeeMachine.Stock, arrExpected4);
		
	}
}
