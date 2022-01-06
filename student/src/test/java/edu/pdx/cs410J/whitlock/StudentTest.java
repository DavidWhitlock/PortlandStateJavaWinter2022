package edu.pdx.cs410J.whitlock;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

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

    try {
      createStudentNamed(tooLongName);
      fail("Should have thrown an IllegalArgumentException");

    } catch (IllegalArgumentException ex) {
      // pass.
    }
  }

  private Student createStudentNamed(String studentName) {
    return new Student(studentName, new ArrayList<>(), 0.0, "Doesn't matter");
  }

}
