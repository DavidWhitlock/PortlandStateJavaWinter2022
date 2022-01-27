package edu.pdx.cs410J.whitlock;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandLineParserTest {

  @Test
  void createStudentFromCommandLineWithNonNumericGpaThrowsInvalidGpaException() {
    String invalidGpa = "NOT A DOUBLE";
    Student.InvalidGpaException ex = assertThrows(Student.InvalidGpaException.class,
      () -> new CommandLineParser("name", "other", invalidGpa).createStudent());
    assertThat(ex.getInvalidGpa(), equalTo(invalidGpa));
  }

  @Test
  void createStudentFromCommandLineWithZeroArgumentsThrowsMissingCommandLineArgumentsException() {
    assertThrows(Student.MissingCommandLineArgumentsException.class, CommandLineParser::new);
  }

  @Test
  void createStudentFromCommandLineSetsGender() {
    String gender = "female";
    Student student = new CommandLineParser("name", gender, "3.45").createStudent();
    assertThat(student.getGenderPronoun(), equalTo(Student.FEMALE_PRONOUN));
  }


}
