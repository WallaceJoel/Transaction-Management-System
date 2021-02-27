package proj2;

/**
 * This class is the 'Date' class to be used in the project. It tests validity
 * of dates and compares them chronologically (however, it does not compute
 * which date comes immediately after a certain date).
 * 
 * @author Nicholas McConnell, Joel Wallace ncm78 jtw91
 */

public class Date implements Comparable<Date> {
   private int year;
   private int month;
   private int day;

   /**
    * Constructor for creating Date
    * 
    * @param month , the month 1-12
    * @param day   , the day
    * @param year  , the year
    */
   public Date(int month, int day, int year) {
      this.month = month;
      this.day = day;
      this.year = year;
   }

   /**
    * Compares this date to another date.
    *
    * @param date , another date to compare with this one
    * @return 0, 1 or -1, depending on which date comes first
    */
   @Override
   public int compareTo(Date date) {
      if (year < date.year)
         return -1;
      if (year > date.year)
         return 1;
      if (month < date.month)
         return -1;
      if (month > date.month)
         return 1;
      if (day < date.day)
         return -1;
      if (day > date.day)
         return 1;
      return 0;
   }

   /**
    * Converts the date to string format to be printed.
    *
    * @return the date in the string format
    */
   @Override
   public String toString() {
      String monthDisp = "" + month;
      while (monthDisp.length() < 2) {
         monthDisp = "0" + monthDisp;
      }
      String dayDisp = "" + day;
      while (dayDisp.length() < 2) {
         dayDisp = "0" + dayDisp;
      }
      String yearDisp = "" + year;
      while (yearDisp.length() < 4) {
         yearDisp = "0" + yearDisp;
      }
      return monthDisp + "/" + dayDisp + "/" + yearDisp;
   }

   /**
    * Returns whether the date is a valid one.
    *
    * @return true if it is valid, otherwise false
    */
   public boolean isValid() {
      // First, we assume B.C. years can't be involved,
      // so that the year must be a positive integer.
      if (year <= 0)
         return false;

      // Next, we need the month to be between 1 and 12.
      if (month < 1 || month > 12)
         return false;

      // The day must be a integer from 1 to 31 (in general).
      if (day < 1 || day > 31)
         return false;

      // April, June, September and November have only 30 days.
      if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
         return false;

      // February has 28 days, except on a leap year, when it has 29.
      // Here we have to be careful: multiples of 100 which are not
      // divisible by 400 (such as 1900) are exceptional common years.
      if (month == 2) {
         boolean leapYear;
         if (year % 400 == 0) {
            leapYear = true;
         } else if (year % 100 == 0) {
            leapYear = false;
         } else if (year % 4 == 0) {
            leapYear = true;
         } else {
            leapYear = false;
         }
         if (day > 29)
            return false;
         if (!leapYear && day == 29)
            return false;
      }

      // Finally, September 1752 was missing the days 3-13, so the
      // date can't be any of them.
      if (year == 1752 && month == 9 && day > 2 && day < 14)
         return false;

      // If the code reaches here, the date passed all tests for validity.
      return true;
   }

}