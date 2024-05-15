package InterviewPrep;

public class LinearSearchAlgo {
	
	static int arr[] = { 1, 2, 5, 67, 83, 2, 0, 3, 63 }; // 0,1,2,3,4,5,6,7,8 //123456789

	static int linear_search = 100;

	static boolean Flag = false;

	public static void ToCheckLinearNumber() {
		for (int i = 0; i < arr.length; i++) {

			if (linear_search == arr[i]) {
				System.out.println("Number is found : " + arr[i]);
				Flag = true;
				break;
			}
		}

		if (Flag == false) {
			System.out.println("Number is not found");
		}

	}

	public static void main(String[] args) {
		ToCheckLinearNumber();

	}

}
