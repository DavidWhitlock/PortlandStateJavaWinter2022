package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class AirlineRestClient extends HttpRequestHelper
{
    private static final String WEB_APP = "airline";
    private static final String SERVLET = "flights";

    private final String url;


    /**
     * Creates a client to the airline REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public AirlineRestClient( String hostName, int port )
    {
        this.url = String.format( "http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET );
    }

  /**
   * Returns all dictionary entries from the server
   */
  public Map<String, String> getAllDictionaryEntries() throws IOException, ParserException {
    throw new UnsupportedOperationException("Delete me!");
  }

  /**
   * Returns the definition for the given airline
   * @return
   */
  public Airline getAirline(String airlineName) throws IOException, ParserException {
    Response response = get(this.url, Map.of("airline", airlineName));
    throwExceptionIfNotOkayHttpStatus(response);
    String content = response.getContent();

    TextParser parser = new TextParser(new StringReader(content));
    return parser.parse();
  }

  public void addFlight(String airlineName, Flight flight) throws IOException {
    Response response = post(this.url, Map.of("airline", airlineName, "flightNumber", String.valueOf(flight.getNumber())));
    throwExceptionIfNotOkayHttpStatus(response);
  }

  public void removeAllAirlines() throws IOException {
    Response response = delete(this.url, Map.of());
    throwExceptionIfNotOkayHttpStatus(response);
  }

  private void throwExceptionIfNotOkayHttpStatus(Response response) {
    int code = response.getCode();
    if (code != HTTP_OK) {
      String message = response.getContent();
      throw new RestException(code, message);
    }
  }

}
