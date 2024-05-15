package InterviewPrep;

public class RemoveJunkValueInString {

	
	public static void main(String[] args)
	{
		
		
			String str ="abcdef19845kjha";
			
			String replaced = str.replaceAll("[0-9]", "");
			
			System.out.println(replaced);
			
			
		
	}
}
