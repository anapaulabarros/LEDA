package ordenacaoLinear;

import java.util.Arrays;

public class RadixSort {
	
	private CountingSort couting;
	
	public RadixSort(){
		couting = new CountingSort();
	}
	
	public int getMaxValue(int[] A){
		return Arrays.stream(A).max().getAsInt();
	}
	
	public void sorting(int[] arr, int sizeArray){

		//encontra o máximo valor no vetor
        int max = getMaxValue(arr);
 
        //Ordena por digito usando o CountingSort
        for (int digit = 1; max/digit > 0; digit *= 10)
            couting.sortPerDigit(arr, digit);
	}
	
}
