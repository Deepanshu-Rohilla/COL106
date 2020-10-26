package demo;
import java.util.Date;
public class CreditCard{
	// Instance variables
	private String number;
	private String name;
	private String bank;
	private double balance;
	private int limit;

	// Class Constructor
	public CreditCard(String nmbr, String nme, String bnk, double bal, int lim){
		number = nmbr;
		name = nme;
		bank = bnk;
		balance = bal;
		limit = lim;
	}
	public CreditCard(){
		number = "a";
		name = "b";
		bank = "c";
		balance = 0.0;
		limit = 0;
	}

	// Access methods also known as getters
	public String getNumber(){ return number;}
	public String getName(){ return name;}
	public String getBank(){ return bank;}
	public double getBalance(){ return balance;}
	public int getLimit(){ return limit;}

	// Action methods 
	public boolean recharge(double amount){
		if(amount + balance > limit){
			return false;
		}
		balance = balance + amount;
		return true;
	}
	public void makePayment(double payableAmount, Date dueDate ){
		balance = balance - payableAmount; 
		balance = balance - 0.01*payableAmount; // interest on each payment
		Date currentDate = new Date();
		long currentTime = currentDate.getTime();
		long dueTime = dueDate.getTime();
		if(currentTime>dueTime){
			int lateFees = 69;
			balance = balance - lateFees;
		}

	}
	public static void printCard(CreditCard card){
		System.out.println("Card number: " + card.getNumber());
		System.out.println("Card holder name: " + card.getName());
		System.out.println("Bank name: " + card.getBank());
		System.out.println("Balance amount: " + card.getBalance());
		System.out.println("Limit: " + card.getLimit());
	}
}



