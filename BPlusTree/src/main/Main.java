package main;

import datamanagment.IntComparable;
import tree.Tree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree<Integer> tree = new Tree<Integer>(3);
		
		IntComparable intc1 = new IntComparable(4);
		IntComparable intc2 = new IntComparable(8);
		IntComparable intc3 = new IntComparable(15);
		IntComparable intc4 = new IntComparable(12);
		IntComparable intc5 = new IntComparable(10);
		IntComparable intc6 = new IntComparable(11);
		IntComparable intc7 = new IntComparable(23);
		IntComparable intc8 = new IntComparable(30);
		IntComparable intc9 = new IntComparable(14);
		IntComparable intc10 = new IntComparable(35);
		IntComparable intc11 = new IntComparable(40);
		IntComparable intc12 = new IntComparable(13);
		IntComparable intc13 = new IntComparable(21);
		IntComparable intc14 = new IntComparable(18);
		IntComparable intc15 = new IntComparable(20);
		IntComparable intc16 = new IntComparable(38);
		IntComparable intc17 = new IntComparable(46);
		
		
		tree.insert(intc1);
		tree.insert(intc2);
		tree.insert(intc3);
		tree.insert(intc4);
		tree.insert(intc5);
		tree.insert(intc6);
		tree.insert(intc7);
		tree.insert(intc8);
		tree.insert(intc9);
		tree.insert(intc10);
		tree.insert(intc11);
		tree.insert(intc12);
		tree.insert(intc13);
		tree.insert(intc14);
		tree.insert(intc15);
		tree.insert(intc16);
		tree.insert(intc17);
		
		
		tree.print();
		
		System.out.println("\n");
		
		tree.printList();
		
		
	}

}
