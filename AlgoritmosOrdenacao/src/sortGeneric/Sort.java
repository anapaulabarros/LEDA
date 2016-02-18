package sortGeneric;

public class Sort<E> {

	@SuppressWarnings("unchecked")
	public <T> E[] bubleSort(E[] a) {
		boolean swapped = true;
		int j = 0;
		while(swapped) {
			swapped = false;
			j++;
			for(int i = 0; i < a.length - j; i++){
				if(((Comparable<T>) a[i]).compareTo((T) a[i + 1]) > 0){
					E temp = a[i];
					a[i] = a[i+1];
					a[i+1] = temp;
					swapped = true;
				}
			}
		}
		return a;
	}
	
	
	public <T> E[] insertionSort(E[] A) {
		
		for(int i = 0; i < A.length; i++) {
			E key = A[i];
			int j = i - 1;
			while(j >= 0 && (((Comparable<T>) A[j]).compareTo((T) key) > 0)){
				A[j+1] = A[j];
				j--;
			}
			A[j+1] = key;
		}
		return A;
		
	}
	
	public <T> E[] selectionSort(E[] A){
		for(int i = 0; i < A.length - 1; i++){
			int min = i;			
			for(int j = i + 1; j < A.length; j++){
				if(((Comparable<T>) A[j]).compareTo((T) A[min])< 0)
					min = j;
			}
			if(min != i){
				E tmp = A[i];
				A[i] = A[min];
				A[min] = tmp;
			}
		}
		return A;
	}
	
	public void mergeSort(E[] A, int low, int high){
		
		if(low < high) {
			int med = low + (high - low) / 2;
			mergeSort(A, low, med);
			mergeSort(A, med+1, high);
			merge(A, low, med, high);
		}
	}
	
	private void merge(E[] a, int low, int med, int high) {
		 E[] vetorAux = (E[]) new Object[a.length];
		 for(int m = 0; m < a.length; m++) {
			 vetorAux[m] = a[m];
		 }
		 int i = low;
		 int j = med + 1;
		 int k = low;
		 
		 while(i <= med && j <= high) {
			 if(((Comparable<E>) vetorAux[i]).compareTo((E) vetorAux[j]) <= 0){
				 a[k] = vetorAux[i];
				 i++;
			 }else {
				 a[k] = vetorAux[j];
				 j++;
			 }
			 k++;
		 }
		 while(i<=med) {
			 a[k] = vetorAux[i];
			 i++;
			 k++;
		 }
		 while(j<=med) {
			 a[k] = vetorAux[j];
			 j++;
			 k++;
		 }	 
	}
	
	@SuppressWarnings("unchecked")
	public <T> boolean isSort(E[] A) {
		boolean sort = true;
		int n = 0;
		while (n < A.length - 1 && sort){
			if(((Comparable<T>) A[n]).compareTo((T) A[n + 1]) > 0) {
				sort = false;
			}
			n++;	
		}
		return sort;
	}
}
