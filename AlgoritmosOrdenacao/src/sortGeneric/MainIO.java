package sortGeneric;

public class MainIO {

	public static void main(String[] args) {
		Sort<Integer> teste = new Sort<>();
		Integer[] newObj = {3,1,4,2,5};
		Integer[]  newStr = {10, 9, 8, 7, 6, 5, 4, 3, -1, -1};
		teste.insertionSort(newStr);
		for (Integer s : newStr) {
			System.out.print(s + " ");
		}
	}
}
