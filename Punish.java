// P-1.1 problem
import java.util.Random;
public class Punish{
	public static void doTypo(String s, Random r){
		int randomIndex = r.nextInt(s.length()); // Pick up a random character from the string
		while(s.charAt(randomIndex) ==' '){    // Ignore spaces as it won't look like a typo.
			randomIndex++;
		}
		int ascii = (int) s.charAt(randomIndex);
		int replacedAscii;
		/*In following lines, it is made sure that if a character is uppercase,
		 then it is replaced by an uppercase letter only and same for lowercase characters also */
		if(ascii>=65 && ascii<=90){
			 replacedAscii = 65 + r.nextInt(26);
			while(replacedAscii==ascii){
				replacedAscii = 65 + r.nextInt(26);
			}
		}
		else if (ascii>=97 && ascii<=122){
			 replacedAscii = 97 + r.nextInt(26);
			while(replacedAscii==ascii){
				replacedAscii = 97 + r.nextInt(26);
			}
		}
		/*	Here, though there are no special characters in given punishment statement
		but still that thing is handled seperately
		 */
		else{
			 replacedAscii = 33 + r.nextInt(32);
			while(replacedAscii==ascii){
				replacedAscii = 33 + r.nextInt(32);
			}
		}
		char replacedChar = (char) replacedAscii;
		// Printing out the results
		System.out.print(s.substring(0,randomIndex));
		System.out.print(replacedChar);
		System.out.println(s.substring(randomIndex+1,s.length()));
	}
	public static void main(String[] args){
		String punishment = "I will not spam my friends again"; // punishment statement
		int countOfTypos=0; 
		Random r = new Random();
		int n = punishment.length();
		for(int i=0;i<100;i++){
			if(countOfTypos<8){
				if(100-i==8-countOfTypos){ 
					// Here we have to make typo in all remaining statements to make total count as 8
					for(int j=0;j<countOfTypos;j++){
						doTypo(punishment,r); 
					}
					countOfTypos=8;
					break;
				}
				else{
					int doTypo = r.nextInt(2); // Making a random decision on whether to do a typo
					if(doTypo==0){
						System.out.println(punishment);
					}
					else{
						countOfTypos++;
						doTypo(punishment,r);
					}
				}
			}
			else{
				System.out.println(punishment); // Printing correct statements
			}
		}
		System.out.println("Total typos are : " + countOfTypos);
	}
}