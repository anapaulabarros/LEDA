package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class AVLTreeImpl<T extends Comparable<T>> 
    extends BSTImpl<T> implements AVLTree<T> {

	//TODO Do not forget: you must override the methods insert and remove conveniently.

	//AUXILIARY
	
	protected int getHeight(BTNode<T> node) {
		return treeHeight(node);
	}
	
	protected int calculateBalance(BSTNode<T> node){
		if (!node.isEmpty())
			return getHeight(node.getRight()) - getHeight(node.getLeft());
		return 0;
	}
	
	//AUXILIARY
	protected void rebalance(BSTNode<T> node){
		int balance = calculateBalance(node);
		if (balance > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation(node);
			} else {
				leftRotation(node);
			}
		} else if (balance < -1) {
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
			} else {
				rightRotation(node);
			}
		}
	}
	
	//AUXILIARY
	protected void rebalanceUp(BSTNode<T> node){
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
	
	//AUXILIARY
	protected void leftRotation(BSTNode<T> node){
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();

		node.setRight(pivot.getLeft());
		pivot.getLeft().setParent(node);
		pivot.setLeft(node);
		pivot.setParent(node.getParent());
		node.setParent(pivot);

		if (node.equals(root)) {
			root = pivot;
		} else {
			if (pivot.getData().compareTo(pivot.getParent().getData()) < 0)
				pivot.getParent().setLeft(pivot);
			else
				pivot.getParent().setRight(pivot);
		}
	}
	
	//AUXILIARY
	protected void rightRotation(BSTNode<T> node){
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

		node.setLeft(pivot.getRight());
		pivot.getRight().setParent(node);
		pivot.setRight(node);
		pivot.setParent(node.getParent());
		node.setParent(pivot);

		if (node.equals(root)) {
			root = pivot;
		} else {
			if (pivot.getData().compareTo(pivot.getParent().getData()) > 0)
				pivot.getParent().setRight(pivot);
			else
				pivot.getParent().setLeft(pivot);
		}
	}
	
}
