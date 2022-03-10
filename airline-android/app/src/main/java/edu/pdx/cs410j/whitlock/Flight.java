package edu.pdx.cs410j.whitlock;

import edu.pdx.cs410J.AbstractFlight;

public class Flight extends AbstractFlight {
  private final int flightNumber;

  public Flight(int flightNumber) {
    this.flightNumber = flightNumber;
  }

  @Override
  public int getNumber() {
    return this.flightNumber;
  }

  @Override
  public String getSource() {
    return "PDX";
  }

  @Override
  public String getDepartureString() {
    return "Now";
  }

  @Override
  public String getDestination() {
    return "LAS";
  }

  @Override
  public String getArrivalString() {
    return "Later";
  }
}
