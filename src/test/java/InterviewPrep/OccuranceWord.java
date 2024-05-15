package InterviewPrep;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.testng.annotations.Test;

public class OccuranceWord {

	@Test

	public void Main() {

		String str = "Gunjan"; //012345  - > 123456

		String stringAfterRemovingSpaces = str.replace(" ", "");

		HashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>();

		for (int i = 0; i < stringAfterRemovingSpaces.length(); i++) {
			char word = str.charAt(i);

			if (hashmap.containsKey(word)) {
				hashmap.put(word, hashmap.get(word) + 1);

			}

			else {
				hashmap.put(word, 1);
			}
		}

		System.out.println(hashmap);
	}

}
