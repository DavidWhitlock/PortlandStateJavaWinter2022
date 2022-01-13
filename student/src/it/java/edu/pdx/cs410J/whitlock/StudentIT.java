package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static edu.pdx.cs410J.whitlock.Student.MISSING_COMMAND_LINE_ARGUMENTS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Integration tests for the <code>Student</code> class's main method.
 * These tests extend <code>InvokeMainTestCase</code> which allows them
 * to easily invoke the <code>main</code> method of <code>Student</code>.
 */
class StudentIT extends InvokeMainTestCase {

  @Test
  void invokingMainWithNoArgumentsHasExitCodeOf1() {
    InvokeMainTestCase.MainMethodResult result = invokeMain();
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void invokingMainWithNoArgumentsPrintsMissingArgumentsToStandardError() {
    InvokeMainTestCase.MainMethodResult result = invokeMain();
    assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
  }

  private MainMethodResult invokeMain(String... args) {
    return invokeMain(Student.class, args);
  }

  @Test
  @Disabled
  void daveStudentIsWrittenToStandardOutput() {
    InvokeMainTestCase.MainMethodResult result = invokeMain("Dave", "male", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getTextWrittenToStandardOut(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems, and Java. He says \"This class is too much work\"."));
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  void onlyProvidingNameIssueNotEnoughArgumentsError() {
    var result = invokeMain("Dave");
    assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void missingGpaIssueNotEnoughArgumentsError() {
    var result = invokeMain("Dave", "other");
    assertThat(result.getTextWrittenToStandardError(), containsString(MISSING_COMMAND_LINE_ARGUMENTS));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void invalidNumberIsNotAValidGpa() {
    String invalidGpa = "foo";
    var result = invokeMain("Dave", "other", invalidGpa);
    String error = result.getTextWrittenToStandardError();

    assertThat(error, containsString(invalidGpa));
    assertThat(error, containsString(" is an invalid gpa"));
    assertThat(result.getExitCode(), equalTo(1));

  }
}
