package proj2;

/**
 * This class is used represent information in regard to the Checking account
 * 
 * @author Joel Wallace, Nicholas McConnell, jtw91 ncm78
 *
 */
public class Checking extends Account {
   private boolean directDeposit;
   private final double interest = 0.0005;
   private final double fee = 25;
   private final double waived = 1500;

   /**
    * Constructor for Checking Accounts
    * 
    * @param profile, account owner
    * @param bal,     balance
    * @param date,    date account was opened
    * @param dd,      directDeposit
    */
   public Checking(Profile profile, double bal, Date date, boolean dd) {
      super(profile, bal, date);
      directDeposit = dd;
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
      if (this.getBalance() >= waived)
         return 0;

      return fee;
   }

   @Override
   /**
    * Converts account data to string
    * 
    * @return a string connecting the Checking account information.
    */
   public String toString() {
      if (directDeposit)
         return super.toString() + "*direct deposit account*";
      return super.toString();
   }

   @Override
   /**
    * check to see if two accounts are equal, profile and account type match
    * 
    * @returns true if, the obj equals this account, false otherwise
    */
   public boolean equals(Object obj) {

      Checking account;
      try {
         account = (Checking) obj;
      } catch (ClassCastException e) {
         return false;
      }
      if (!account.getBank().equals("Checking"))
         return false;
      if (!account.getProfile().equals(this.getProfile()))
         return false;

      return true;
   }

   @Override
   /**
    * get bank type
    * 
    * @return "Checking"
    */
   public String getBank() {
      return "Checking";
   }

}
