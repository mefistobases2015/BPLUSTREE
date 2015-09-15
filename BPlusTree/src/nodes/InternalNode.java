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
	private Vector<Node<T>> references;
	
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
	 * Este metodo retorna la cantidad 
	 * de referencias que tiene este nodo
	 * @return int que tiene la cantida de 
	 * referencias que tiene este nodo.
	 */
	public int getReferencesSize(){
		return references.size();
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
			if(references.size() >= keys.size()+1){
				references.insertElementAt(reference, keys.size());
			}else if(references.size() >= keys.size()){
				references.addElement(reference);
			}
		}else{
			//si es menor que una llave se pone antes que la llave
			for (int i = 0; i < keys.size(); i++) {
				if(this.getKey(i).isGreater(reference.getMaxKey())){
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

	/**
	 * Parte el nodo en dos y le agrega las referencias correspondientes 
	 * @return
	 */
	public InternalNode<T> splitInternalLeft(){
		if(this.isFull()){
			InternalNode<T> leftIntNode = new InternalNode<T>(super.splitLeft());
			
			Vector<Node<T>> left_references = new Vector<Node<T>>(order+1);
			left_references.addAll(references.subList(0, (order/2)+1 ));
			
			leftIntNode.setReferences(left_references);
			
			updateFatherRef(left_references, leftIntNode);
			
			return leftIntNode;
		}
		return null;
		
	}
	
	/**
	 * Parte el nodo en dos y le agrega las referencias
	 * asignadas a ese lado.
	 * @return 
	 */
	public InternalNode<T> splitInternalRight(){
		if(this.isFull()){
			InternalNode<T> rightIntNode = new InternalNode<T>(super.splitRight());
			
			Vector<Node<T>> right_references = new Vector<Node<T>>(order+1);
			right_references.addAll(references.subList((order/2)+1, order+1));
			
			rightIntNode.setReferences(right_references);
			
			updateFatherRef(right_references, rightIntNode);
			
			return rightIntNode;
		}
		return null;
	}
	
	/**
	 * Actualiza el padre de las referencias 
	 * @param pReferences referencias a las que se les va actualizar el padre
	 * @param pFather nuevo padre
	 */
	private void updateFatherRef(Vector<Node<T>> pReferences, InternalNode<T> pFather){
		for (int i = 0; i < pReferences.size(); i++) {
			pReferences.get(i).setFather(pFather);
		}
	}

	/**
	 * @return the references
	 */
	public Vector<Node<T>> getReferences() {
		return references;
	}

	/**
	 * @param references the references to set
	 */
	public void setReferences(Vector<Node<T>> references) {
		this.references = references;
	}
	
	/**
	 * Esta función permite conocer si una referencia 
	 * esta dentro de las referencias 
	 * @param reference referencias a probar
	 * @return true si esta referencia se encuentra 
	 * dentro de las referencias.
	 */
	public boolean isReference(Node<T> reference){
		return references.contains(reference);
	}
	
	/**
	 * Esta función retorna el indice en el que 
	 * se encuentra una referencia.
	 * @param reference referencia de la que 
	 * se quiere saber la posicion
	 * @return la poscion de la referencia y si 
	 * no se encuentra en las referencias retorna
	 * -1
	 */
	public int indexOfRef(Node<T> reference){
		return references.indexOf(reference);
	}
	
	/**
	 * Elimina una referencia de las lista de referencias.
	 * @param reference referencia a borrar del nodo
	 */
	public void removeRef(Node<T> reference){
		references.remove(reference);
	}
	
	/**
	 * Cambia el valor de una referencia en una posicion
	 * @param index posicion en la que se quiere cambiar la 
	 * referencia 
	 * @param reference nueva referencia
	 */
	public void setRef(int index, Node<T> reference){
		references.set(index, reference);
	}
	
}
