
//class file tree
//wren lee

import java.util.ArrayList;

public class Tree<T> {
	// data
	ArrayList rootSeq = new ArrayList<T>();
	Node root = new Node(rootSeq);
	int orderLength;
	ArrayList in;
	float pMin;
	int totalInputTokens = 0;
	
	public Tree(ArrayList<T> initStr, float pM) {
		// sets token sequence to empty string
		root.setTokenSeq(initStr);
		pMin = pM;
	}// overloaded constructor

	public void train(ArrayList input, int orderLen) {
		orderLength = orderLen;
		in = input;
		for (int i = 1; i < orderLength; i++) { // order number
			for (int j = i - 1; j < input.size(); j++) { // input
				ArrayList<T> currentSeq = new ArrayList<T>(input.subList(j - (i - 1), j + 1)); // makes current sequence
				Node newNode = new Node(currentSeq);// make a new node and set it equal to the current sequence
				root.addNode(newNode);
			}
		}
		totalInputTokens += input.size(); //adding to the total tokens
		root.pMinElimination(totalInputTokens, pMin);
	}// train

	public void print() {
		int oL = orderLength - 1;
		System.out.println("Order Length " + oL + " for " + in);
		root.print();
		System.out.println("---------------");
	}// print

}
