package adt.queue;

public class CircularQueue<T> implements Queue<T> {
	
	private T[] array;
	private int tail;
	private int head;
	private int elements;
	
	public CircularQueue(int size){
		array = (T[])new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(element == null)
			return;
		if(this.isFull())
			throw new QueueOverflowException();
		if(this.isEmpty()){
			this.tail++;
			this.head++;
			this.elements++;
			this.array[head] = element;
		}else {
			this.tail = (tail+1) % this.array.length;
			this.elements++;
			this.array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		Object out;
		
		if(this.isEmpty())
			throw new QueueUnderflowException();
		if(this.head == this.tail){
			out = this.array[head];
			this.head--;
			this.tail--;
			this.elements--;
			return (T) out;
		}else{
			out = this.array[head];
			this.head = (head+1) % array.length;
			this.elements--;
			return (T) out;
		}
	}

	@Override
	public T head() {
		return this.isEmpty() ? null : this.array[head];
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return ((this.tail+1) % array.length) == this.head;
	}

}
