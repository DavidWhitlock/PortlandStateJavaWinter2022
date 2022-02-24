package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.UncaughtExceptionInMain;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.MethodOrderer.MethodName;

/**
 * An integration test for {@link Project5} that invokes its main method with
 * various arguments
 */
@TestMethodOrder(MethodName.class)
class Project5IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    void test0RemoveAllAirlines() throws IOException {
      AirlineRestClient client = new AirlineRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllAirlines();
    }

    @Test
    void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project5.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project5.MISSING_ARGS));
    }

    @Test
    void test3NoAirlineThrowsRestException() {
        String word = "WORD";
        try {
            invokeMain(Project5.class, HOSTNAME, PORT, word);
            fail("Should have thrown a RestException");

        } catch (UncaughtExceptionInMain ex) {
            RestException cause = (RestException) ex.getCause();
            assertThat(cause.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_NOT_FOUND));
        }
    }

    @Test
    void test4AddFlight() {
        String airlineName = "WORD";
        int flightNumber = 12345;

        MainMethodResult result = invokeMain( Project5.class, HOSTNAME, PORT, airlineName, String.valueOf(flightNumber));
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));

        result = invokeMain( Project5.class, HOSTNAME, PORT, airlineName );
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(PrettyPrinter.formatFlightNumber(flightNumber)));
    }

    @Test
    void readmeOptionPrintsREADME() {
        MainMethodResult result = invokeMain(Project5.class, "-README");
        assertThat(result.getTextWrittenToStandardOut(), containsString("This is a README file!"));
    }
}