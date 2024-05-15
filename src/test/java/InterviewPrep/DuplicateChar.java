package InterviewPrep;

import java.util.HashSet;

public class DuplicateChar {

	public static void main(String[] args) {
		String str = "Gunja n";

		// n

		String spacesRemoval = str.replace(" ", "");

		HashSet<Character> set = new HashSet<Character>();

		for (int i = 1; i < spacesRemoval.length(); i++) {
			char rev = spacesRemoval.charAt(i);

			if (set.add(rev) == false) {
				System.out.println("Duplicate value is - " + rev);
			}
		}

	}

}
