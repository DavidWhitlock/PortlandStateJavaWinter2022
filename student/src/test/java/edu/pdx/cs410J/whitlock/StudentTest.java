package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.whitlock.Student.InvalidGpaException;
import edu.pdx.cs410J.whitlock.Student.UnrecognizedGenderException;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = createStudentNamed(name);
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  void nameLongerThan40CharactersThrowsIllegalArgumentException() {
    String tooLongName = "12345678901234567890123456789012345678901";
    assertThrows(IllegalArgumentException.class, () -> createStudentNamed(tooLongName));
  }

  private Student createStudentNamed(String studentName) {
    return new Student(studentName, new ArrayList<>(), 0.0, "other");
  }

  @Test
  void femaleGenderHasShePronoun() {
    Student femaleStudent = createStudentWithGender(Student.FEMALE_GENDER);
    assertThat(femaleStudent.toString(), containsString(Student.FEMALE_PRONOUN));
  }

  private Student createStudentWithGender(String gender) {
    return new Student("Name", new ArrayList<>(), 0.0, gender);
  }

  @Test
  void maleGenderHasHePronoun() {
    Student maleStudent = createStudentWithGender(Student.MALE_GENDER);
    assertThat(maleStudent.toString(), containsString(Student.MALE_PRONOUN));
  }

  @Test
  void otherGenderHasTheyPronoun() {
    Student maleStudent = createStudentWithGender(Student.OTHER_GENDER);
    assertThat(maleStudent.toString(), containsString(Student.THEY_PRONOUN));
  }

  @Test
  void otherGenderCaseInsensitiveHasTheyPronoun() {
    Student maleStudent = createStudentWithGender("OTHER");
    assertThat(maleStudent.toString(), containsString(Student.THEY_PRONOUN));
  }

  @Test
  void unrecognizedGenderThrowsUnrecognizedGenderException() {
    String unrecognizedGender = "unsupported";
    UnrecognizedGenderException ex =
      assertThrows(UnrecognizedGenderException.class, () -> createStudentWithGender(unrecognizedGender));
    assertThat(ex.getMessage(), containsString(unrecognizedGender));
  }

  @Test
  void toStringContainsStudentName() {
    String studentName = "Dave";
    Student dave = createStudentNamed(studentName);
    assertThat(dave.toString(), containsString(studentName));
  }

  @Test
  void tooLargeGpaThrowAnInvalidGpaException() {
    double invalidGpa = 4.01;
    InvalidGpaException ex =
      assertThrows(InvalidGpaException.class, () -> createStudentWithGpa(invalidGpa));
    assertThat(ex.getMessage(), containsString(String.valueOf(invalidGpa)));

  }

  @Test
  void saysIsNotImplementedYet() {
    Student student = createStudentWithGpa(4.0);
    assertThrows(UnsupportedOperationException.class, student::says);
  }

  private Student createStudentWithGpa(double gpa) {
    return new Student("Name", new ArrayList<>(), gpa, "other");
  }

  @Test
  void toStringContainsGpa() {
    double gpa = 3.45;
    Student student = createStudentWithGpa(gpa);
    assertThat(student.toString(), containsString(" has a gpa of " + gpa));
  }

  @Test
  void toStringContainsEnrolledClasses() {
    String class1 = "Operating Systems";
    String class2 = "Compilers";
    String class3 = "Algorithms";

    ArrayList<String> classes = new ArrayList<>();
    classes.add(class1);
    classes.add(class2);
    classes.add(class3);

    Student student = new Student("Name", classes, 3.45, "other");
    String toString = student.toString();

    assertThat(toString, StringContains.containsString(class1));
    assertThat(toString, StringContains.containsString(class2));
    assertThat(toString, StringContains.containsString(class3));
  }
}
