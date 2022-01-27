package edu.pdx.cs410J.whitlock;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;

public class CommandLineParser {
  private final String name;
  private final String gender;
  private final String gpa;
  private final ArrayList<String> classes = new ArrayList<>();

  public CommandLineParser(String... args) {
    if (args.length < 3) {
      throw new Student.MissingCommandLineArgumentsException();
    }

    this.name = args[0];
    this.gender = args[1];
    this.gpa = args[2];

    for (int i = 3; i < args.length; i++) {
      this.classes.add(args[i]);
    }
  }

  public Student createStudent() {
    return new Student(name, classes, parseGpa(), gender);
  }

  protected double parseGpa() {
    double gpa;
    try {
      gpa = Double.parseDouble(this.gpa);

    } catch (NumberFormatException ex) {
      throw new Student.InvalidGpaException(this.gpa);
    }
    return gpa;
  }

}
