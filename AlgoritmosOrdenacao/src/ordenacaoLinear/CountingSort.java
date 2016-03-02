package ordenacaoLinear;

import java.util.Arrays;

public class CountingSort {

public CountingSort(){}

	/**
	 * Metodo couting Sort tradicional
	 * @param int[] Array
	 * @param int max - maior valor encontrado dentro do array
	 * @return int[] array auxiliar ordenado
	 * */
	public int[] sort(int[] A, int max){
		int[] vetorContador = new int[max]; //inicia o vetor auxiliar
		int[] vetorAux = new int[A.length]; //array final com valores ordenados
		
		/*Contagem de elemetos*/
		for(int i = 0; i < A.length; i++) {
			vetorContador[A[i]-1]++;
		}
		/*Soma acumulativa*/
		for(int i = 1; i < vetorContador.length; i++) {
			vetorContador[i] = vetorContador[i-1] + vetorContador[i];
		}
		
		/*ordena os valores com base no vetor auxiliar e o vetor original*/
		for(int i = A.length - 1; i >= 0; i--){
			vetorAux[vetorContador[A[i] - 1] - 1] = A[i];
			vetorContador[A[i] - 1]--;
		}
		
		return vetorAux;
		
	}
	
	/**
	 * Metodo couting Sort por dígito
	 * @param int[] Array
	 * @param int max - maior valor encontrado dentro do array
	 * @return void 
	 * */
	public void sortPerDigit(int arr[], int exp)
    {
		int output[] = new int[arr.length]; // array aux
        int count[] = new int[10]; // array com os digitos
 
        //armazenamento de contador de ocorrencia em count[] 
        for (int i = 0; i < arr.length; i++)
            count[ (arr[i]/exp)%10 ]++;
 
        // contagem dos digitos
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];
 
        // Build the output array
        for (int i = arr.length - 1; i >= 0; i--)
        {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }
 
        // Copia os valores do array output para arr[], assim o array original
        // conterá os seus valores ordenados
        for (int i = 0; i < arr.length; i++)
            arr[i] = output[i];
    }
	
	
	
	/**
	 * Metodo para ordenar usando o algoritmo Counting Sort 
	 * @param array
	 * @param leftIndex
	 * @param rightIndex]
	 * @return void
	 */
	public void sort(Integer[] array,int leftIndex, int rightIndex) {
		if(array.length <= 0)
			return;
		
		int maxValueOfArray = getMaxValue(array); // obtem o maior elemento dentro do array
	
		Integer[] vetorContador = new Integer[maxValueOfArray]; //inicia o vetor auxiliar
		Integer[] vetorAux = new Integer[array.length]; //array final com valores ordenados		
		Arrays.fill(vetorContador, 0); //preenche os elementos do vetor com 0

		/*Contagem de elemetos*/
		for(int i = 0; i < array.length; i++) {
			if(array[i]-1 < 0)
				vetorContador[0]++;
			else
				vetorContador[array[i]-1]++;
		}
		
		/*Soma acumulativa*/
		for(int i = 1; i < vetorContador.length; i++) {
			vetorContador[i] = vetorContador[i-1] + vetorContador[i];
		}
		/*ordena os valores com base no vetor auxiliar e o vetor original*/
		for(int i = array.length - 1; i >= 0; i--){
			if(array[i]-1  >= 0){
				vetorAux[vetorContador[array[i] - 1] - 1] = array[i];
				vetorContador[array[i] - 1]--;
			}else{
				vetorAux[vetorContador[array[i] - 1 +1 ] - 1] = array[i];
				vetorContador[0]--;
			}
		}
		/*copia os valores do vetor aux para o vetor original*/
		for(int i = 0; i < array.length; i++) {
			array[i] = vetorAux[i];
		}
	}
	
	
	/**
	 * Metodo para obter o maior valor dentro do array
	 * @param array
	 * @return int
	 */
	private int getMaxValue(Integer[] array){
		int min = array[0];
		for (Integer elements : array) {
			if(elements > min)
				min = elements;
		}
		return min;
	}
}
