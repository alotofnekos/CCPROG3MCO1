
import java.util.Scanner;

import com.billvnd.BillVnd;
public class BillVending {
    public static void main(String[] args) {
        Scanner scmain = new Scanner(System.in);
        int choice = 0;
        BillVnd vnd = new BillVnd();
        while(choice!=3){
            System.out.println("Press 1 for coin maintenance, 2 for purchasing, and 3 to exit");
            choice = scmain.nextInt();
            scmain.nextLine();
            if(choice==1){
                vnd.billMaintenance();
            }
            else if(choice==2){
                vnd.purchaseItem();
            }
            else if(choice==3){
                System.out.println("You are exiting the vending machine. Have a nice day!");
            }
            else{
                System.out.println("Invalid choice. Please try again");
            }
        }
        scmain.close();
    }
}
