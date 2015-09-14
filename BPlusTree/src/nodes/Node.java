package nodes;

import java.util.List;
import java.util.Vector;
import datamanagment.TComparable;

/**
 * 
 * @author Andres
 *
 *Nodo que va a ser la estructura
 *alamacenada en el arbol B+
 *
 * @param <T> Tipo que se va a 
 * almacenar dentro del nodo
 */ 
public class Node<T> {
	
	//TIPOS DE NODO
	public static final int SIMPLE_ROOT_NODE = 0;
	public static final int COMPLEX_ROOT_NODE = 1;
	public static final int INTERNAL_NODE = 2;
	public static final int LEAVE_NODE = 3;
	
	/**
	 *Tipo de nodo el cual especifica 
	 *que tipo de nodo es este si este 
	 *la raiz o un nodo interno 
	 */
	private int node_type;
	
	/**
	 * Vector que va a almacenar las llaves 
	 * de cada nodo o los valores en sí para 
	 * las hojas
	 */
	protected Vector<TComparable<T>> keys;
	
	/**
	 * Orden del nodo
	 */
	protected int order;
	
	/**
	 * Nodo padre de este nodo 
	 */
	private InternalNode<T> father = null;
	
	/**
	 * Constructor con el orden del arbol
	 * @param order int que es el orden 
	 * de los nodos del arbol
	 */
	public Node(int order){
		this.order = order;
		keys = new Vector<TComparable<T>>(this.order);
	}
	
	/**
	 * Este constructor se crea a partir
	 * de un vector
	 * @param keys Vector de Comparables
	 */
	public Node(Vector<TComparable<T>> keys){
		this.keys = keys;
		order = keys.capacity();
	}
	
	/**
	 * Se obtiene el Comparable que
	 * está en la posción del vector 
	 * del nodo
	 * @param i posicion del Comparable
	 * @return Comparable de tal posicion
	 */
	public TComparable<T> getKey(int i){
		return keys.elementAt(i);
	}

	/**
	 * @return the node_type
	 */
	public int getNode_type() {
		return node_type;
	}

	/**
	 * @param node_type the node_type to set
	 */
	public void setNode_type(int node_type) {
		this.node_type = node_type;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	
	/**
	 * Inserta solo en el caso que el nodo 
	 * tenga un tipo de SIMPLE_ROOT_NODE
	 * @param comp valor que se agrega al 
	 * nodo 
	 */
	public void insert(TComparable<T> comp){
		if(this.node_type == Node.SIMPLE_ROOT_NODE ||
				this.node_type == Node.LEAVE_NODE){
			//Si no tiene elementos el nodo
			if(keys.isEmpty()){
				keys.addElement(comp);
			//si tiene elementos y no ha llegado a la capacidad 
			}else if(keys.size() < keys.capacity()){
				//si la llave que se inserta es mayor que la ultima llave
				//esta va desues de esa llave
				if(keys.get(keys.size()-1).isLesser(comp)){
					keys.addElement(comp);
				}else{
					//si no se compara si es menor que las otras llaves
					//y se inserta antes que esa llave
					for (int i = 0; i < keys.size(); i++) {
						if(keys.elementAt(i).isGreater(comp)){
							keys.add(i, comp);
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Este metodo permite conocer si 
	 * el nodo esta lleno
	 * @return true si esta lleno, false
	 * si no esta lleno
	 */
	public boolean isFull(){
		return keys.size() >= order;
	}
	
	/**
	 * A la hora de partir el vector del nodo
	 * por que se ha llenado retorna lado 
	 * izquierdo del arreglo
	 * @return un Vector de Comparables
	 */
	public Vector<TComparable<T>> splitLeft(){
		if(this.isFull()){
			Vector<TComparable<T>> left_vector = new Vector<TComparable<T>>(order);
			
			List<TComparable<T>> left = keys.subList(0, (order/2));
			
			left_vector.addAll(left);
			
			return left_vector;
		}
		return null;
	}
	
	/**
	 * A la hora de partir el vector del nodod
	 * por que se ha llenado retorna el lado
	 * derecho del arreglo 
	 * @return un Vector de Comparables
	 */
	public Vector<TComparable<T>> splitRight(){
		if(this.isFull()){
			Vector<TComparable<T>> right_vector = new Vector<TComparable<T>>(order);
			
			List<TComparable<T>> right = keys.subList((order/2), order);
			
			right_vector.addAll(right);
			
			return right_vector;
		}
		return null;
	}
	/**
	 * Retorna la llave del medio del 
	 * arreglo 
	 * @return TComparable<T> que 
	 * es la llave del medio del arreglo
	 */
	public TComparable<T> getMidKey(){
		if(this.isFull()){
			return keys.get(order/2);
		}
		return null;
	}
	
	/**
	 * Retorna el maximo valor del arreglo
	 * de este nodo
	 * @return Comparable qeu es el maximo
	 * valor de la llaves este el nodo
	 */
	public TComparable<T> getMaxKey(){
		return keys.lastElement();
	}
	
	/**
	 * Retorna el minimo valor del arreglo
	 * de este nodo
	 * @return TComparable que es el minimo
	 * valor de las llaves en este nodo
	 */
	public TComparable<T> getMinKey(){
		return keys.firstElement();
	}
	
	public void print(){
		for (int i = 0; i < keys.size(); i++) {
			System.out.format("|%s|",keys.get(i).getKey().toString());
		}
		System.out.println();
	}

	/**
	 * @return the father
	 */
	public InternalNode<T> getFather() {
		return father;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(InternalNode<T> father) {
		this.father = father;
	}
	
}
