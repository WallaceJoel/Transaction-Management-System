
package proj2;

import java.text.DecimalFormat;

/**
 * This class is an array-based container class, used to manage multiple
 * accounts within a database.
 * 
 * @author Joel Wallace, Nicholas McConnell, jtw91 ncm78
 *
 */
public class AccountDatabase {
   private final int growCapacity = 5;
   private Account[] accounts;
   private int size;
   private int capacity;

   /**
    * Constructor for the database set size at 0 and initializes array;
    */
   public AccountDatabase() {
      capacity = growCapacity;
      accounts = new Account[capacity];
      size = 0;
   }

   /**
    * Insures the amount is in correct cash format;
    * 
    * @param amount , the amount to be formatted.
    * @return String , converts amount to the right format
    */
   private String format(double amount) {
      DecimalFormat form = new DecimalFormat("#,###,##0.00");
      return form.format(amount);
   }

   /**
    * Used to find an account index within the database
    * 
    * @param account, for the index to find
    * @return the index of the account in the array, -1 if its not in it
    */
   private int find(Account account) {
      for (int i = 0; i < size; i++) {
         if (account.equals(accounts[i]))
            return i;
      }
      return -1;
   }

   /**
    * Used to increase the size of the array by 5(growCapacity).
    * 
    */
   private void grow() {
      Account[] newAccounts = new Account[capacity + growCapacity];
      for (int i = 0; i < size; i++) {
         newAccounts[i] = accounts[i];
      }
      capacity += growCapacity;
      accounts = newAccounts;
   }

   /**
    * used to add an account into the database
    * 
    * @param account, to be added
    * @return true if the account is added, returns false if account is already
    *         there
    */
   public boolean add(Account account) {
      int accountIndex = find(account);
      if (accountIndex >= 0)
         return false;
      accounts[size] = account;
      size++;
      if (size == capacity)
         grow();
      return true;
   }

   /**
    * Removes account from database
    * 
    * @param account, to be removed
    * @return ture if removed, false if account isn't there
    */
   public boolean remove(Account account) {
      int accountIndex = find(account);
      if (accountIndex < 0)
         return false;
      size--;
      accounts[accountIndex] = accounts[size];
      accounts[size] = null;
      return true;
   }

   /**
    * This is used to deposit to an account in the database
    * 
    * @param account, to receive deposit
    * @param amount,  amount to deposit
    * @return true if deposit is successful, false if account doesn't exist.
    */
   public boolean deposit(Account account, double amount) {
      int accountIndex = find(account);
      if (accountIndex < 0)
         return false;

      accounts[accountIndex].credit(amount);
      return true;
   }

   /**
    * This is used to with draw from an accoun in the database
    * 
    * @param account, to receive withdraw
    * @param amount,  to withdraw
    * @return 0: withdrawal successful, 1: insufficient funds, -1 account doesn’t
    *         exist
    */
   public int withdrawal(Account account, double amount) {
      int accountIndex = find(account);
      if (accountIndex < 0)
         return -1;

      double accountfunds = accounts[accountIndex].getBalance() - amount;
      if (accountfunds < 0)
         return 1;

      accounts[accountIndex].debit(amount);
      return 0;

   }

   /**
    * This method is a helper method for the the two "sortBy..." method, used for
    * mergeSort
    * 
    * @param choice,   1 for sortByDate, 0 for sortByLastName
    * @param account1  , the main account
    * @param account2, the account to be compared to
    * @return true, if account 1 is less than or equal, false otherwise
    */
   private boolean compare(int choice, Account account1, Account account2) {
      if (choice == 1) {
         int comparison = account1.getDate().compareTo(account2.getDate()); // 0 if equals, 1 if greater, -1 if less
         if (comparison <= 0)
            return true;
         return false;
      }
      int comparison = account1.getProfile().compareLastName(account2.getProfile()); // 0 if equals, 1 if greater, -1 if
                                                                                     // less
      if (comparison <= 0)
         return true;
      return false;

   }

   /**
    * This and the "mergeSort" method combine to create a merge sort algorithm used
    * for the two "sortBy" methods
    * 
    * @param copyAccounts , a portion of accounts array
    * @param l,           the first index of the copyAccounts array
    * @param m,           the middle index
    * @param r,           the rightmost index
    * @param choice,      1 for sortByDate, 0 for sortByLastName
    */
   private void merge(Account copyAccounts[], int l, int m, int r, int choice) {
      int leftSize = m - l + 1;
      int rightSize = r - m;

      Account leftAccounts[] = new Account[leftSize];
      Account rightAccounts[] = new Account[rightSize];

      for (int i = 0; i < leftSize; i++)
         leftAccounts[i] = copyAccounts[l + i];
      for (int j = 0; j < rightSize; j++)
         rightAccounts[j] = copyAccounts[m + 1 + j];

      int i = 0, j = 0;
      int x = l;
      while (i < leftSize && j < rightSize) {
         if (compare(choice, leftAccounts[i], rightAccounts[j])) {
            copyAccounts[x] = leftAccounts[i];
            i++;
         } else {
            copyAccounts[x] = rightAccounts[j];
            j++;
         }
         x++;
      }

      while (i < leftSize) {
         copyAccounts[x] = leftAccounts[i];
         i++;
         x++;
      }

      while (j < rightSize) {
         copyAccounts[x] = rightAccounts[j];
         j++;
         x++;
      }
   }

   /**
    * 
    * This and the "merge" method combine to create a merge sort algorithm used for
    * the two "sortBy" methods
    * 
    * @param copyAccounts , a portion of accounts array
    * @param l,           the first index of the copyAccounts array
    * @param r,           the rightmost index
    * @param choice,      1 for sortByDate, 0 for sortByLastName
    */
   private void mergeSort(Account copyAccounts[], int l, int r, int choice) {
      if (l < r) {

         int middleIndex = (l + r) / 2; // used for middle index
         mergeSort(copyAccounts, l, middleIndex, choice);
         mergeSort(copyAccounts, middleIndex + 1, r, choice);
         merge(copyAccounts, l, middleIndex, r, choice);
      }
   }

   /**
    * sorts the database by the date in which it was open, sort in ascending order
    */
   private void sortByDateOpen() {
      Account[] accountsCopy = accounts;
      mergeSort(accountsCopy, 0, size - 1, 1);
      accounts = accountsCopy;
   }

   /**
    * sorts the database by the last name of the account owner, sort in ascending
    * order
    */
   private void sortByLastName() {
      Account[] accountsCopy = accounts;
      mergeSort(accountsCopy, 0, size - 1, 0);
      accounts = accountsCopy;
   }

   /**
    * helper method for printByDateOpen and printByLastName, prints accounts, fees,
    * interest, and final balance.
    */
   private void printStatement() {
      for (int i = 0; i < size; i++) {
         System.out.println("\n" + accounts[i].toString());
         System.out.println("-interest: $ " + format(accounts[i].monthlyInterest()));
         System.out.println("-fee: $ " + format(accounts[i].monthlyFee()));
         double newBalance = accounts[i].getBalance() - accounts[i].monthlyFee() + accounts[i].monthlyInterest();
         accounts[i].debit(accounts[i].monthlyFee());
         accounts[i].credit(accounts[i].monthlyInterest());
         System.out.println("-new balance: $ " + format(newBalance));
      }
   }

   /**
    * prints the database in Date open order, along with interest and fee, and new
    * balance
    */
   public void printByDateOpen() {
      sortByDateOpen();
      printStatement();
   }

   /**
    * prints the database by the last names of account holders, along with interest
    * and fee, and new balance
    */
   public void printByLastName() {
      sortByLastName();
      printStatement();
   }

   /**
    * prints out the database
    */
   public void printAccounts() {
      for (int i = 0; i < size; i++)
         System.out.println(accounts[i].toString());
   }

   /**
    * Method to check if database is empty
    * 
    * @return true if empty, false if not
    */
   public boolean isEmpty() {
      return size == 0;
   }

}
