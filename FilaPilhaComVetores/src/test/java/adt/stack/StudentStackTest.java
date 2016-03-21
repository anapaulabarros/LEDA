package adt.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;

	@Before
	public void setUp() throws StackOverflowException{
		stack1 = new StackImpl<Integer>(6);
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		
		stack2 = new StackImpl<Integer>(2);
		stack2.push(1);
		stack2.push(2);
		
		stack3 = new StackImpl<Integer>(6);
	}
	
	//METODOS DE TESTE
	@Test
	public void testTop() {
		Assert.assertEquals(3, stack1.top().intValue());
		Assert.assertEquals(2, stack2.top().intValue());
		Assert.assertEquals(null, stack3.top());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(stack1.isEmpty());
		Assert.assertFalse(stack2.isEmpty());
		Assert.assertTrue(stack3.isEmpty());
	}

	@Test
	public void testIsFull() {
		Assert.assertFalse(stack1.isFull());
		Assert.assertTrue(stack2.isFull());
		Assert.assertFalse(stack3.isFull());
	}

	@Test
	public void testPush() throws StackOverflowException {
		stack1.push(4);
		stack3.push(1);
		stack3.push(null); //try add element null, but the stack remains unchanged 
		Assert.assertEquals(4, stack1.top().intValue());
		Assert.assertEquals(1, stack3.top().intValue());
		Assert.assertFalse(stack2.isEmpty());
		
	}
	
	@Test(expected=StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack2.push(3);
	}

	@Test
	public void testPop() throws StackUnderflowException {
		Assert.assertEquals(3, stack1.pop().intValue());
		Assert.assertEquals(2, stack2.pop().intValue());
	}
	
	@Test(expected=StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		stack3.pop();
	}
}