package proj2;

/**
 * This is the JUnit testing for the MoneyMarket class.
 * 
 * @author Nicholas McConnell, Joel Wallace
 *               ncm78           jtw91
 */

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

public class DateTest extends TestCase {

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

   public void testIsValid() {
      assertTrue(new Date(12, 31, 2020).isValid());
      assertTrue(new Date(12, 2, 2001).isValid());
      assertFalse(new Date(6, 31, 2017).isValid()); // should be invalid
      assertFalse(new Date(13, 2, 2002).isValid());
      assertTrue(new Date(1, 29, 2011).isValid());
      assertTrue(new Date(8, 31, 2010).isValid());
      assertFalse(new Date(1, 0, 2011).isValid());
      assertTrue(new Date(2, 29, 2016).isValid()); // should be valid
      assertFalse(new Date(2, 29, 2017).isValid()); // should be invalid
      assertFalse(new Date(2, 29, 2018).isValid()); // should be invalid
      assertTrue(new Date(2, 28, 2018).isValid());
      assertFalse(new Date(2, 29, 1900).isValid());
      // should be invalid because 1900 was an exceptional common year
      assertTrue(new Date(2, 29, 2000).isValid());
      // should be valid because 2000 was a leap year
      assertTrue(new Date(9, 2, 1752).isValid());
      assertFalse(new Date(9, 4, 1752).isValid());
      // should be invalid because September 1752 was missing
      // the days 3-13
   }

}
