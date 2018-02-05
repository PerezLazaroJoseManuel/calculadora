package calculadora;
/**
 * Clase para crear objetos <code>NodoPila</code>.
 * @version 3.0, 04/02/2018.
 * @author jmpela.
 *
 */
class NodoPila {
	public NodoPila abajo;
	public double dato;
	/**
	 * Constructor.
	 * @param dato parámetro que almacena <code>dato</code> por medio del constructor.
	 * @param abajo parámetro que almacena <code>abajo</code> por medio del constructor.
	 */
	public NodoPila(double dato, NodoPila abajo) {
		this.dato = dato;
		this.abajo = abajo;
	}
}
