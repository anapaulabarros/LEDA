package adt.splaytree;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;

public class SplayTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements
		SplayTree<T> {

	private void splay(BSTNode<T> node){
		if(node.equals(root)){
			return;
		}
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> parentOfParent = (BSTNode<T>) parent.getParent();
		if (parent.equals(root)) {
			if (parent.getLeft().equals(node)) {
				super.rightRotation(parent);
			} else {
				super.leftRotation(parent);
			}
		} else if (parentOfParent.getLeft().equals(parent) && parent.getLeft().equals(node)) {
			super.rightRotation(parentOfParent);
			super.rightRotation(parent);
		} else if (parentOfParent.getRight().equals(parent) && parent.getRight().equals(node)) {
			super.leftRotation(parentOfParent);
			super.leftRotation(parent);
		} else if(parentOfParent.getLeft().equals(parent) && parent.getRight().equals(node)){
			super.leftRotation(parent);
			super.rightRotation(parentOfParent);
		} else if(parentOfParent.getRight().equals(parent) && parent.getLeft().equals(node)){
			super.rightRotation(parent);
			super.leftRotation(parentOfParent);
		}
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return searchSplay(element);
	}
	
	public BSTNode<T> searchSplay(T element) {
		BSTNode<T> nodeResult = treeSearch(root,element);
		
		if(!nodeResult.isEmpty())
			splay(nodeResult);
		else
			splay((BSTNode<T>) nodeResult.getParent());
		
		return nodeResult;
	}
	
	@Override
	public void insert(T element) {
		super.insert(element);
		search(element);
	}
	
	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> aux = super.search(element);
			if (aux.isEmpty()) {
				splay((BSTNode<T>) aux.getParent());
			} else {
				delete(element);
			}
		}
	}

	private void delete(T element) {
		BSTNode<T> node = super.search(element);
		if (!node.isEmpty()) {
			if (node.equals(root) && size() == 1) {
				root = new BSTNode<T>();
			} else if (node.equals(root)) {
				BSTNode<T> auxNode = sucessor(node.getData());
				if (auxNode == null) {
					auxNode = predecessor(node.getData());
				}
				T aux = auxNode.getData();
				auxNode.setData(node.getData());
				node.setData(aux);
				recursiveRemove(auxNode);
			} else {
				recursiveRemove(node);
			}
		}
	}

	private void recursiveRemove(BSTNode<T> node) {
		boolean flag = false;
		if (node.getParent() != null) {
			flag = node == node.getParent().getLeft();
		}
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
			node.setRight(null);
			node.setLeft(null);
			splay((BSTNode<T>) node.getParent());
		} else if (node.getRight().isEmpty()) {
			if (node.getParent() != null) {
				if (flag) {
					node.getParent().setLeft(node.getLeft());
				} else {
					node.getParent().setRight(node.getLeft());
				}
			}
			node.getLeft().setParent(node.getParent());
			splay((BSTNode<T>) node.getParent());
		} else if (node.getLeft().isEmpty()) {
			if (node.getParent() != null) {
				if (flag) {
					node.getParent().setLeft(node.getRight());
				} else {
					node.getParent().setRight(node.getRight());
				}
			}
			node.getRight().setParent(node.getParent());
			splay((BSTNode<T>) node.getParent());
		} else {
			BSTNode<T> sucessor = sucessor(node.getData());
			T aux = sucessor.getData();
			sucessor.setData(node.getData());
			node.setData(aux);
			recursiveRemove(sucessor);
		}
	}
	
}
