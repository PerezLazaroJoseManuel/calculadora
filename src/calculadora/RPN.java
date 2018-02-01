package calculadora;


/*
 * 		Objetivo: Obtener c�digo refactorizaci�n a partir de otro que no lo esta.
 * 
 *		Tarea: Para esta tarea, se refactorizar� un programa mal escrito, sin
 *		cambiar la forma en que funciona el programa. Este programa, RPN.java
 *		es una calculadora de notaci�n inversa polaca que utiliza una pila.
 *
 *			Reverse Polish notation (RPN) Notaci�n Polaca inversa; por ejemplo
 *		la expresi�n: 4 * 5 - 7 / 2 % 3 nos da 1,5 respetando la prioridad de 
 *		los operadores en notaci�n RPN ser�a: 4 5 * 7 2 / - 3 % (pues no podemos 
 *		poner los par�ntesis para alterar la prioridad)
 *
 *		se debe reorganizar este c�digo usando al menos tres de las reglas 
 *		vistas en clase.
 */
/**
 * 
 * @author jmpel
 *
 *      	En esta primera refactorizaci�n lo que hacemos es reorganizar el c�digo 
 *      poniendo los atributos primero y despu�s los metodos.
 *      
 *      	Luego comprobamos con JUnit que no hay ninguna variaci�n en el resultado.
 *      
 *      	En la segunda refactorizaci�n he extraido metodos del metodo resultado para 
 *      simplificarlo y definir mejor las operaciones.
 *      
 *      	Lo vuelvo a comprobar con JUnit para verificar que no varian los resultados.
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
				
				//convertir a double y a�adir a la pila 
				numero = Double.parseDouble(temp);
				pushPila(numero);
			} else if(commando.charAt(i) == '+') {
				sum();
			} else if(commando.charAt(i) == '-') {
				rest();
			} else if(commando.charAt(i) == '*') {
				mult();
			} else if(commando.charAt(i) == '/') {
				div();
			} else if(commando.charAt(i) == '^') {
				pow();
			} else if(commando.charAt(i) == '%') {
				modul();
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

	private void modul() {
		double a;
		double b;
		b = popPila();
		a = popPila();
		pushPila(a % b);
	}

	private void pow() {
		double a;
		double b;
		b = popPila();
		a = popPila();
		pushPila(Math.pow(a, b));
	}

	private void div() {
		double a;
		double b;
		b = popPila();
		a = popPila();
		pushPila(a / b);
	}

	private void mult() {
		double a;
		double b;
		b = popPila();
		a = popPila();
		pushPila(a * b);
	}

	private void rest() {
		double a;
		double b;
		b = popPila();
		a = popPila();
		pushPila(a - b);
	}

	private void sum() {
		double a;
		double b;
		b = popPila();
		a = popPila();
		pushPila(a + b);
	}
	
}