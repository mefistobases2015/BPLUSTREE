package nodes;

import java.util.Vector;
import datamanagment.TComparable;

/**
 * 
 * @author Andres
 *
 *Nodo de hoja del arbol
 *
 * @param <T> T tipo de lo que se 
 * almacena
 */
public class LeaveNode<T> extends Node<T> {
	/**
	 * Siguiente nodo que va ligado 
	 * ha este nodo hoja
	 */
	private LeaveNode<T> next;
	
	/**
	 * Nodoe anterior en la 
	 * parte de las hojas
	 */
	private LeaveNode<T> prev;
	
	/**
	 * Se crea un nodo hoja
	 * con un orden 
	 * @param order int que es el
	 * orden que va a tener el nodo
	 */
	public LeaveNode(int order){
		super(order);
	}
	
	/**
	 * Constructor de nodo que 
	 * recibe un vector 
	 * @param vec vector en el 
	 * que se basa el nodo
	 */
	public LeaveNode(Vector<TComparable<T>> vec){
		super(vec);
	}

	/**
	 * @return the next
	 */
	public LeaveNode<T> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(LeaveNode<T> next) {
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public LeaveNode<T> getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(LeaveNode<T> prev) {
		this.prev = prev;
	}

}
