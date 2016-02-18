
public class Sort {

	
	/**
	 * Metodo de ordenacao Selection Sort por partes
	 * @param 
	 * @param A[]
	 * @return A[] ordenado
	 */
	public int[] selectionSort(int[] A){
		
		
		for(int i = 0; i < A.length - 1; i++){
			int min = i;			
			for(int j = i + 1; j < A.length; j++){
				if(A[j]> A[min])
					min = j;
			}
			if(min != i){
				swap(A, i, min);
			}
		}
		return A;
	}

	/**
	 * Metodo de ordenacao Buble Sort por partes
	 * @param A[]
	 * @return A[] ordenado
	 */
	public int[] bubleSort(int[] A, int iLeft, int iRight) {
		boolean swapped = true;
		int j = 0;
		while(swapped) {
			swapped = false;
			j++;
			for(int i = iLeft; i < iRight - j; i++){
				if(A[i] > A[i + 1]){
					int temp = A[i];
					A[i] = A[i+1];
					A[i+1] = temp;
					swapped = true;
				}
			}
		}
		return A;
	}
	
	
	/**
	 * Metodo para ordernar um vetor usando Insertion Sort
	 * @param A[]
	 * @return A[] ordenado
	 */
	public int[] insertionSort(int[] A, int iLeft, int iRight) {
		
		for(int i = iLeft; i < iRight; i++) {
			int key = A[i];
			int j = i - 1;
			while(j >= 0 && A[j] > key){
				A[j+1] = A[j];
				j--;
			}
			A[j+1] = key;
		}
		return A;
		
	}
	
	
	/**
	 * Metodo auxiliar para realizar as trocas dentro de vetor
	 * @param A[]
	 * @param int i
	 * @param int min
	 * @return void
	 */
	private void swap(int[] a, int i, int min) {
		int tmp = a[i];
		a[i] = a[min];
		a[min] = tmp;
	}
	
	/**
	 * Metodo para verificar se o vetor esta ordenado
	 * @param A[]
	 * @return boolean
	 */
	public boolean isSort(int[] A) {
		boolean sort = true;
		int n = 0;
		while (n < A.length - 1 && sort){
			if(A[n] > A[n+1]) {
				sort = false;
			}
			n++;	
		}
		return sort;
	}

	
}
