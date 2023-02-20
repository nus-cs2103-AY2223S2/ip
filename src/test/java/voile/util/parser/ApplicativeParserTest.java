package voile.util.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import voile.util.container.Pair;

public class ApplicativeParserTest {
    private static final Class<ParserException> EXPECTED_EXCEPTION_CLASS =
            ParserException.class;

    @Test
    public void parseString_validInput_shouldSucceed() {
        String input = "Hidden Seasons will pass you by";
        ApplicativeParser<String> parser = ApplicativeParser.parseString(input);
        Pair<String, String> result = parser.parse(input);
        assertEquals("", result.getFirst());
        assertEquals(input, result.getSecond());
    }

    @Test
    public void parseString_invalidInput_shouldThrow() {
        String input = "Witching Dream Battle";
        ApplicativeParser<String> parser = ApplicativeParser.parseString("Maiden's Capriccio");
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parser.parse(input));
    }

    @Test
    public void parseUntil_validInput_shouldSucceed() {
        String input = "Red, White and Black Butterfly";
        ApplicativeParser<String> parser = ApplicativeParser.parseUntil("Butterfly");
        Pair<String, String> result = parser.parse(input);
        assertEquals("", result.getFirst());
        assertEquals("Red, White and Black ", result.getSecond());
    }

    @Test
    public void parseUntil_invalidInput_shouldThrow() {
        String input = "Dichromatic Lotus Butterfly";
        ApplicativeParser<String> parser = ApplicativeParser.parseUntil("Red and White");
        assertThrows(EXPECTED_EXCEPTION_CLASS, () -> parser.parse(input));
    }

    @Test
    public void shouldLiftCorrectly() {
        String input = "The Rabbit Has Landed";
        ApplicativeParser<String> leftParser = ApplicativeParser
                .parseString("The Rabbit")
                .dropNext(ApplicativeParser.skipWhitespaces());
        ApplicativeParser<String> rightParser = ApplicativeParser.parseString("Has Landed");
        ApplicativeParser<String> parser = ApplicativeParser.lift(
                (left, right) -> String.join(" ", left, right),
                leftParser,
                rightParser);
        Pair<String, String> result = parser.parse(input);
        assertEquals("", result.getFirst());
        assertEquals(input, result.getSecond());
    }

    @Test
    public void shouldMapCorrectly() {
        String input = "Legacy of Lunatic Kingdom";
        ApplicativeParser<String> parser = ApplicativeParser
                .parseString(input)
                .map(result -> result + " (LoLK)");
        Pair<String, String> result = parser.parse(input);
        assertEquals("", result.getFirst());
        assertEquals(input + " (LoLK)", result.getSecond());
    }

    @Test
    public void shouldFlatMapCorrectly() {
        String input = "Tonight Stars an Easygoing Egoist";
        ApplicativeParser<String> parser = ApplicativeParser
                .parseString("Tonight Stars")
                .flatMap(left -> ApplicativeParser
                        .skipWhitespaces()
                        .takeNext(ApplicativeParser.parseString("an"))
                        .flatMap(middle -> ApplicativeParser
                                .skipWhitespaces()
                                .takeNext(ApplicativeParser.parseString("Easygoing Egoist"))
                                .flatMap(right -> ApplicativeParser
                                        .of(String.join(" ", left, middle, right)))));
        Pair<String, String> result = parser.parse(input);
        assertEquals("", result.getFirst());
        assertEquals(input, result.getSecond());
    }

    @Test
    public void of_anyInput_shouldNotConsume() {
        String input = "The Girl's Secret Room";
        String output = "Locked Girl";
        ApplicativeParser<String> parser = ApplicativeParser.of(output);
        Pair<String, String> result = parser.parse(input);
        assertEquals(input, result.getFirst());
        assertEquals(output, result.getSecond());
    }
}
