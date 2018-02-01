package calculadora;


/*
 * 		Objetivo: Obtener código refactorización a partir de otro que no lo esta.
 * 
 *		Tarea: Para esta tarea, se refactorizará un programa mal escrito, sin
 *		cambiar la forma en que funciona el programa. Este programa, RPN.java
 *		es una calculadora de notación inversa polaca que utiliza una pila.
 *
 *			Reverse Polish notation (RPN) Notación Polaca inversa; por ejemplo
 *		la expresión: 4 * 5 - 7 / 2 % 3 nos da 1,5 respetando la prioridad de 
 *		los operadores en notación RPN sería: 4 5 * 7 2 / - 3 % (pues no podemos 
 *		poner los paréntesis para alterar la prioridad)
 *
 *		se debe reorganizar este código usando al menos tres de las reglas 
 *		vistas en clase.
 */
/**
 * 
 * @author jmpel
 *
 *      	En esta primera refactorización lo que hacemos es reorganizar el código 
 *      poniendo los atributos primero y después los metodos.
 *      
 *      	Luego comprobamos con JUnit que no hay ninguna variación en el resultado.
 *       
 */

class NodoPila {
	public NodoPila abajo;
	public double dato;
	
	public NodoPila(double dato, NodoPila abajo) {
		this.dato = dato;
		this.abajo = abajo;
	}
}

public class RPN {
	private String commando;
	private NodoPila arriba;
	
	public void pushPila(double nuevo_dato) {
		NodoPila nuevo_nodo = new NodoPila(nuevo_dato, arriba);
		arriba = nuevo_nodo;
	}
	
	public double popPila() {
		double dato_arriba = arriba.dato;
		arriba = arriba.abajo;
		return dato_arriba;
	}
	
	public RPN(String commando) {
		arriba = null;
		this.commando = commando;
	}
	
	public double resultado() {
		double a, b;
		int j;
			
		for(int i = 0; i < commando.length(); i++) {
			//si es un dijito
			if(Character.isDigit(commando.charAt(i))) {
				double numero;
				
				// obtener un string a partir de un numero
				String temp = "";
				for(j = 0; (j < 100) && (Character.isDigit(commando.charAt(i)) || (commando.charAt(i) == '.')); j++, i++) {
					temp = temp + String.valueOf(commando.charAt(i));
				}
				
				//convertir a double y añadir a la pila 
				numero = Double.parseDouble(temp);
				pushPila(numero);
			} else if(commando.charAt(i) == '+') {
				b = popPila();
				a = popPila();
				pushPila(a + b);
			} else if(commando.charAt(i) == '-') {
				b = popPila();
				a = popPila();
				pushPila(a - b);
			} else if(commando.charAt(i) == '*') {
				b = popPila();
				a = popPila();
				pushPila(a * b);
			} else if(commando.charAt(i) == '/') {
				b = popPila();
				a = popPila();
				pushPila(a / b);
			} else if(commando.charAt(i) == '^') {
				b = popPila();
				a = popPila();
				pushPila(Math.pow(a, b));
			} else if(commando.charAt(i) == '%') {
				b = popPila();
				a = popPila();
				pushPila(a % b);
			} else if(commando.charAt(i) != ' ') {
				throw new IllegalArgumentException();
			}
		}
		
		double val 	= popPila();
		
		if(arriba != null) {
			throw new IllegalArgumentException();
		}
		
		return val;
	}
	
}