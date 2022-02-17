package edu.pdx.cs410J.whitlock;

import java.io.PrintWriter;
import java.io.Writer;

public class TextDumper {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  public void dump(Airline airline) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ) {
      pw.println(airline.getName());

      airline.getFlights().forEach(flight -> {
          pw.println(flight.getNumber());
        }
      );

      pw.flush();
    }
  }
}
