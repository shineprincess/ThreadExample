import java.util.Scanner;
class Account{
  private int amt;
  
  public Account(int amt){
    this.amt=amt;
  }

  public Boolean isSufficientBalance(int wamt){
    if(amt>wamt){
      return true;
    }else
      return false;
  }
  public void withdrawAmount(int wamt){
    amt-=wamt;
    System.out.println("Withdrawl Amt is "+ wamt);
    System.out.println("Balance is "+amt);
  }
}

class Customer implements Runnable{
  private String name;
  Account account;
  Customer(String name,Account account){
    this.name=name;
    this.account=account;
  }
  public void run(){
    Scanner sc= new Scanner(System.in);
    synchronized(account){
      System.out.println("Please Enter a amount for withdrawl " +name);
      int iAmt= sc.nextInt();
      if(account.isSufficientBalance(iAmt)){
        account.withdrawAmount(iAmt);
      }else{
        System.out.println("Insufficient Balance");
      }
    }
  }
  
}


public class Main {
  public static void main(String[] args) {
    System.out.println("Code runs successsfully");
    
    Account account = new Account(1000);
    Customer c1 = new Customer("Rosy",account);
    Customer c2 = new Customer("Liana",account);
    Thread t1 = new Thread(c1);
    Thread t2 = new Thread(c2);
    t1.start();
    t2.start();
  }
}