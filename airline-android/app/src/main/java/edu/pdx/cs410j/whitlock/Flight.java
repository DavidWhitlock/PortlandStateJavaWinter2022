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
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDepartureString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDestination() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getArrivalString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}
