/*  For this problem, we will follow following properties:
1. We will consider the year to NOT be a leap year
2. Each date is represented by a number. For eg. 1 Jan by 1, 1 Feb by 32 ...... 31 Dec by 365.
	We could have chosen Date data type but we have much simpler comparisons, so we will stick to this notation.*/
import java.util.Random;
public class Paradox{
	public static boolean allDistinct(int[] a){
		int n = a.length;
		for(int i=0;i<n;i++){
			for(int j=i+1;j<n;j++){
				if(a[i]==a[j]){
					return false;
				}
			}
		}
		return true;
	}
	public static void print(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]);
			System.out.print(" ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Random r = new Random();
		int[] peopleInRoom = new int[20];
		for(int i=0;i<20;i++){
			peopleInRoom[i] = (i+1)*5;
		}
		for(int i=0;i<20;i++){
			int[] birthdays = new int[peopleInRoom[i]];
			for(int j=0;j<peopleInRoom[i];j++){
				birthdays[j] = 1 + r.nextInt(365);
			}
			print(birthdays);
			if(allDistinct(birthdays)){
				System.out.println("For n = " + peopleInRoom[i] + " All birthdays are distinct");
			}
			else{
				System.out.println("For n = " + peopleInRoom[i] + " Two or more people have same birthday");
			}
		}
	}
}

