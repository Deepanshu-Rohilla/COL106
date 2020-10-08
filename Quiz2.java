import java.util.*;
public  class Quiz2{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // n = size of the input
		int ar[] = new int[n]; // The main array
		for(int i=0;i<n;i++){
			int x = sc.nextInt(); // Input values and put in the array
			ar[i]=x;
		}
		// Insertion sort begins 
		int i = n-2;
		while(i>=0){
		    int temp = ar[i];
		    int j = i+1;
		    while(j<n && ar[j]<temp){
		        ar[j-1] = ar[j];
		        j++;
		    }
		    ar[j-1] = temp;
		    i--;
		    for(i=0;i<n;i++){
			System.out.print(ar[i]);
		}
		}
		// Insertion sort ends
		for(i=0;i<n;i++){
			System.out.print(ar[i]);
		}

	}
}