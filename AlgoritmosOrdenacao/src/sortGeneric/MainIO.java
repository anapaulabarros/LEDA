package sortGeneric;

public class MainIO {

	public static void main(String[] args) {
		Sort<Integer> teste = new Sort<>();
		Integer[] newObj = {3,1,4,2,5};
		String[]  newStr = {"b", "c", "a", "a"};
		teste.selectionSort(newObj);
		for (Integer s : newObj) {
			System.out.print(s + " ");
		}
	}
}
