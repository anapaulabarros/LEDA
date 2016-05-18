package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;
	
	protected int height;
	protected int maxHeight;

	//SET THIS VALUE TO TRUE IF YOU ARE IMPLEMENTING MAX_LEVEL = LEVEL
	//SET THIS VALUE TO FALSE IF YOU ARE IMPLEMENTING MAX_LEVEL != LEVEL
	protected boolean USE_MAX_HEIGHT_AS_HEIGHT = true;
	protected double PROBABILITY = 0.5; 
	
	
	public SkipListImpl(int maxHeight) {
		if(USE_MAX_HEIGHT_AS_HEIGHT){
			this.height = maxHeight;
		}else{
			this.height = 1;
		}
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}
	
	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL
	 * Caso esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com 
	 * level=1 e o metodo deve conectar apenas o forward[0].  
	 */
	private void connectRootToNil(){
	  for(int i = maxHeight - 1; i >=0 ; i--)
		  root.forward[i] = NIL;
	}
	
	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no metodo
	 * insert(int,V) 
	 */
	private int randomLevel(){
		int randomLevel = 1;
		double random = Math.random();
		while(Math.random() <= PROBABILITY && randomLevel < maxHeight){
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}
	
	@Override
	public void insert(int key, T newValue) {
		if (newValue == null) {
			return;
		}
		
		SkipListNode<T>[] update = montaArray(maxHeight);
		SkipListNode<T> auxRoot = root;
		
		auxRoot = searchElement(key, update, auxRoot);
		
		if (auxRoot.getKey() == key) {
			auxRoot.setValue(newValue);
		} else {
			int nivel = randomLevel();
			
			if (nivel > maxHeight) {
				for (int i = maxHeight; i < nivel; i++) {
					update[i] = root;
				}
				maxHeight = nivel;
			}
			
			SkipListNode<T> newNode = new SkipListNode<T>(key, nivel, newValue);
			
			for (int i = 0; i < nivel; i++) {
				newNode.forward[i] = update[i].getForward(i);
				update[i].forward[i] = newNode;
			}
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		if (newValue == null || height < 0) {
			return;
		}
		SkipListNode<T>[] update = montaArray(maxHeight);
		SkipListNode<T> auxRoot = root;
		
		auxRoot = searchElement(key, update, auxRoot);
		
		if (auxRoot.getKey() == key) {
			auxRoot.setValue(newValue);
		} else {
			int nivel = height;
			
			if (nivel > maxHeight) {
				for (int i = maxHeight; i < nivel; i++) {
					update[i] = root;
				}
				maxHeight = nivel;
			}
			
			SkipListNode<T> newNode = new SkipListNode<T>(key, nivel, newValue);
			
			for (int i = 0; i < nivel; i++) {
				newNode.forward[i] = update[i].getForward(i);
				update[i].forward[i] = newNode;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = montaArray(maxHeight);
		SkipListNode<T> auxRoot = root;
		
		auxRoot = searchElement(key, update, auxRoot);
		
		if (auxRoot.getKey() == key) {
			for (int i = 0; i < maxHeight; i++) {
				if (update[i].getForward(i) != auxRoot) {
					break;
				}
				update[i].forward[i] = auxRoot.getForward(i);
			}
		} else {
			while (maxHeight > 1 && root.forward[maxHeight - 1].equals(NIL)) {
				maxHeight--;
			}
		}
	}

	@Override
	public int height() {
		return maxHeight;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> auxRoot = root;
		
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (auxRoot.getForward(i).getKey() <= key) {
				auxRoot = auxRoot.getForward(i);
			}
		}
		
		if (auxRoot.getKey() == key) {
			return auxRoot;
		} else {
			return null;
		}
	}

	@Override
	public int size(){
		SkipListNode<T> aux = root.forward[0];
		int size = 0;
		
		while (aux.value != null) {
			size++;
			aux = aux.forward[0];
		}
		
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T> auxRoot = root;
		SkipListNode<T>[] array = montaArray(this.size() + 2);
		
		int i = 0;
		while (auxRoot != null) {
			array[i++] = auxRoot;
			auxRoot = auxRoot.forward[0];
		}
		
		return array;
	}

	public SkipListNode<T>[] montaArray(int size) {
		@SuppressWarnings("unchecked")
		SkipListNode<T>[] array = (SkipListNode<T>[]) new SkipListNode[size];
		return array;
	}
	
	private SkipListNode<T> searchElement(int key, SkipListNode<T>[] update, SkipListNode<T> auxRoot) {
		for (int i = maxHeight - 1; i >= 0; i--) {
			while (auxRoot.getForward(i).getKey() < key) {
				auxRoot = auxRoot.getForward(i);
			}
			update[i] = auxRoot;
		}
		
		auxRoot = auxRoot.forward[0];
		return auxRoot;
	}
	
	/*Imprimir os nos por nivel*/
	public void printNodesByHeight(){
		int level = this.maxHeight;
        ArrayList<SkipListNode<T>> listaOrdenada = new ArrayList<SkipListNode<T>>(); 
        SkipListNode<T>[] array = this.toArray();
       
        while(listaOrdenada.size() < size()){
        	for(SkipListNode<T> no : array){
        		if(no.getHeight() == level && no != NIL){
        			listaOrdenada.add(no);
                }
            }
            level--;
        }
        System.out.println(Arrays.toString(listaOrdenada.toArray(new SkipListNode[0])));
		
	}
}
