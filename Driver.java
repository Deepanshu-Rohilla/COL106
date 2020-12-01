import java.util.Scanner;
public class Driver{
    public static void main(String args[]){
        long startTime = System.nanoTime();
        int numTestCases;
        Scanner sc = new Scanner(System.in);
        numTestCases = sc.nextInt();
        while(numTestCases-->0){
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size, 3);
            int numCommands = sc.nextInt();
            while(numCommands-->0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                boolean toPrint = true;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    case "Defragment":
                        obj.Defragment();
                        toPrint = false;
                        break;
                    default:
                        break;
                }
                if(toPrint){
                    System.out.println(result);
                }
            }
            
        }
        long stopTime = System.nanoTime();
        // System.out.println((stopTime - startTime)/1000000000.0);
    }
}