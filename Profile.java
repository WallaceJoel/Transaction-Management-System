
package proj2;

/**
 * This class is used represent information in regard to account owners, holds
 * data related to them
 * 
 * @author Joel Wallace, Nicholas McConnell, jtw91 ncm78
 *
 */
public class Profile {
   private String fname;
   private String lname;

   /**
    * Constructor for the class, creates a new profile
    * 
    * @param first, name of account owner
    * @param last,  name of account owner
    */
   public Profile(String first, String last) {
      fname = first;
      lname = last;
   }

   /**
    * Used to compare last names
    * 
    * @param profile, to compare
    * @return 1 if the last name is alphabetically greater, 0 if equal, -1 if less
    *         than
    */
   public int compareLastName(Profile profile) {
      if (this.equals(profile))
         return 0;
      String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      String name1 = this.lname.toUpperCase();
      String name2 = profile.lname.toUpperCase();
      int size = name2.length();
      if (name1.length() < name2.length())
         size = name1.length();
      for (int i = 0; i < size; i++) {
         if (alphabet.indexOf(name1.charAt(i)) < alphabet.indexOf(name2.charAt(i))) {
            return -1;
         }
         if (alphabet.indexOf(name1.charAt(i)) > alphabet.indexOf(name2.charAt(i))) {
            return 1;
         }
      }
      if (name1.length() < name2.length())
         return -1;
      if (name1.length() > name2.length())
         return 1;

      name1 = this.fname.toUpperCase();
      name2 = profile.fname.toUpperCase();
      size = name2.length();
      if (name1.length() < name2.length())
         size = name1.length();
      for (int i = 0; i < size; i++) {
         if (alphabet.indexOf(name1.charAt(i)) < alphabet.indexOf(name2.charAt(i))) {
            return -1;
         }
         if (alphabet.indexOf(name1.charAt(i)) > alphabet.indexOf(name2.charAt(i))) {
            return 1;
         }
      }
      return 0;
   }

   /**
    * Method used to compare profiles
    * 
    * @param obj , a profile to be compared
    * 
    * @return true if equal, false otherwise
    */
   @Override
   public boolean equals(Object obj) {
      Profile profile = (Profile) obj;
      if (this.fname.equals(profile.fname)) {
         if (this.lname.equals(profile.lname))
            return true;
         return false;
      }
      return false;
   }

   @Override
   /**
    * Method used to connect all profile data into one string
    * 
    * @return the first and last name in string format
    */
   public String toString() {
      return "" + fname + " " + lname;
   }
}
