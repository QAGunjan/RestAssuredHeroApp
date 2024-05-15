package InterviewPrep;

public class ToFindMinimumValueInArray {
	
	static int arr[] = {100,200,12,5,6,6,7,8,1,5,9};
	
	static int MiniValue= 90;	
	public static void ToCheckMiniValueInArray()
	{
		for (int i=0; i<arr.length; i++)
		{
			if (MiniValue>arr[i])
			{
				MiniValue = arr[i];
			}
		}
		
		System.out.println(MiniValue);
	}
	
	public static void main(String[] args)
	{
		ToCheckMiniValueInArray();
	}

}
