package aqua.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;

public class ArgumentParserTest {
    private static ArgumentMap expected;


    @BeforeAll
    public static void initialiseExpected() {
        HashMap<String, String> map = new HashMap<>();
        map.put(ArgumentMap.TAG_MAIN_INPUT, "1234");
        map.put("tag1", "1234");
        expected = new ArgumentMap(map);
    }


    @Test
    public void parse_validInput_argumentParsed() throws SyntaxException {
        ArgumentParser parser = new ArgumentParser();
        String input = "1234 / tag1 1234";
        ArgumentMap actual = parser.parse(input);
        assertEquals(expected.getMainInput().get(), actual.getMainInput().get());
        assertEquals(expected.get("tag1").get(), actual.get("tag1").get());
    }


    @Test
    public void parse_invalidInput_exceptionThrown() {
        ArgumentParser parser = new ArgumentParser();
        String input = "1234 /";
        try {
            parser.parse(input);
        } catch (SyntaxException synEx) {
            return;
        }
        fail("Exception not thrown");
    }
}
