
import org.junit.Before;
import org.junit.Test;

import quickAndMergeSort.QuickSort;
import quickAndMergeSort.Sorting;


public class TestQuickSort {

	private Integer[] vetor;
	private Sorting<Integer> implementation;
	
	@Before
	public void setUp() throws Exception {
		vetor = new Integer[5];
		this.implementation = new QuickSort<Integer>();
		
		vetor[0] = 5;
		vetor[1] = 1;
		vetor[2] = 4;
		vetor[3] = 1;
		vetor[4] = 2;
	}

	@Test
	public void testOrdenaVetor() {
		this.implementation.sort(vetor, 0, vetor.length - 1);

	}

}
