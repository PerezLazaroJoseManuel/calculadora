package calculadora;

import java.util.Scanner;
/**
 * Clase con método main para ejecutar programa.
 * @version 3.0 04/02/2018.
 * @author jmpela.
 *
 */
public class TestRPN {
	/**
	 *  metodo main 
	 *  */
	public static void main(String[] args) {
		while(true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Introduce espresion Postfija o teclea \"fin\".");
			String linea = in.nextLine();
			if(linea.equals("fin")) {
				System.out.println("Fin de programa");
				break;
			} else {
				RPN calc = new RPN(linea);
				System.out.printf("El resultado es %f\n", calc.resultado());
			}
		}
	}	
}
