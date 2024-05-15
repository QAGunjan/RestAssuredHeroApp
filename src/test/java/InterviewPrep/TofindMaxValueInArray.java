package InterviewPrep;

public class TofindMaxValueInArray {
	
	static int arr[] = {100,14,5,2,6,7,4,65,7,9000};
	static int maxValue=50;
	
	public static void  ToFindMaxValueArray()
	{
		for (int i=0; i<arr.length; i++)
		{
			if (maxValue<arr[i])
			{
				maxValue=arr[i];
			}
		}
		
		System.out.println(maxValue);
	}

	public static void main(String[] args)
	{
		ToFindMaxValueArray();
	
	}
}
