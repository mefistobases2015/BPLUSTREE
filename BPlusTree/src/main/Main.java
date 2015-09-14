package main;

import datamanagment.IntComparable;
import tree.Tree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree<Integer> tree = new Tree<Integer>(3);
		
		IntComparable intc1 = new IntComparable(5);
		IntComparable intc2 = new IntComparable(2);
		IntComparable intc3 = new IntComparable(1);
		IntComparable intc4 = new IntComparable(6);
		
		tree.insert(intc1);
		tree.insert(intc2);
		tree.insert(intc3);
		tree.insert(intc4);
		
		tree.print();
		
	}
	
	public static void pointersTrial(IntComparable intc,IntComparable intc2){
		intc = intc2;
	}

}
