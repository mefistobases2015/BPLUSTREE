package datamanagment;

/**
 * 
 * @author Andres
 *
 * Esta interfaz debe ser implementada por los 
 * datos que van dentro del Arbol B+ para 
 * poder ser comparados sea como sea el 
 * objeto que se va a insertar
 *
 * @param <T> Tipo de la llave del tipo
 * de objeto que se va a insertar en el 
 * Arbol B+
 */
public interface TComparable<T> {

	/**
	 * Este metodo permite obtener 
	 * la llave que diferencia cada 
	 * objeto de otro.
	 * @return un objeto de tipo T
	 */
	public T getKey();
	
	/**
	 * Este es implementado para definir 
	 * como se sabe que un objeto es mayor 
	 * que otro
	 * @param comp Objeto que debe ser del
	 * mismo tipo T con el que se va a comparar
	 * @return un boolean que va a ser true si 
	 * este objeto es mayor que comp y va 
	 * a ser false en otro caso.
	 */
	public boolean isGreater( TComparable<T> comp );
	
	/**
	 * Este es implementado para definir 
	 * como se sabe que un objeto es menor 
	 * que otro
	 * @param comp Objeto que debe ser del
	 * mismo tipo T con el que se va a comparar
	 * @return un boolean que va a ser true si 
	 * este objeto es menor que comp y va a 
	 * ser false en otro caso.
	 */
	public boolean isLesser( TComparable<T> comp );
	
	/**
	 * Este es implementado para definir 
	 * como se sabe que un objeto es igual 
	 * que otro
	 * @param comp Objeto que debe ser del
	 * mismo tipo T con el que se va a comparar
	 * @return un boolean que va a ser true si 
	 * este objeto es menor que comp y va a 
	 * ser false en otro caso.
	 */
	public boolean isEqual( TComparable<T> comp );
	
}
