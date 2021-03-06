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
	public static final int INTERNAL_NODE = 1;
	public static final int LEAVE_NODE = 2;
	
	/**
	 *Tipo de nodo el cual especifica 
	 *que tipo de nodo es este si este 
	 *la raiz o un nodo interno 
	 */
	protected int node_type;
	
	/**
	 * Vector que va a almacenar las llaves 
	 * de cada nodo o los valores en s� para 
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
	protected InternalNode<T> father = null;
	
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
	 * est� en la posci�n del vector 
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
	 * Retorna la cantidad de llaves 
	 * que tiene el nodo
	 * @return int con la cantidd 
	 * de llaves que tiene el nodo
	 */
	public int keysSize(){
		return keys.size();
	}
	
	/**
	 * A la hora de partir el vector del nodo
	 * por que se ha llenado retorna lado 
	 * izquierdo del arreglo
	 * @return un nodo con las llaves del 
	 * nodo izuierdo cuando se llene un nodo
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
	 * @return un Node con las llaves de nodo 
	 * derecho de cuando se llena el nodo
	 */
	public Vector<TComparable<T>> splitRight(){
		if(this.isFull()){
			Vector<TComparable<T>> right_vector = new Vector<TComparable<T>>(order);
			
			List<TComparable<T>> right = keys.subList(((order/2)+1), order);
			
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
	
	/**
	 * Imprime el nodo
	 */
	public void print(){
		for (int i = 0; i < keys.size(); i++) {
			System.out.format("|%s|",keys.get(i).getKey().toString());
		}
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
	
	/**
	 * Retorna si la llavae dada esta dentro de 
	 * las llaves que se tienen almacenadas 
	 * @param comp Llave que se va a probar 
	 * si esta dentro del arrglo
	 * @return true si se encuentra dentro 
	 * del arreglo de llave.
	 */
	public boolean isKey(TComparable<T> comp){
		return keys.contains(comp);
	}
	
	/**
	 * Retorna el indice de la llave dada
	 * @param comp Llave a la que se le va a 
	 * buscar el indice
	 * @return el indice de la llave o -1 
	 * si la llave no esta dentro del arreglo
	 */
	public int indexOfKey(TComparable<T> comp){
		return keys.indexOf(comp);
	}
	
	/**
	 * Borra de las llaves la especificada
	 * @param comp llave a borrar
	 */
	public void removeKey(TComparable<T> comp){
		keys.remove(comp);
	}
	
	/**
	 * Coloca una nueva llave en la posicion 
	 * especificada 
	 * @param index posicion de la nueva llave
	 * @param comp llave a colocar
	 */
	public void setKey(int index,TComparable<T> comp){
		keys.set(index, comp);
	}
	
	/**
	 * Este metodo dice si un nodo tiene padre
	 * @return true si tiene padre 
	 */
	public boolean haveFather(){
		return father != null;
	}
	
}
