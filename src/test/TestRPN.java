package test;

import junit.framework.TestCase;

import org.junit.jupiter.api.Test;

import calculadora.RPN;

public class TestRPN extends TestCase {
	
	@Test
	public void testRPN() {
		
		 RPN calc1 = new RPN("4 5 +");
		    RPN calc2 = new RPN("4 5 -");
		    RPN calc3 = new RPN("4 5 *");
		    RPN calc4 = new RPN("5 2 /");
		    RPN calc5 = new RPN("2 2 ^");
		    RPN calc6 = new RPN("3 2 %");
		    RPN calc7 = new RPN("10 2 ^ 3 6 * + 9 5 / -");
		    RPN calc8 = new RPN("4 5 * 7 2 / - 3 %");
		    RPN calc9 = new RPN("10 5 + 10 - 6 6 -");
		    RPN calc10 = new RPN("2 M + 3 -");
				assertEquals(9.0, calc1.resultado());	// caso correcto
				assertEquals(-1.0, calc2.resultado());	// caso correcto
				assertEquals(20.0, calc3.resultado());  // caso correcto
				assertEquals(2.5, calc4.resultado());   // caso correcto
				assertEquals(4.0, calc5.resultado());   // caso correcto
				assertEquals(1.0, calc6.resultado());   // caso correcto
				assertEquals(116.2, calc7.resultado()); // caso correcto
				assertEquals(1.5, calc8.resultado());	// caso correcto
				assertEquals(3, calc9.resultado()); // caso incorrecto, comprobar que no puede dejar dos numeros apilados.
				assertEquals(3, calc10.resultado()); // caso incorrecto, comprobar que el valor no puede ser una letra.	
	}	
}
