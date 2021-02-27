package proj2;

/**
 * This class is used represent information in regard to Savings account
 * 
 * @author Joel Wallace, Nicholas McConnell, jtw91 ncm78
 *
 */
public class Savings extends Account {
   private boolean isLoyal;
   private final double interest = 0.0025;
   private final double loyalInterest = 0.0035;
   private final double fee = 5;
   private final double waived = 300;

   /**
    * Constructor for Savings Accounts
    * 
    * @param profile, account owner
    * @param bal,     balance
    * @param date,    date account was opened
    * @param loyal,   true if loyal, false if not
    */
   public Savings(Profile profile, double bal, Date date, boolean loyal) {
      super(profile, bal, date);
      isLoyal = loyal;
   }

   @Override
   /**
    * a getter method for receiving the monthly interest for the account
    * 
    * @return the interest this account generates
    */
   public double monthlyInterest() {
      final double monthlyDivisor = 12;
      if (isLoyal)
         return (loyalInterest * this.getBalance()) / monthlyDivisor;
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
    * @return a string connecting the Savings account information.
    */
   public String toString() {
      if (isLoyal)
         return super.toString() + "*special Savings account*";
      return super.toString();
   }

   @Override
   /**
    * check to see if two accounts are equal, profile and account type match
    * 
    * @returns true if, the obj equals this account, false otherwise
    */
   public boolean equals(Object obj) {
      Savings account;
      try {
         account = (Savings) obj;
      } catch (ClassCastException e) {
         return false;
      }
      if (!account.getBank().equals("Savings"))
         return false;
      if (!account.getProfile().equals(this.getProfile()))
         return false;

      return true;
   }

   @Override
   /**
    * get bank type
    * 
    * @return "Savings"
    */
   public String getBank() {
      return "Savings";
   }

}
