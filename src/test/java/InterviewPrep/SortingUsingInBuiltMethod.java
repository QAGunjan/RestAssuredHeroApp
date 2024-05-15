package InterviewPrep;

import java.util.Arrays;

public class SortingUsingInBuiltMethod {
	
	  public static void main(String[] args) {
	        int arr[]={1,5,8,20,3,10,7,4,9};
	        
	        System.out.println("Array UnSorted: "+ Arrays.toString(arr));
	        
	        Arrays.parallelSort(arr);
	      
	        
	       System.out.println("Array Sorted: "+ Arrays.toString(arr));
	    }

}
