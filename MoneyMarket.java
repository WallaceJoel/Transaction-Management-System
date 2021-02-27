package proj2;

/**
 * This class is used to represent information in regard to MoneyMarket account
 * 
 * @author Joel Wallace, Nicholas McConnell, jtw91 ncm78
 *
 */

public class MoneyMarket extends Account {
   private int withdrawals;
   private final int maxWithdrawals = 6;
   private final double interest = 0.0065;
   private final double fee = 12;
   private final double waived = 2500;

   /**
    * Constructor for MoneyMarket Accounts
    * 
    * @param profile, account owner
    * @param bal,     balance
    * @param date,    date account was opened
    */
   public MoneyMarket(Profile profile, double bal, Date date) {
      super(profile, bal, date);
      withdrawals = 0;
   }

   @Override
   /**
    * a getter method for receiving the monthly interest for the account
    * 
    * @return the interest this account generates
    */
   public double monthlyInterest() {
      final double monthlyDivisor = 12;
      return (interest * this.getBalance()) / monthlyDivisor;
   }

   @Override
   /**
    * a getter method for the getting the fee for account
    * 
    * @return monthly fee account will pay
    */
   public double monthlyFee() {
      if (this.getBalance() >= waived && withdrawals <= maxWithdrawals)
         return 0;

      return fee;
   }

   @Override
   /**
    * Withdraws money from balance
    * 
    * @param amount, to be taken from account
    */
   public void debit(double amount) {
      super.debit(amount);
      withdrawals++;
   }

   @Override
   /**
    * Converts account data to string
    * 
    * @return a string connecting the MoneyMarket account information.
    */
   public String toString() {
      if (withdrawals == 1)
         return super.toString() + "*1 withdrawal*";
      return super.toString() + "*" + withdrawals + " withdrawals*";
   }

   @Override
   /**
    * check to see if two accounts are equal, profile and account type match
    * 
    * @returns true if, the obj equals this account, false otherwise
    */
   public boolean equals(Object obj) {
      MoneyMarket account;
      try {
         account = (MoneyMarket) obj;
      } catch (ClassCastException e) {
         return false;
      }
      if (!account.getBank().equals("Money Market"))
         return false;
      if (!account.getProfile().equals(this.getProfile()))
         return false;

      return true;
   }

   @Override
   /**
    * get bank type
    * 
    * @return "MoneyMarket"
    */
   public String getBank() {
      return "Money Market";
   }

}
