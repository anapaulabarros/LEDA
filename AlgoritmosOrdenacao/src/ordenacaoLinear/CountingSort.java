package ordenacaoLinear;

public class CountingSort {

	public CountingSort(){}
	
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
}
