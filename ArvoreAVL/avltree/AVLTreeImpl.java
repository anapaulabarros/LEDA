package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// OVERRIDE METODOS

	// AUXILIARY CODE
	protected int getHeight(BTNode<T> node) {
		return treeHeight(node);
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (!node.isEmpty())
			return getHeight(node.getRight()) - getHeight(node.getLeft());
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
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

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
	
	@Override
	public void insert(T element) {
		insertRecursive(root, element);
	}

	private void insertRecursive(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
	        node.setData(element);
	        node.setLeft(new BSTNode<T>());
	        node.setRight(new BSTNode<T>());

	    } else {
	        if (element.compareTo(node.getData()) < 0) {
	            insertRecursive((BSTNode<T>) node.getLeft(), element);
	            node.getLeft().setParent(node);
	        } else if (element.compareTo(node.getData()) > 0) {
	            insertRecursive((BSTNode<T>) node.getRight(), element);
	            node.getRight().setParent(node);
	        }
	        rebalance(node);
	    }
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			if (!node.isEmpty()) {
				if (node == root && size() == 1) 
					root = new BSTNode<T>();
				else if (node == root) { 
					BSTNode<T> nodeAux = sucessor(node.getData());
					if (nodeAux == null)
						nodeAux = predecessor(node.getData());
					T aux = nodeAux.getData();
					nodeAux.setData(node.getData());
					node.setData(aux);
					recursiveRemove(nodeAux);
				} else
					recursiveRemove(node);
			}
		}
	}

	private void recursiveRemove(BSTNode<T> node) {
		boolean isLeft = false;
		if (node.getParent() != null) {
			isLeft = node == node.getParent().getLeft();
		}
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
			node.setRight(null);
			node.setLeft(null);
			rebalanceUp(node);
		} else if (node.getRight().isEmpty()) {
			if (node.getParent() != null) {
				if (isLeft)
					node.getParent().setLeft(node.getLeft());
				else
					node.getParent().setRight(node.getLeft());
			}
			node.getLeft().setParent(node.getParent());
			rebalanceUp(node);
		} else if (node.getLeft().isEmpty()) {
			if (node.getParent() != null) {
				if (isLeft)
					node.getParent().setLeft(node.getRight());
				else
					node.getParent().setRight(node.getRight());
			}
			node.getRight().setParent(node.getParent());
			rebalanceUp(node);
		}
		else {
			BSTNode<T> sucessor = sucessor(node.getData());

			T aux = sucessor.getData();
			sucessor.setData(node.getData());
			node.setData(aux);

			recursiveRemove(sucessor);

		}
	}


	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
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

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
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
