package proj2;

import junit.framework.TestCase;

public class testingClass extends TestCase {

   public void testingChecking() {
      Checking account = new Checking(new Profile("Joel", "Wallace"), 700, new Date(1, 10, 2020), true);

      assert (account.monthlyInterest() == 700 * 0.0005); // should be true

   }

   public void testingChecking2() {
      Checking account = new Checking(new Profile("Joel", "Wallace"), 700, new Date(1, 10, 2020), true);

      assert (account.monthlyFee() == 0); // should be false

   }

   public void testingChecking3() {
      Checking account = new Checking(new Profile("Joel", "Wallace"), 700, new Date(1, 10, 2020), true);

      assert (account.monthlyFee() == 25); // should be false

   }

   public void testingChecking4() {
      Checking account = new Checking(new Profile("Joel", "Wallace"), 700, new Date(1, 10, 2020), true);

      assert (account.getBank() == "Savings"); // should be false

   }

}
