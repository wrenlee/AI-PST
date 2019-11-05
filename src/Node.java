
//class file node
//wren lee

import java.util.ArrayList;

public class Node<T> {
	ArrayList<T> tokenSequence = new ArrayList<T>();
	ArrayList<Node> children = new ArrayList<Node>();
	int counts = 1; // number of times the node appear

	Node(ArrayList tokenSeq) {
		tokenSequence = tokenSeq;
	}// constructor

	// setters and getters
	public ArrayList<T> getTokenSeq() {
		return tokenSequence;
	}

	public void setTokenSeq(ArrayList<T> inSeq) {
		tokenSequence = inSeq;
	}

	public int getTokenSeqSize() {
		return tokenSequence.size();
	}

	// methods
	public boolean isASuffix(Node node) {
		// checks suffix
		ArrayList<T> nodeSeq = new ArrayList<T>();
		nodeSeq = node.getTokenSeq(); // adds param node to node seq to get sublist

		int from = node.getTokenSeqSize() - tokenSequence.size();
		int to = node.getTokenSeqSize();
		ArrayList<T> suffix = new ArrayList<T>(nodeSeq.subList(from, to)); // creates the suffix

		if (tokenSequence.equals(suffix)) {// checks the content of suffix, == checks reference, .equals contents
			return true;
		} else {
			return false;
		}
	}// is a suffix

	public boolean addNode(Node node) {
		boolean found = false;
		if (tokenSequence.equals(node.getTokenSeq())) { // if it's the same sequence
			found = true;
			counts++;
		} else if (isASuffix(node) || (tokenSequence.size() == 0)) { // if it's not the same sequence
			int index = 0;
			while (index < children.size() && !found) {
				found = children.get(index).addNode(node);
				index++;
			}
			if (found == false) {
				children.add(node); // add node to children
				found = true; // finally change found to true
			}
		}
		return found;
	} // try to add to all child nodes

	public boolean pMinElimination(int totalTokens, float pMin) {
		float empProb = counts / ((float) totalTokens - (tokenSequence.size() - 1)); // empirical probability
		boolean shouldDelete = empProb < pMin; // if emp prob is less than pmin then should delete it
		
		if (tokenSequence.size() == 0) {// accounting for empty sequence
			shouldDelete = false;
		}

		if (shouldDelete == false) {// don't remove
			for (int i = children.size() - 1; i >= 0; i--) {// loop through children going from back to front
				if (children.get(i).pMinElimination(totalTokens, pMin) == true) {// if should remove node
					children.remove(children.get(i));// remove that node
				}
			}
		}
		return shouldDelete;
	}// pmin elim

	public void print() {
		// print w/o param
		System.out.println(tokenSequence);

		for (int i = 0; i < children.size(); i++) {
			children.get(i).print(1);
		}
	}// print w/o param

	public void print(int numSpaceBefore) {
		// print w/ param for legibility
		for (int i = 0; i < numSpaceBefore; i++) {
			System.out.print("  "); // spacing
		}
		System.out.print("->");
		System.out.println(tokenSequence); // prints actual token
		for (int i = 0; i < children.size(); i++) {
			children.get(i).print(numSpaceBefore + 1); // prints children
		}
	}// print w/ param
}
