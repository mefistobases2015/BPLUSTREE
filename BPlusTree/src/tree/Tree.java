package tree;

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
		
		if(root.getNode_type() == Node.SIMPLE_ROOT_NODE){
			root.insert(comp);
			if(root.isFull()){
				root = splitNode(root);
				root.setNode_type(Node.COMPLEX_ROOT_NODE);
			}
		}
		else if(root.getNode_type() == Node.COMPLEX_ROOT_NODE){
			
		}
	}
	
	/**
	 * Parte un noco cuando se llena
	 * y genera las referencias 
	 * @param node Nodo al que se 
	 * le va a hacer el split
	 */
	private Node<T> splitNode(Node<T> node){
		System.out.println("entro");
		Node<T> leftLNode = new Node<T>(node.splitLeft());
		
		Node<T> rightLNode = new Node<T>(node.splitRight());
		
		InternalNode<T> tmpFather = new InternalNode<T>(order);
		tmpFather.insert(node.getMidKey());
		tmpFather.setFather(node.getFather());
		
		tmpFather.insertReference(leftLNode);
		tmpFather.insertReference(rightLNode);
		
		return tmpFather;
	}
	
	
	public void print() {
		root.print();
		
		((InternalNode<T>)(root)).getReference(0).print();
		((InternalNode<T>)(root)).getReference(1).print();
	}
}
