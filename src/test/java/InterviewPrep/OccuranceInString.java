package InterviewPrep;

import java.util.HashMap;

public class OccuranceInString {
	
	public static void main(String[] args)
	{
		String str = "my name is gunjan";        
		
	String replaceAfterremovalSpaces = str.replace(" ", "");
	
	int lengthCount = replaceAfterremovalSpaces.length();
	
	char words;
	
	System.out.println(lengthCount);
	
	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	
	for (int i =0; i<=lengthCount-1;i++)
	{
		  words = replaceAfterremovalSpaces.charAt(i);
		  
		  if (map.containsKey(words))
		  {
			  map.put(words, map.get(words) + 1);
		  }
		  
		  else
		  {
			  map.put(words, 1);
		  }
		  
	}
	
	System.out.println(map);
		
	}

}
