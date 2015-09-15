package tree;

import java.util.LinkedList;
import java.util.Queue;

import datamanagment.Pair;
import datamanagment.TComparable;
import nodes.InternalNode;
import nodes.LeaveNode;
import nodes.Node;

/**
 * 
 * @author Andres
 *
 * Clase del arbol, se van  hacer 
 * las inserciones, eliminaciones,
 * y busquedas 
 *
 */
public class Tree<T> {
	/**
	 * Raiz del arbol
	 */
	private Node<T> root;
	/**
	 * Orden del arbol
	 */
	private int order;
	
	/**
	 * Constructor del arbol donde 
	 * se da el orden del arbol
	 * @param order orden del arbol
	 */
	public Tree(int order){
		this.order = order;
		root = new LeaveNode<T>(this.order);
		root.setNode_type(Node.SIMPLE_ROOT_NODE);
		((LeaveNode<T>)(root)).setNext(null);
	}
	
	/**
	 * Este metodo inserta un valor al 
	 * arbol 
	 * @param comp valor a insertar
	 */
	public void insert(TComparable<T> comp){
		//el root inicial se llena
		if(root.getNode_type() == Node.SIMPLE_ROOT_NODE){
			root.insert(comp);
			if(root.isFull()){
				root = splitNode(root);
				root.setNode_type(Node.INTERNAL_NODE);
			}
		}
		//el root es complejo
		else if(root.getNode_type() == Node.INTERNAL_NODE){
			
			Node<T> node = search(comp);
			node.insert(comp);
			
			if(node.isFull()){
				splitNode(node);
			}
		}
	}
	
	/**
	 * Parte un noco cuando se llena
	 * y genera las referencias 
	 * @param node Nodo al que se 
	 * le va a hacer el split
	 */
	private Node<T> splitNode(Node<T> node){
		if(node.getNode_type() == Node.SIMPLE_ROOT_NODE){
			return splitSRootNode(node);
		}
		else if(node.getNode_type() == Node.LEAVE_NODE){
			splitLeaveNode((LeaveNode<T>)node);
		}
		else if(node.getNode_type() == Node.INTERNAL_NODE){
			splitInternalNode((InternalNode<T>) node);
		}
		return null;
	}
	
	/**
	 * Division para el caso en que la raiz se llena por primera
	 * vez y se divide 
	 * @param node Raiz del arbol
	 * @return la nueva raiz del arbol
	 */
	private Node<T> splitSRootNode(Node<T> node){
		//toma la llave central
		TComparable<T> center_key = node.getMidKey();
		//parte el arreglo en dos nodos
		LeaveNode<T> left_node = new LeaveNode<T>(node.splitLeft());
		LeaveNode<T> right_node = new LeaveNode<T>(node.splitRight());
		//se le agrega la llave central al izquierdo 
		//ya que este va a ser una hoja del arbol
		right_node.insert(center_key);
		//crea la nueva raiz y se le agrega la llave central
		InternalNode<T> new_root = new InternalNode<T>(order);
		new_root.insert(center_key);
		new_root.setFather(node.getFather());
		//se agregan los dos nodos a la raiz
		new_root.insertReference(left_node);
		new_root.insertReference(right_node);
		//se le greaga el tipo y el padre
		left_node.setFather(new_root);
		left_node.setNode_type(Node.LEAVE_NODE);
		
		right_node.setFather(new_root);
		right_node.setNode_type(Node.LEAVE_NODE);
		
		//se coloca como siguiente a ese nodo
		left_node.setNext(right_node);
		//se coloca como previo a este nodo
		right_node.setPrev(left_node);
		
		return new_root;
	}
	
	/**
	 * Hace una division de una hoja
	 * @param node hoja a la que se le va a 
	 * hacer la division
	 */
	private void splitLeaveNode(LeaveNode<T> node){
		//se toma el padre de la hoja
		InternalNode<T> father = node.getFather();
		//se toma la llave central
		TComparable<T> central_key = node.getMidKey();
		//los arreglos hijos del central
		LeaveNode<T> left_node = new LeaveNode<T>(node.splitLeft());
		LeaveNode<T> right_node = new LeaveNode<T>(node.splitRight());
		left_node.setNode_type(Node.LEAVE_NODE);
		right_node.setNode_type(Node.LEAVE_NODE);
		//se agrega la centrala a la rama derecha
		right_node.insert(central_key);
		//se agrega la llave central a el nuevo 
		//arreglo padre
		father.insert(central_key);
		//se agregan las nuevas referencias
		if(node.getPrev() != null){
			node.getPrev().setNext(left_node);
		}
		left_node.setPrev(node.getPrev());
		//nuevo arreglo izquierdo
		left_node.setNext(right_node);
		right_node.setPrev(left_node);
		//nodo derecho con lo derecho del nodo pasado
		right_node.setNext(node.getNext());
		if(node.getNext() != null){
			node.getNext().setPrev(right_node);
		}
		//se elimina la hoja anterior
		father.removeRef(node);
		//se insertan las nuevas hojas
		father.insertReference(left_node);
		father.insertReference(right_node);
		//se setean los padres
		left_node.setFather(father);
		right_node.setFather(father);
		//se ve si el padre esa lleno
		if(father.isFull()){
			splitNode(father);
		}
	}
	
	/**
	 * Se parte los arregflos internos con referencias
	 * @param node El nodo que se va a partir
	 */
	private void splitInternalNode(InternalNode<T> node){
		//toma la llave central
		TComparable<T> central_key = node.getMidKey();
		//se hace genera un nodo nuevo si es el root
		InternalNode<T> father = node.getFather();
		if(!node.haveFather()){
			root = father = new InternalNode<T>(order);
		}
		//se agrega la llave central
		father.insert(central_key);
		//se sacan los dos hijos
		InternalNode<T> left_node = node.splitInternalLeft();
		InternalNode<T> right_node = node.splitInternalRight();
		//se elimina el pasado nodo
		father.removeRef(node);
		//se agregan los hijos
		father.insertReference(left_node);
		father.insertReference(right_node);
		//se setean los padres de los hijos
		left_node.setFather(father);
		right_node.setFather(father);
		//se colocan los tipos de nodo
		left_node.setNode_type(Node.INTERNAL_NODE);
		right_node.setNode_type(Node.INTERNAL_NODE);
		father.setNode_type(Node.INTERNAL_NODE);
		//se verifica que no este lleno el padre
		if(father.isFull()){
			splitNode(father);
		}
		
	}
	
	/**
	 * Busca el nodo donde esta o donde va a quedar 
	 * la llave que se metio
	 * @param comp llave que se va a insertar
	 * @return Nodo en el qeu se insertaria
	 */
	public Node<T> search(TComparable<T> comp){
		if(root.getNode_type() == Node.INTERNAL_NODE){
			Node<T> tmp = root;
			
			while(tmp.getNode_type() != Node.LEAVE_NODE){
				tmp = ((InternalNode<T>)(tmp)).getReference(searchKeyIndexOnReference(comp, tmp));
			}
			
			return tmp;
		}
		return null;
	}
	
	/**
	 * Busca dentro de las referencias la posicón de 
	 * donde iria una llave
	 * @param comp llave en la que se va a buscar la 
	 * posicion de donde iria
	 * @param  nodo en la que se van a buscar as referencias
	 * @return la posición de por donde iria
	 */
	private int searchKeyIndexOnReference(TComparable<T> comp, Node<T> node){
		if(node.getMaxKey().isLesser(comp)){
			return node.keysSize();
		}else{
			for (int i = 0; i < node.keysSize(); i++) {
				if(comp.isLesser(node.getKey(i))){
					return i;
				}
			}
		}
		return -1;
	}
	
	/**
	 * Imprime el arbol
	 */
	public void print() {
		if(root.getNode_type() == Node.SIMPLE_ROOT_NODE){
			root.print();
		}
		else if(root.getNode_type() == Node.INTERNAL_NODE){
			Queue<Pair<Integer,Node<T>>> queue = new LinkedList<Pair<Integer,Node<T>>>();
			
			queue.add(new Pair<Integer,Node<T>>(0,root));
			
			int current_level = 0;
			
			while(!queue.isEmpty()){
				Pair<Integer,Node<T>> pair = queue.poll();
				
				if(current_level != pair.getFirst()){
					current_level = pair.getFirst();
					System.out.println();
				}
				
				if(pair.getSecond().getNode_type() == Node.INTERNAL_NODE){
					
					InternalNode<T> tmp = (InternalNode<T>) pair.getSecond();
					tmp.print();
					System.out.print("-");
					for (int i = 0; i < tmp.getReferencesSize(); i++) {
						queue.add(new Pair<Integer,Node<T>>(current_level+1,tmp.getReference(i)));
					}
					
				}
				else if(pair.getSecond().getNode_type() == Node.LEAVE_NODE){
					pair.getSecond().print();
					System.out.print("-");
				}
				
			}
			
			
		}
	}
	
	/**
	 * Imprime la lista en las 
	 * hojas del arbol
	 */
	public void printList(){
		//busca el nodo menor
		Node<T> tmp = root;
		
		while(tmp.getNode_type() != Node.LEAVE_NODE){
			tmp = ((InternalNode<T>)(tmp)).getReference(0);
		}
		
		LeaveNode<T> tmp2 = (LeaveNode<T>) tmp;
		
		do {
			tmp2.print();
			System.out.print("->");
			tmp2 = tmp2.getNext();
		} while (tmp2.getNext() != null);
		
		tmp2.print();
	}
	
}
