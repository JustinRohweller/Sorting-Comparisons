import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Sorting 
{
	String bestMethod=null;
	int selectionSwaps=0;
	int insertionSwaps=0;
	int bubbleSwaps=0;
	int quickSwaps=0;
	int mergeSwaps=0;
	
	int selectionCompares=0;
	int insertionCompares=0;
	int bubbleCompares=0;
	int quickCompares=0;
	int mergeCompares=0;
	
	public void sort(String file1String, String file2String) throws IOException
	{
		File file1 = new File(file1String);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file2String, true)));
		Scanner scan = new Scanner(file1);
		boolean isArray=false;
		
		while (scan.hasNext())
		{
			selectionSwaps=0;
			insertionSwaps=0;
			bubbleSwaps=0;
			quickSwaps=0;
			mergeSwaps=0;
			
			selectionCompares=0;
			insertionCompares=0;
			bubbleCompares=0;
			quickCompares=0;
			mergeCompares=0;
			
			String line = scan.nextLine();
			if (isArray==false)
			{
				isArray=true;
				continue;
			}
			
			//fill up the array
			String[] curLine = line.split(" ");
			int [] numbers = new int[curLine.length];
			for (int i=0; i<curLine.length;i++)
			{
				numbers[i]= Integer.parseInt(curLine[i]);
			}
			//create dummy arrays for each sort
			int [] dummy1 = new int[numbers.length];
			int [] dummy2 = new int[numbers.length];
			int [] dummy3 = new int[numbers.length];
			int [] dummy4 = new int[numbers.length];
			int [] dummy5 = new int[numbers.length];
			for (int i=0; i<curLine.length;i++)
			{
				dummy1[i]=numbers[i];
				dummy2[i]=numbers[i];
				dummy3[i]=numbers[i];
				dummy4[i]=numbers[i];
				dummy5[i]=numbers[i];
			}
			//sort a copy of the array for each sort.
			selectionSort(dummy1);
			insertionSort(dummy2);
			bubbleSort(dummy3);
			mergeSort(dummy4, 0, dummy4.length-1);
			quickSort(dummy5, 0, dummy5.length-1);
			
				out.println(" ");
			System.out.print("\nSorted Array of length "+numbers.length+"\n");
				out.println("\nSorted Array of length "+numbers.length+"\n");
			for (int j = 0;j<numbers.length;j++)
			{
				System.out.print(dummy3[j]+"\t");
					out.print(dummy4[j]+" ");
			}
			isArray=false;
				out.println(" ");
			//print the output
			System.out.println("\nSorting Method\t"+"Swaps\t"+"\tComparisons"+"\n");
				out.println("\nSorting Method\t"+"Swaps\t"+"\tComparisons"+"\n");
			System.out.println("Select\t\t"+selectionSwaps+"\t\t"+selectionCompares);
				out.println("Select\t\t"+selectionSwaps+"\t\t"+selectionCompares);
			System.out.println("Insert\t\t"+insertionSwaps+"\t\t"+insertionCompares);
				out.println("Insert\t\t"+insertionSwaps+"\t\t"+insertionCompares);
			System.out.println("Bubble\t\t"+bubbleSwaps+"\t\t"+bubbleCompares);
				out.println("Bubble\t\t"+bubbleSwaps+"\t\t"+bubbleCompares);
			System.out.println("Merge\t\t"+mergeSwaps+"\t\t"+mergeCompares);
				out.println("Merge\t\t"+mergeSwaps+"\t\t"+mergeCompares);
			System.out.println("Quick\t\t"+quickSwaps+"\t\t"+quickCompares);
				out.println("Quick\t\t"+quickSwaps+"\t\t"+quickCompares);
			
			//picking best method
			int[]best = new int []{selectionSwaps+selectionCompares, insertionSwaps+insertionCompares, bubbleSwaps+ bubbleCompares,mergeSwaps+mergeCompares, quickSwaps+ quickCompares};
			int selectNum = selectionSwaps+selectionCompares;
			selectionSort(best);
			
			if (best[0]==selectNum)
				bestMethod="selection sort";
			if (best[0]==insertionSwaps+insertionCompares)
				bestMethod="insertion sort";
			if (best[0]==bubbleSwaps+bubbleCompares)
				bestMethod="bubble sort";
			if (best[0]==mergeSwaps+mergeCompares)
				bestMethod="merge sort";
			if (best[0]==quickSwaps+quickCompares)
				bestMethod="quick sort";
			System.out.println("Best Sorting Method: "+bestMethod);
				out.println("Best Sorting Method: "+bestMethod);
			
		}
		out.close();		
	}
	
	//good
	public int[] selectionSort(int array[])
	{
		for(int i = array.length - 1; i >= 0; i--)		// start at the end of the array
		{
			int highestIndex = i;				// default value of the highest element index.
			for(int j = i; j >= 0; j--)			// loop from the end of unsorted zone to the beginning of the array.
			{
				if(array[j] > array[highestIndex])	// compare current element to highest
					highestIndex = j;				// if it's higher, it becomes the new highest
				selectionCompares++;
			}
			// swap the two values
			swap(array,i,highestIndex);
			selectionSwaps++;
		}
		return array;
	}
	//good
	public void insertionSort(int array[])
	{
		int temp;		// this variable is the element of the unsorted array currently being compared to elements of the sorted array
		int pos;		// this variable keeps track of position in array swap happens
		
		for (int i = 1; i < array.length; i++)		// loop through the unsorted array
		{
														// (the first element is considered sorted)
			temp = array[i];							// grab the first element of the unsorted array
			pos = i - 1;								// get the index of the last sorted element
			while ((pos >= 0) && (temp < array [pos]))	// while the current sorted element is greater than temp
			{
				array[pos + 1] = array[pos];		// keep shifting sorted elements back by 1
				insertionSwaps++;
				insertionCompares++;
				pos--;					
			}
			array[pos + 1] = temp;				// insert temp
			insertionSwaps++;
		}
	}	
	//good
	 public void bubbleSort( int numbers[])
	 {      
		boolean sorted=false;
	        for(int i = 0; (i < numbers.length)&&!(sorted); i++)
	        {
	        	sorted=true;
	        	for(int j = 1; j < (numbers.length -i); j++)
	        	{
	        		//if numbers[j-1] > numbers[j], swap the elements
	        		if(numbers[j-1] > numbers[j])
	        		{
	        			swap(numbers,j-1, j);
	        			bubbleSwaps++;
	        			sorted=false;
	        		}
	        		bubbleCompares++;
	            }
	        }
	 }
	 //good
	public void quickSort(int array[], int start, int end)
	{
	        int i = start;                          // index of left-to-right scan
	        int k = end;                            // index of right-to-left scan

	        if (end - start >= 1)                   // check that there are at least two elements to sort
	        {
	                int pivot = array[start];       // set the pivot as the first element in the partition

	                while (k > i)                   // while the scan indices from left and right have not met,
	                {
	                        while (array[i] <= pivot && i <= end && k > i)  // from the left, look for the first
	                        {
	                        	i++;                                    // element greater than the pivot
	                        	quickCompares++;
	                        }
	                        while (array[k] > pivot && k >= start && k >= i) // from the right, look for the first
	                        {
	                        	k--;                                        // element not greater than the pivot
	                        	quickCompares++;
	                        }
	                        if (k > i)                                       // if the left index is still smaller than
	                        {
	                        	swap(array, i, k);                      // the right index, swap
	                        	quickSwaps++;
	                        }
	                }
	                swap(array, start, k);          // after the indices have crossed, swap the last element in
	                quickSwaps++;                   // the left partition with the pivot 
	                quickSort(array, start, k - 1); // quicksort the left
	                quickSort(array, k + 1, end);   // quicksort the right
	        }
	        else    							// if there is only one element in the part, return
	        {
	                return;          
	        }
	}

	public void mergeSort(int[] num, int i, int j) 
	{
		int low = i; //bottom of array
		int high = j; //end of the array
		
		if (low >= high) //if the user put in bad input return
			return;
		
		int middle = (low + high) / 2; //find middle of array
		
		mergeSort(num, low, middle); 		//recursive part left
		mergeSort(num, middle + 1, high); 	//recursive part right

		int endLow = middle; 		//set end of first part to middle
		int startHigh = middle + 1; //set beginning of second part to middle plus one
		
		while ((low <= endLow) && (startHigh <= high))  //while the bottom is not sorted, and the top is not sorted
		{
			if (num[low] < num[startHigh]) 				//move to the right as long as still in left part
			{
				low++; 
				mergeCompares++;
			}
			else 
			{
				int Temp = num[startHigh]; 				//set temp to first number in right half of array
				for (int k = startHigh- 1; k >= low; k--) //for the left half of the array
				{
					num[k+1] = num[k];					//cycle through array
					mergeSwaps++;
				}
				num[low] = Temp;						// switch to right half
				low ++;
				endLow ++;
				startHigh ++;
			}
		}
	}
	
	public void swap(int array[], int number1, int number2) 
	{
		int temp = array[number1];           // store first in temp
		array[number1] = array[number2];      // copy second to first
		array[number2] = temp;               // copy temp to second
	}




}