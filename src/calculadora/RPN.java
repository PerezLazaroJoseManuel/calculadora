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
/*
 * 
 * 
 *
 *      	En esta primera refactorización lo que hacemos es reorganizar el código 
 *      poniendo los atributos primero y después los metodos.
 *      
 *      	Luego comprobamos con JUnit que no hay ninguna variación en el resultado.
 *      
 *      	En la segunda refactorización he extraido metodos del metodo resultado para 
 *      simplificarlo y definir mejor las operaciones, además he organizado los metodos 
 *      para que se entienda mejor el código.
 *      
 *      	Lo vuelvo a comprobar con JUnit para verificar que no varian los resultados.
 *       	
 *       	En la tercera refactorización he creado la clase NodoPila fuera de la clase
 *       RPN, así se queda más limpio el código y he creado todos los comentarios para 
 *       generar la documentación con Javadoc.
 *       
 *       	Lo he vuelto a probar con JUnit para verificar que sigue funcionando del mismo 
 *       modo el programa.
 *       
 */

import calculadora.NodoPila;
/**
 * Clase para calcular operaciones (calculadora de notación inversa polaca).
 * @version 3.0, 04/02/2018.
 * @author jmpela.
 *
 */
public class RPN {
	private String commando;
	private NodoPila arriba;
	private double a;
	private double b;
	
	/**
	 * Constructor que pone en nulo <code>arriba</code> y almacena el parámetro <code>commando</code>.
	 * @param commando parámetro del constructor.
	 */
	public RPN(String commando) {
		arriba = null;
		this.commando = commando;
	}
	/**
	 * Método <code>resultado</code>para calcular las operaciones.
	 * @return retorna resultados de las operaciones.
	 */
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
				
				//convertir a double y añadir a la pila 
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
	/**
	 * Método <code>sum</code> calcula la suma
	 */
	private void sum() {
		b = popPila();
		a = popPila();
		pushPila(a + b);
	}
	/**
	 * Método <code>rest</code> calcula la resta
	 */
	private void rest() {
		b = popPila();
		a = popPila();
		pushPila(a - b);
	}
	/**
	 * Método <code>mult</code> calcula la multiplicación
	 */
	private void mult() {
		b = popPila();
		a = popPila();
		pushPila(a * b);
	}
	/**
	 * Método <code>div</code> calcula la división
	 */
	private void div() {
		b = popPila();
		a = popPila();
		pushPila(a / b);
	}
	/**
	 * Método <code>pow</code> calcula la potencia
	 */
	private void pow() {
		b = popPila();
		a = popPila();
		pushPila(Math.pow(a, b));
	}
	/**
	 * Método <code>modul</code> calcula el módulo
	 */
	private void modul() {
		b = popPila();
		a = popPila();
		pushPila(a % b);
	}
	/**
	 * Método <code>popPila</code> inicializa <code>dato_arriba</code> parámetro que almacena el valor de <code>dato</code> del objeto <code>arriba</code>
	 * y a su vez almacena en <code>arriba</code> el objeto <code>abajo</code>.
	 * @return retorna parámetro <code>dato_arriba</code>.
	 */
	public double popPila() {
		double dato_arriba = arriba.dato;
		arriba = arriba.abajo;
		return dato_arriba;
	}
	/**
	 * Método <code>pushPila</code> crea un objeto del constructor <code>NodoPila</code> y almacena el double que le pasamos 
	 * en la variable <code>dato</code>.
	 * @param nuevo_dato, parámetro que pasamos para que lo almacene en dato por medio del objeto arriba.
	 */
	public void pushPila(double nuevo_dato) {
		NodoPila nuevo_nodo = new NodoPila(nuevo_dato, arriba);
		arriba = nuevo_nodo;
	}
}