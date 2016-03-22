package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
		
	
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[])new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		return this.isEmpty() ? null : array[0]; 
		
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}
	
	private void shiftLeft(){
		for(int i = 0; i <= (this.tail - 1); i++){
			array[i] = array[i+1];
		}
		this.tail--;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(element == null)
			return;
		if(this.isFull())
			throw new QueueOverflowException();
		tail++;
		this.array[tail] = element;

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty())
			throw new QueueUnderflowException();
		Object out = array[0];
		this.shiftLeft();
		return (T) out;
		
	}


}
