package demo;
public class Easy{
	/*  C-1.1 problem.
		Solution is O(n) */

	public static boolean productOdd(int[] a){
		for(int i=0;i<n;i++){
			if(a[i]%2==1){
				oddCount++;
			}
			else{
				evenCount++;
			}
		}
		if(oddCount>1){
			return true;
		}
		return false;
	}


	/*  C-1.2 problem.
		Solution is O(n*n) */

	public static boolean allDistinct(int[] a){
		int n = a.length;
		for(int i=0;i<n;i++){
			for(int j=i;j<n;j++){
				if(a[i]==a[j]){
					return false;
				}
			}
		}
		return true;
	}

	public static int dotProduct(int[] a, int[] b){ // Length of both the arrays should be is same.
		int ans=0;
		int n = a.length;
		for(int i=0;i<n;i++){
			ans = ans + a[i]*b[i];
		}
		return ans;

	}


	public static void main(String[] args) {

		 // Testing for dotProduct
		int[] a = {1,2,3,4,5};
		int[] b = {5,2,1,4,3};
		System.out.println(dotProduct(a,b));


	}
}