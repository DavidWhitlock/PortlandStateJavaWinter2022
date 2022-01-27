package edu.pdx.cs410J.whitlock;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;

public class CommandLineParser {
  public CommandLineParser(String[] args) {
  }

  public Student createStudent() {
    return null;
  }

  @VisibleForTesting
  static Student createStudentFromCommandLineArguments(String... args) {
    if (args.length < 3) {
      throw new Student.MissingCommandLineArgumentsException();
    }

    String name = args[0];
    String gender = args[1];
    String gpaString = args[2];

    double gpa;
    try {
      gpa = Double.parseDouble(gpaString);

    } catch (NumberFormatException ex) {
      throw new Student.InvalidGpaException(gpaString);
    }

    ArrayList<String> classes = new ArrayList<>();
    for (int i = 3; i < args.length; i++) {
      classes.add(args[i]);
    }

    return new Student(name, classes, gpa, gender);
  }

}
