package nodes;

import java.util.Vector;
import datamanagment.TComparable;

/**
 * 
 * @author Andres
 *
 *Nodo que hace el 
 *
 * @param <T>
 */
public class InternalNode<T> extends Node<T> {

	/**
	 * Referencias a las que apunta el nodo
	 */
	Vector<Node<T>> references;
	
	/**
	 * Se crea el nodo con el orden del arbol
	 * @param order orden del arbol
	 */
	public InternalNode(int order){
		super(order);
		references = new Vector<Node<T>>(super.getOrder()+1);
	}
	
	/**
	 * Se crea el nodo ya con un vector 
	 * @param vec vector con las llaves.
	 */
	public InternalNode(Vector<TComparable<T>> vec){
		super(vec);
		references = new Vector<Node<T>>(super.getOrder()+1);
	}
	
	/**
	 * Retorna la referencia en una posición dada
	 * @param i posicion de la referencia
	 * @return Nodo al que se referencia
	 */
	public Node<T> getReference(int i){
		return references.elementAt(i);
	}
	
	/**
	 * Inserta una referencia respecto a las llaves
	 * @param reference Nodo que va a ser la referencia
	 * a insertar
	 */
	public void insertReference(Node<T> reference){
		//si la referencia va al final
		if(reference.getMinKey().isGreater(this.getMaxKey()) || 
				reference.getMinKey().isEqual(this.getMaxKey())){
			reference.setFather(this);
			if(references.size() >= keys.size()+1){
				references.insertElementAt(reference, keys.size());
			}else if(references.size() >= keys.size()){
				references.addElement(reference);
			}
		}else{
			//si es menor que una llave se pone antes que la llave
			for (int i = 0; i < keys.size(); i++) {
				if(this.getKey(i).isGreater(reference.getMaxKey())){
					reference.setFather(this);
					if(references.size() <=i){
						references.addElement(reference);
					}else if(references.size() > i){
						references.insertElementAt(reference, i);
					}
					break;
				}
			}
		}
	}
	
}
