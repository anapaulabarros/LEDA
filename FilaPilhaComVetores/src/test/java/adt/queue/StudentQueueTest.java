package adt.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException{
		queue1 = new QueueImpl<Integer>(6);
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);
		
		queue2 = new QueueImpl<Integer>(2);
		queue2.enqueue(1);
		queue2.enqueue(2);
		
		queue3 = new QueueImpl<Integer>(6);
	}
	
	//METODOS DE TESTE
	@Test
	public void testHead() {
		Assert.assertEquals(1, queue1.head().intValue());
		Assert.assertEquals(1, queue2.head().intValue());
		Assert.assertEquals(null, queue3.head());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(queue1.isEmpty());
		Assert.assertFalse(queue2.isEmpty());
		Assert.assertTrue(queue3.isEmpty());
	}

	@Test
	public void testIsFull() {
		Assert.assertFalse(queue1.isFull());
		Assert.assertTrue(queue2.isFull());
		Assert.assertFalse(queue3.isFull());
	}

	@Test
	public void testEnqueue() throws QueueOverflowException {
		queue1.enqueue(4);
		queue1.enqueue(5);
		Assert.assertEquals(1, queue1.head().intValue());
		queue3.enqueue(1);
		queue3.enqueue(null); // try add element null, but the queue remains unchanged 
		Assert.assertEquals(1, queue3.head().intValue());
	}
	
	@Test(expected=QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(3); // try add element in the Queue full
	}

	@Test
	public void testDequeue() throws QueueUnderflowException {
		queue1.dequeue();
		Assert.assertEquals(2, queue1.head().intValue());
		queue1.dequeue();
		Assert.assertEquals(3, queue1.head().intValue());
		queue2.dequeue();
		Assert.assertEquals(2, queue2.head().intValue());
		
	}
	
	@Test(expected=QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		queue3.dequeue();
	}
}