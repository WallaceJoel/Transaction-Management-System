package proj2;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import junit.framework.TestCase;

public class CheckingTest extends TestCase {

   private final Profile phineas = new Profile("Phineas", "Flynn");
   private final Profile ferb = new Profile("Ferb", "Fletcher");
   private final Profile candace = new Profile("Candace", "Flynn");
   private final double interest = 0.0005;
   private final double fee = 25;
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
    * @return boolean, true if their difference is less than the tolerance error,
    *         otherwise false
    */
   public boolean suffEqual(double r1, double r2) {
      final double errTolerance = 0.000001;
      if (r1 - r2 > errTolerance)
         return false;
      if (r2 - r1 > errTolerance)
         return false;
      return true;
   }

   @Test
   public void testMonthlyInterest() {
      Checking checkTest;

      checkTest = new Checking(phineas, 4115, new Date(12, 31, 2020), false);
      assertTrue(suffEqual(checkTest.monthlyInterest(), (4115 * interest) / monthlyDivisor));

      checkTest.credit(10);
      assertTrue(suffEqual(checkTest.monthlyInterest(), (4125 * interest) / monthlyDivisor));

      checkTest = new Checking(candace, 1310, new Date(1, 1, 2021), true);
      assertTrue(suffEqual(checkTest.monthlyInterest(), (1310 * interest) / monthlyDivisor));

      checkTest.debit(15);
      assertTrue(suffEqual(checkTest.monthlyInterest(), (1295 * interest) / monthlyDivisor));
   }

   @Test
   public void testMonthlyFee() {
      Checking checkTest;

      checkTest = new Checking(phineas, 4115, new Date(12, 31, 2020), false);
      assertTrue(suffEqual(checkTest.monthlyFee(), 0)); // should be waived since 4115 > 1500

      checkTest.debit(3000);
      assertTrue(suffEqual(checkTest.monthlyFee(), fee)); // no longer waived

      checkTest = new Checking(ferb, 1499, new Date(1, 1, 2021), true);
      assertTrue(suffEqual(checkTest.monthlyFee(), fee)); // not waived

      checkTest.credit(2);
      assertTrue(suffEqual(checkTest.monthlyFee(), 0));
   }

}
