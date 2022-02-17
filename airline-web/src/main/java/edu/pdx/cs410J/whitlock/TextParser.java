package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextParser {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }

  public Airline parse() throws ParserException {
    try (
      BufferedReader br = new BufferedReader(this.reader)
    ) {

      Airline airline = null;
      for (String line = br.readLine(); line != null; line = br.readLine()) {
        if (airline == null) {
          airline = new Airline(line);

        } else {
          airline.addFlight(new Flight(Integer.parseInt(line)));
        }
      }

      return airline;

    } catch (IOException e) {
      throw new ParserException("While parsing dictionary", e);
    }

  }
}
