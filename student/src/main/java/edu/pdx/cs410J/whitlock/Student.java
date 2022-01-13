package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
                                                                                    
/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {

  static final String THEY_PRONOUN = "They";
  static final String OTHER_GENDER = "other";
  static final String FEMALE_PRONOUN = "She";
  static final String MALE_PRONOUN = "He";
  static final String FEMALE_GENDER = "female";
  static final String MALE_GENDER = "male";

  private final String genderPronoun;

  /**
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male", "female", or "other", case-insensitive)
   */                                                                               
  public Student(String name, ArrayList<String> classes, double gpa, String gender) {
    super(name);

    if (name.length() > 40) {
      throw new IllegalArgumentException("Name is too long");
    }

    this.genderPronoun = getGenderPronoun(gender);
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    throw new UnsupportedOperationException("Not implemented yet");
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    return this.genderPronoun;
  }

  private String getGenderPronoun(String gender) {
    if (FEMALE_GENDER.equalsIgnoreCase(gender)) {
      return FEMALE_PRONOUN;

    } else if (MALE_GENDER.equalsIgnoreCase(gender)) {
      return MALE_PRONOUN;

    } else if (OTHER_GENDER.equalsIgnoreCase(gender)) {
      return THEY_PRONOUN;
    }

    throw new UnrecognizedGenderException("Gender " + gender + " not supported yet");
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    System.err.println("Missing command line arguments");
    System.exit(1);
  }

  public static class UnrecognizedGenderException extends RuntimeException {
    public UnrecognizedGenderException(String message) {
      super(message);
    }
  }
}