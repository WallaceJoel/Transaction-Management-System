package proj2;

/**
 * This is the JUnit testing for the MoneyMarket class.
 * 
 * @author Nicholas McConnell, Joel Wallace
 *               ncm78           jtw91
 */

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import junit.framework.TestCase;

public class MoneyMarketTest extends TestCase {

   private final Profile isabella = new Profile("Isabella", "Garcia-Shapiro");
   private final Profile baljeet = new Profile("Baljeet", "Tjinder");
   private final Profile buford = new Profile("Buford", "Van Stomm");
   private final double interest = 0.0065;
   private final double fee = 12;
   private final int monthlyDivisor = 12;

   /**
    * @throws java.lang.Exception
    **/
   public static void setUpBeforeClass() throws Exception {
      System.out.println("@ static void uthils.EclipseTimes.setUpBeforeClass() throws Exception");
      TimeUnit.SECONDS.sleep(3);
   }

   /**
    * @throws java.lang.Exception
    **/
   public static void tearDownAfterClass() throws Exception {
      System.out.println("@ static void uthils.EclipseTimes.tearDownAfterClass() throws Exception");
      TimeUnit.SECONDS.sleep(2);
   }

   /**
    * @throws java.lang.Exception
    **/
   @Override
   public void setUp() throws Exception {
      System.out.println("@ void uthils.EclipseTimes.setUp() throws Exception");
      TimeUnit.SECONDS.sleep(4);
   }

   /**
    * @throws java.lang.Exception
    **/
   @Override
   public void tearDown() throws Exception {
      System.out.println("@ void uthils.EclipseTimes.tearDown() throws Exception");
      TimeUnit.SECONDS.sleep(1);
   }

   /**
    * Since floating errors are present, this method allows doubles to be
    * considered equal if their difference is less than the tolerance error of
    * .000001.
    *
    * @param double, the first double to compare
    * @param double, the second double to compare
    * @return boolean, true if their difference is less than the tolerance error,
    *         otherwise false
    */
   boolean suffEqual(double r1, double r2) {
      final double errTolerance = 0.000001;
      if (r1 - r2 > errTolerance)
         return false;
      if (r2 - r1 > errTolerance)
         return false;
      return true;
   }

   @Test
   public void testMonthlyInterest() {
      MoneyMarket mmTest;

      mmTest = new MoneyMarket(isabella, 4115, new Date(12, 31, 2020));
      assertTrue(suffEqual(mmTest.monthlyInterest(), (4115 * interest) / monthlyDivisor));

      mmTest.credit(10);
      assertTrue(suffEqual(mmTest.monthlyInterest(), (4125 * interest) / monthlyDivisor));

      mmTest = new MoneyMarket(baljeet, 1310, new Date(1, 1, 2021));
      assertTrue(suffEqual(mmTest.monthlyInterest(), (1310 * interest) / monthlyDivisor));

      mmTest.debit(15);
      assertTrue(suffEqual(mmTest.monthlyInterest(), (1295 * interest) / monthlyDivisor));
   }

   @Test
   public void testMonthlyFee() {
      MoneyMarket mmTest;

      mmTest = new MoneyMarket(isabella, 4115, new Date(12, 31, 2020));
      assertTrue(suffEqual(mmTest.monthlyFee(), 0)); // should be waived since 4115 > 2500

      mmTest.debit(2000);
      assertTrue(suffEqual(mmTest.monthlyFee(), fee)); // not waived

      mmTest = new MoneyMarket(baljeet, 2499, new Date(1, 1, 2021));
      assertTrue(suffEqual(mmTest.monthlyFee(), fee));

      mmTest.credit(2);
      assertTrue(suffEqual(mmTest.monthlyFee(), 0));

      mmTest = new MoneyMarket(buford, 2600, new Date(1, 1, 2021));
      assertTrue(suffEqual(mmTest.monthlyFee(), 0));

      mmTest.debit(10);
      mmTest.debit(10);
      mmTest.debit(10);
      mmTest.debit(10);
      mmTest.debit(10);
      mmTest.debit(10);
      assertTrue(suffEqual(mmTest.monthlyFee(), 0)); // still waived

      mmTest.debit(10);
      assertTrue(suffEqual(mmTest.monthlyFee(), fee));
      // Now, although the balance (2530) still exceeds 2500,
      // it shouldn't be waived since maximum withdrawals have been exceeded.
   }

}
