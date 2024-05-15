package InterviewPrep;

import java.util.Arrays;

public class BubleSort {
	public static void main(String[] args) {
        int arr[]={1,5,8,20,3,10,7,4,9};
        
        System.out.println("Array UnSorted: "+ Arrays.toString(arr));
        
        int n=arr.length;
        int temp;
        
        for (int i=0;i<n-1;i++)
        {
            for (int j=0;j<n-1;j++)
            {
                if (arr[j] > arr[j+1])
                {
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    
                }
            }
        }
        
       System.out.println("Array Sorted: "+ Arrays.toString(arr));
    }

}
