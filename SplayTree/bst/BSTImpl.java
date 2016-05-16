package adt.bst;

import java.util.ArrayList;
import java.util.List;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected  BSTNode<T> root;
		
	public BSTImpl() {
		root = new BSTNode<T>();
	}
	
	public BSTNode<T> getRoot(){
		return this.root;
	}
	
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}
	
	@Override
	public int height() {
		return treeHeight(this.getRoot());
	}
	
	protected int treeHeight(BTNode<T> node) {
		if (node.isEmpty())
			return -1;
		else
			return 1 + Math.max(treeHeight(node.getLeft()), treeHeight(node.getRight()));
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return treeSearch(root, element);
	}
	
	protected BSTNode<T> treeSearch(BSTNode<T> node, T key) {
		if(node.isEmpty() || key.equals(node.getData()))
			return node;
		else{
			if(key.compareTo(node.getData()) < 0)
				return treeSearch((BSTNode<T>) node.getLeft(), key);
			else
				return treeSearch((BSTNode<T>) node.getRight(), key);
		}
	}
	
	@Override
	public void insert(T element) {
		if (this.isEmpty()) {
			root.setData(element);
			root.setLeft(new BSTNode<T>());
			root.setRight(new BSTNode<T>());
			root.setParent(new BSTNode<T>());
		}
		else
			insertElement(root, element, root);
			
	}
	
	private void insertElement(BSTNode<T> node, T element, BSTNode<T> parent) {
		if(node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
		}else{
			if (element.compareTo(node.getData()) < 0) {
				insertElement((BSTNode<T>) node.getLeft(), element, node);
			} else if (element.compareTo(node.getData()) > 0) {
				insertElement((BSTNode<T>) node.getRight(), element, node);
			}
		}
	}
	
	@Override
	public BSTNode<T> maximum() {
		return maximumTree(root);
	}
	
	private BSTNode<T> maximumTree(BSTNode<T> node) {
		if(node.isEmpty())
			return null;
		if(node.getRight().isEmpty())
			return node;
		else
			return maximumTree((BSTNode<T>) node.getRight());
	}
	
	@Override
	public BSTNode<T> minimum() {
		return minimumTree(root);
	}
	
	private BSTNode<T> minimumTree(BSTNode<T> node) {
		
		if(node.isEmpty())
			return null;
		else{
			BSTNode<T> max = node;
			if(node.getLeft().isEmpty())
				return max;
			else
				return minimumTree((BSTNode<T>)node.getLeft());
		}
	}
	
	@Override
	public BSTNode<T> sucessor(T element) {
	
		if (element != null) {
			BSTNode<T> node = search(element);
	
			if (!node.isEmpty()) {
	
				if (!node.getRight().isEmpty())
					return minimumTree((BSTNode<T>) node.getRight());
	
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
	
				while (parentNode != null && node == parentNode.getRight()) {
					node = parentNode;
					parentNode = (BSTNode<T>) parentNode.getParent();
				}
	
				return parentNode;
			}
	
		}
		return null;
	}
	
	@Override
	public BSTNode<T> predecessor(T element) {
	
		if (element != null) {
	
			BSTNode<T> node = search(element);
	
			if (!node.isEmpty()) {
	
				if (!node.getLeft().isEmpty())
					return maximumTree((BSTNode<T>) node.getLeft());
	
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
	
				while (parentNode != null && node == parentNode.getLeft()) {
					node = parentNode;
					parentNode = (BSTNode<T>) parentNode.getParent();
				}
	
				return parentNode;
	
			}
		}
	
		return null;
	}
	
	@Override
	public void remove(T element) {
		root = remove(element, root);
	}
	
	protected BSTNode<T> remove(T element, BSTNode<T> node) {
		if(node.isEmpty())
			return node; //no nao encontrado, nao faz nada
		if(element.compareTo(node.getData()) < 0){
			node.setLeft(remove(element, (BSTNode<T>) node.getLeft()));
		}else if(element.compareTo(node.getData()) > 0){
			node.setRight(remove(element, (BSTNode<T>) node.getRight()));
		}else if(!node.getLeft().isEmpty() && !node.getRight().isEmpty()){ // no tem dois filhos
			BSTNode<T> newNode =  minimumTree((BSTNode<T>) node.getRight());
			node.setData(newNode.getData());
			node.setRight(remove(node.getData(), (BSTNode<T>) node.getRight()));
		}else
			node = (BSTNode<T>) ((!node.getLeft().isEmpty()) ? node.getLeft() : node.getRight());
		return node; //retorna o novo root		
	}
	
	@Override
	public T[] preOrder() {
		List<T> list = new ArrayList<T>();
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(list , this.root);
		return (T[]) list.toArray(array);
	}
	
	private void preOrder(List<T> list ,BSTNode<T> node) {
		if(!node.isEmpty()){
			list.add(node.getData());
			preOrder(list, (BSTNode<T>) node.getLeft());
			preOrder(list, (BSTNode<T>) node.getRight());
		}
	}
	
	@Override
	public T[] order() {
		List<T> list1 = new ArrayList<T>();
		T[] array = (T[]) new Comparable[this.size()];
		InOrder(list1 , this.root);
		return (T[]) list1.toArray(array);
	}
	
	private void InOrder(List<T> list1, BSTNode<T> node) {
		if(!node.isEmpty()){
			InOrder(list1, (BSTNode<T>) node.getLeft());
			list1.add(node.getData());
			InOrder(list1, (BSTNode<T>) node.getRight());
		}
	}
	
	@Override
	public T[] postOrder() {
		List<T> list2 = new ArrayList<T>();
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(list2, root);
		return (T[]) list2.toArray(array);
	}
	
	private void postOrder(List<T> list2, BSTNode<T> node) {
		if(!node.isEmpty()){
			postOrder(list2, (BSTNode<T>) node.getLeft());
			postOrder(list2, (BSTNode<T>) node.getRight());
			list2.add(node.getData());
		}
	}
	
	/**
	 * This method is already implemented using recursion. You must understand how it work and 
	 * use similar idea with the other methods. 
	 */
	@Override
	public int size() {
		return size(root);
	}
	private int size(BSTNode<T> node){
		int result = 0;
		//base case means doing nothing (return 0)
		if(!node.isEmpty()){ //indusctive case
			result = 1 + size((BSTNode<T>)node.getLeft()) + size((BSTNode<T>)node.getRight());
		}
		return result;
	}

}
