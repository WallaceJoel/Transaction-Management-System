package proj2;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This is the user interface class, handles transactions and displays the
 * results on the console;
 * 
 * @author Joel Wallace, Nicholas McConnell, jtw91 ncm78
 *
 */

public class TransactionManager {
   private AccountDatabase accounts;
   private Account account;
   private boolean running;
   private String[] commands;
   private String badInput = "Input data type mismatch.";
   private String invalidDate = " is not a valid date!";

   /**
    * Insures the amount is in correct cash format;
    * 
    * @param amount , to be formatted
    * @return String , converts amount to the right format
    */
   private String format(double amount) {
      DecimalFormat form = new DecimalFormat("#,###,##0.00");

      return form.format(amount);
   }

   /**
    * Handles O commands from user, for creating an account
    * 
    * @return a String, confirming command success or failure
    */
   private String commandO() {
      double balance;
      Date dateOpen;
      try {
         balance = Double.parseDouble(commands[3]);
         if (balance < 0)
            return badInput;
         String[] date = commands[4].split("/");
         int month = Integer.parseInt(date[0]);
         int day = Integer.parseInt(date[1]);
         int year = Integer.parseInt(date[2]);
         dateOpen = new Date(month, day, year);
      } catch (Exception e) {
         return badInput;
      }
      if (!dateOpen.isValid())
         return dateOpen.toString() + invalidDate;

      switch (commands[0].charAt(1)) {
      case 'C':
         boolean directDeposit = false;
         if (!commands[5].toLowerCase().equals("true") && !commands[5].toLowerCase().contentEquals("false"))
            return badInput;
         if (commands[5].toLowerCase().equals("true"))
            directDeposit = true;
         account = new Checking(new Profile(commands[1], commands[2]), balance, dateOpen, directDeposit);
         break;

      case 'S':
         boolean isLoyal = false;
         if (!commands[5].toLowerCase().equals("true") && !commands[5].toLowerCase().contentEquals("false"))
            return badInput;
         if (commands[5].toLowerCase().equals("true"))
            isLoyal = true;
         account = new Savings(new Profile(commands[1], commands[2]), balance, dateOpen, isLoyal);
         break;

      case 'M':
         account = new MoneyMarket(new Profile(commands[1], commands[2]), balance, dateOpen);
         break;
      default:
         return "Command '" + commands[0] + "' not supported!";
      }
      if (accounts.add(account))
         return "Account opened and added to the database.";
      return "Account is already in the database.";
   }

   /**
    * A helper method for validating profile, and make accounts for searching the
    * database
    * 
    * @return A string confirming success, or error with user data
    */
   private String accountMaker() {
      Profile profile;
      try {
         profile = new Profile(commands[1], commands[2]);
      } catch (Exception e) {
         return badInput;
      }
      switch (commands[0].charAt(1)) {
      case 'C':
         account = new Checking(profile, 0, null, true); // don't need date, for removing accounts
         break;
      case 'S':
         account = new Savings(profile, 0, null, true);
         break;
      case 'M':
         account = new MoneyMarket(profile, 0, null);
         break;
      default:
         return "Command '" + commands[0] + "' not supported!";
      }
      return null;
   }

   /**
    * Handles C commands from user, checking out
    * 
    * @return a String, confirming command success or failure
    */
   private String commandC() {
      String statement = accountMaker();
      if (statement != null)
         return statement;
      if (accounts.remove(account))
         return "Account closed and removed from the database.";
      return "Account does not exist.";
   }

   /**
    * Handles D commands from user, deposit
    * 
    * @return a String, confirming command success or failure
    */
   private String commandD() {
      String statement = accountMaker();
      if (statement != null)
         return statement;
      double amount;
      String deposit;
      try {
         amount = Double.parseDouble(commands[3]);
         deposit = format(amount);
      } catch (Exception e) {
         return badInput;
      }

      if (accounts.deposit(account, amount))
         return "" + deposit + " deposited to the account.";
      return "Account does not exist";
   }

   /**
    * Handles W commands from user, withdraw
    * 
    * @return a String, confirming command success or failure
    */
   private String commandW() {
      String statement = accountMaker();
      if (statement != null)
         return statement;
      double amount;
      String withdraw;
      try {
         amount = Double.parseDouble(commands[3]);
         withdraw = format(amount);
      } catch (Exception e) {
         return badInput;
      }

      switch (accounts.withdrawal(account, amount)) {
      case 1:
         return "Insufficient funds.";
      case 0:
         return "" + withdraw + " withdrawn from account";
      case -1:
         return "Account does not exist";
      }
      return badInput;
   }

   /**
    * Excutes the commands of the user
    * 
    * @param userInput, as string, contains the command and corresponding data
    */
   private void executeInput(String userInput) {
      final int maxCommandLength = 2;
      if (userInput.length() == 0) {
         System.out.println("No input given!");
         return;
      }
      commands = userInput.split(" ");

      String firstCommand = commands[0];
      if (firstCommand.equals("Q")) {
         System.out.println("Transaction processing completed.");
         running = false;
         return;
      }
      if (firstCommand.length() != 2) {
         System.out.println("Command '" + commands[0] + "' not supported!");
         return;
      }

      switch (commands[0].charAt(0)) {
      case 'O':
         System.out.println(commandO());
         break;
      case 'C':
         System.out.println(commandC());
         break;
      case 'D':
         System.out.println(commandD());
         break;
      case 'W':
         System.out.println(commandW());
         break;
      case 'P':
         if (accounts.isEmpty()) {
            System.out.println("Database is empty.");
            break;
         }
         switch (commands[0].charAt(1)) {
         case 'A':
            System.out.println("--Listing accounts in the database--");
            accounts.printAccounts();
            System.out.println("--end of listing--");
            break;
         case 'D':
            System.out.println("\n--Printing statements by date opened--");
            accounts.printByDateOpen();
            System.out.println("--end of printing--");
            break;
         case 'N':
            System.out.println("\n--Printing statements by last name--");
            accounts.printByLastName();
            System.out.println("--end of printing--");
            break;
         default:
            System.out.println("Command '" + commands[0] + "' not supported!");
            break;
         }
         break;
      default:
         System.out.println("Command '" + commands[0] + "' not supported!");
      }

   }

   /**
    * The method which runs the program. It gets called in the 'RunProject2' class.
    */
   public void run() {
      accounts = new AccountDatabase();
      running = true;

      Scanner scanner = new Scanner(System.in);
      System.out.println("Transaction processing starts.....");
      while (running) {
         executeInput(scanner.nextLine());
      }
      scanner.close();
   }

}
