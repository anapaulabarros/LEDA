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
}
