package elems;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dukeexceptions.IllegalCommandException;

public class ParserTest {
    @Test
    public void invalidKeywordTest() {
        Parser parser = new Parser();
        String testInput = "what 1 2 3";

        try {
            parser.parse(testInput);
        } catch (IllegalCommandException e) {
            assertEquals("You have entered an invalid command", e.getMessage());
        }

    }
}
