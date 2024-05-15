package InterviewPrep;

import java.util.HashSet;

public class DuplicateInArray {

	static int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 8, 7, 6, 5, 4 }; // 87654

	public static void ToCheckDuplicateNumberInArray() {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (set.add(arr[i]) == false) {
				System.out.println("Duplicate numbers are " + arr[i]);
			}
		}
	}

	public static void main(String[] args) {
		ToCheckDuplicateNumberInArray();
	}
}
