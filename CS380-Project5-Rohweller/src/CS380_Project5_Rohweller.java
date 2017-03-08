import java.io.IOException;


public class CS380_Project5_Rohweller {

	public static void main(String[] args) throws IOException 
	{
		System.out.println("Justin Rohweller CS380-Project5  002502776 4-4-16");
		//int [] testing = new int[] {9,1,5,4,6,3,10,8,7,9};
		Sorting r = new Sorting();
		String file1String = "SortingIn.txt";
		String file2String = "SortingOut.txt";
		r.sort(file1String, file2String);
		
		//to do:
		//turn in
		

	}

}
