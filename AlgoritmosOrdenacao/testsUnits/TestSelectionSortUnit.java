import static org.junit.Assert.*;

import javax.sound.midi.Track;

import org.junit.Before;
import org.junit.Test;


public class TestSelectionSortUnit {

	private int[] vetor;
	private Sort sort;
	@Before
	public void setUp() throws Exception {
		sort = new Sort();
		vetor = new int[5];
		vetor[0] = 2;
		vetor[1] = 1;
		vetor[2] = 5;
		vetor[3] = 4;
		vetor[4] = 1;
	}

	@Test
	public void testisSortSelectionSort() {
		sort.selectionSort(vetor);
		assertTrue(sort.isSort(vetor));
	}
	
	@Test
	public void testisSortBubleSort() {
		sort.bubleSort(vetor, 0, vetor.length);
		assertTrue(sort.isSort(vetor));
	}

	@Test
	public void testIsSortInsertionSort() {
		sort.insertionSort(vetor, 1, vetor.length);
		assertTrue(sort.isSort(vetor));
	}
	
	@Test
	public void testIsSortMergeSort() {
		sort.mergeSort(vetor, 0, vetor.length-1);
		assertTrue(sort.isSort(vetor));
	}
}
