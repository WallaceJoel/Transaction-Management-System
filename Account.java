package proj2;

import java.text.DecimalFormat;

/**
 * This class is an abstract class used to be the parent of Checking class,
 * Savings class, MoneyMarket class. This class represents a bank account.
 * 
 * @author Joel Wallace, Nicholas McConnell, jtw91 ncm78
 *
 */
public abstract class Account {
   private Profile holder;
   private double balance;
   private Date dateOpen;

   /**
    * Constructor for class
    * 
    * @param profile ,data regarding account owner
    * @param bal     ,balance for the money in the account
    * @param date    ,date account was opened
    */
   public Account(Profile profile, double bal, Date date) {
      holder = profile;
      balance = bal;
      dateOpen = date;
   }

   /**
    * Withdraws money from balance
    * 
    * @param amount , to be taken from account
    */
   public void debit(double amount) {
      balance -= amount;
   }

   /**
    * Deposits money into balance
    * 
    * @param amount , to increase balance by
    */
   public void credit(double amount) {
      balance += amount;
   }

   /**
    * Method used to receive balance from account
    * 
    * @return balance in account
    */
   public double getBalance() {
      return balance;
   }

   /**
    * a getter method for date
    * 
    * @return the date the account was open
    */
   public Date getDate() {
      return dateOpen;
   }

   /**
    * a getter method for holder @return, the profile for account
    * 
    * @return holder , the profile of the account
    */
   public Profile getProfile() {
      return holder;
   }

   /**
    * Insures the amount is in correct cash format;
    * 
    * @param amount , the double to be correctly formated into a string
    * @return String , converts amount to the right format
    */
   private String format(double amount) {
      DecimalFormat form = new DecimalFormat("#,###,##0.00");
      return form.format(amount);
   }

   @Override
   /**
    * Connects all data into string for Account
    * 
    * @return String, containing all data for account
    */
   public String toString() {
      return "*" + this.getBank() + "*" + holder.toString() + "* $" + format(balance) + "*" + dateOpen.toString();
   }

   /**
    * How method operates will depend on the sub class, abstract method
    * 
    * @return the amount of monthly interest for the account.
    */
   public abstract double monthlyInterest();

   /**
    * Method operates differently for each sub class, abstract method.
    * 
    * @return the amount the account must pay
    */
   public abstract double monthlyFee();

   /**
    * method for figuring out what type of account this is.
    *
    * @return, the account type
    */
   public abstract String getBank();

   @Override
   /**
    * check to see if two accounts are equal, profile and account type match
    * 
    * @returns true if, the obj equals this account, false otherwise
    */
   public abstract boolean equals(Object obj);

}
