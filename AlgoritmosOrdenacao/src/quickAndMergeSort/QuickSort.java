package quickAndMergeSort;

public class QuickSort<T extends Comparable<T>> implements Sorting<T> {

	/**
	 * Metodo para rearanjar um vetor em ordem crescente
	 * @param T[] array
	 * @param int left
	 * @param int right
	 * @return void
	 */
	public void sort(T[] array, int leftIndex, int rightIndex){
		
		int index = separe(array, leftIndex, rightIndex);
		if(leftIndex < index - 1) {
			sort(array, leftIndex, index - 1);
		}
		if(rightIndex > index){
			sort(array, index, rightIndex);
		}
	};
	
	/**
	 * Metodo para separar o vetor em problemas menores
	 * @param array
	 * @param left
	 * @param right
	 * @return int
	 */
	public int separe(T[] array, int left, int right) {
		  int i = left, j = right;
	      Object tmp;
	      Object pivot = array[(left + right) / 2];
	      while (i <= j) {
	            while (array[i].compareTo((T) pivot) < 0)
	                  i++;
	            while (array[j].compareTo((T) pivot) > 0)
	                  j--;
	            if (i <= j) {
	                  tmp = array[i];
	                  array[i] = array[j];
	                  array[j] = (T) tmp;
	                  i++;
	                  j--;
	            }
	      };
	     
	      return i;
	}
}
