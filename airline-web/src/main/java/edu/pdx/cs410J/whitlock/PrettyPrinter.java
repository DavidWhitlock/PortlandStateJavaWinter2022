package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AirlineDumper;

import java.io.PrintWriter;
import java.io.Writer;

public class PrettyPrinter implements AirlineDumper<Airline> {
  private final Writer writer;

  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }

  public static String formatFlightNumber(int flightNumber) {
    return "Number: " + flightNumber;
  }

  @Override
  public void dump(Airline airline) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ) {

      pw.println(airline.getName());

      airline.getFlights().forEach(flight -> {
        pw.println(formatFlightNumber(flight.getNumber()));
      });

      pw.flush();
    }

  }
}
