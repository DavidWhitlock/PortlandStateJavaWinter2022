package edu.pdx.cs410J.whitlock;

public class TextDumperParserTest {

//  @Test
//  void emptyMapCanBeDumpedAndParsed() throws ParserException {
//    Map<String, String> map = Collections.emptyMap();
//    Map<String, String> read = dumpAndParse(map);
//    assertThat(read, equalTo(map));
//  }
//
//  private Map<String, String> dumpAndParse(Map<String, String> map) throws ParserException {
//    StringWriter sw = new StringWriter();
//    TextDumper dumper = new TextDumper(sw);
//    dumper.dump(map);
//
//    String text = sw.toString();
//
//    TextParser parser = new TextParser(new StringReader(text));
//    return parser.parse();
//  }
//
//  @Test
//  void dumpedTextCanBeParsed() throws ParserException {
//    Map<String, String> map = Map.of("one", "1", "two", "2");
//    Map<String, String> read = dumpAndParse(map);
//    assertThat(read, equalTo(map));
//  }
}
