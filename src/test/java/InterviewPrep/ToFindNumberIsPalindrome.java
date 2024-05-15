package InterviewPrep;

public class ToFindNumberIsPalindrome {
	
	
	
	public static void ToCheckNumberisPalindrome()
	{
		 int num = 172;
		 String rev ="";
		String convertedToString = Integer.toString(num);
		
		for (int i=convertedToString.length()-1;i>=0;i--)
		{
			rev = rev + convertedToString.charAt(i);
		}
		 
		int convertedToInteger = Integer.parseInt(rev);
		 
		if (num == convertedToInteger)

		{
			System.out.println("Number is palindrome");
		}
		 
		else
		{
			System.out.println("Number is Not palindrome");
		}
		
	}
	
	public static void main(String[] args)
	{
		ToCheckNumberisPalindrome();
	}

}
