package adt.rbtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T> 
	implements RBTree<T> {

	private RBNode<T> NIL; 
	
	public RBTreeImpl() {
		this.root = new RBNode<T>();
		NIL = new RBNode<T>();
	}
	
	protected int blackHeight(){
		return alturaPreta(root);
	}

	private int alturaPreta(BSTNode<T> node) {
		if (!node.isEmpty())
			if (((RBNode<T>) node.getLeft()).getColour() == Colour.BLACK
					|| ((RBNode<T>) node.getRight()).getColour() == Colour.BLACK)
				return alturaPreta((RBNode<T>) node.getLeft()) + 1;
			else
				return 1;
		return 0;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		if (isEmpty()) {
			return true;
		} else {
			return verifyChildrenOfRedNodes((RBNode<T>) root);
		}
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (node.isEmpty()) {
			return true;
		} else if (node.getColour() == Colour.RED) {
			if (((RBNode<T>) node.getLeft()).getColour() == Colour.RED
					|| ((RBNode<T>) node.getRight()).getColour() == Colour.RED) {
				return false;
			}
		}
		return verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
				&& verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		return true;
	}

	
	@Override
	public void insert(T value) {
		if (!search(value).equals(NIL))
			return;
		RBNode<T> node = new RBNode<T>();
		node.setData(value);
		node.setLeft(NIL);
		node.setRight(NIL);
		node.setColour(Colour.RED);
		insertElement((RBNode<T>) root, node);
		if (!verifyProperties())
			fixUpCase1((RBNode<T>) search(value));
	}
	
	private void insertElement(RBNode<T> node, RBNode<T> newNode) {
		RBNode<T> no = node;
		RBNode<T> nodeAux = new RBNode<T>();
		if (search(newNode.getData()).equals(NIL)) {
			while (!no.isEmpty()) {
				nodeAux = no;
				if (newNode.getData().compareTo(no.getData()) > 0)
					no = (RBNode<T>) no.getRight();
				else
					no = (RBNode<T>) no.getLeft();
			}
		}
		newNode.setParent(nodeAux);
		if (nodeAux.isEmpty()) {
			root = newNode;
		} else {
			if (newNode.getData().compareTo(nodeAux.getData()) > 0) {
				nodeAux.setRight(newNode);
			} else {
				nodeAux.setLeft(newNode);
			}
		}
	}
	
	@Override
	public RBNode<T>[] extendedPreOrder() {
		List<RBNode<T>> aux = new ArrayList<RBNode<T>>();
		recursivepreOrder((RBNode<T>) this.root, aux);
		RBNode<T>[] array = new RBNode[aux.size()];
		for (int i = 0; i < aux.size(); i++) {
			array[i] = aux.get(i);
		}
		return (RBNode<T>[]) array;
	}

	private void recursivepreOrder(RBNode<T> node, List<RBNode<T>> array) {
		if (!node.isEmpty()) {
			array.add((RBNode<T>) node);
			recursivepreOrder((RBNode<T>) node.getLeft(), array);
			recursivepreOrder((RBNode<T>) node.getRight(), array);
		}
	}
	
	//FIXUP methods
	protected void fixUpCase1(RBNode<T> node){
		if (node.equals(root)) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}
	protected void fixUpCase2(RBNode<T> node){
		if (((RBNode<T>) node.getParent()).getColour() == Colour.BLACK) {
			return;
		} else {
			fixUpCase3(node);
		}
	}
	
	private RBNode<T> getUncleNode(RBNode<T> node) {
		if(node.getParent().equals(node.getParent().getParent().getLeft())){ 
			return (RBNode<T>) node.getParent().getParent().getRight();
	} else { 
		return (RBNode<T>) node.getParent().getParent().getLeft();
	}
	}
	protected void fixUpCase3(RBNode<T> node){
		if (getUncleNode(node).getColour() == Colour.RED) {
			((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
			getUncleNode(node).setColour(Colour.BLACK);
			((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
			fixUpCase1((RBNode<T>) node.getParent().getParent());
		} else {
			fixUpCase4(node);
		}
	}
	protected void fixUpCase4(RBNode<T> node){
		RBNode<T> nodeAux = node;
		if (node.getParent().getRight().equals(node)
				&& node.getParent().getParent().getLeft()
						.equals(node.getParent())) {
			leftRotation((RBNode<T>) node.getParent());
			nodeAux = (RBNode<T>) node.getLeft();
		} else if (node.getParent().getLeft().equals(node)
				&& node.getParent().getParent().getRight()
						.equals(node.getParent())) {
			rightRotation((RBNode<T>) node.getParent());
			nodeAux = (RBNode<T>) node.getRight();
		}

		fixUpCase5(nodeAux);
	}
	protected void fixUpCase5(RBNode<T> node){
		((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
		((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
		if (node.getParent().getLeft().equals(node)) { 
			rightRotation((BSTNode<T>) node.getParent().getParent());
		} else { 
			leftRotation((BSTNode<T>) node.getParent().getParent());
		}
	}
}
