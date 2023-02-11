package babe;

import babe.exception.NoArgumentException;
import babe.exception.NonsenseInputException;
import babe.exception.WrongDateFormatException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {

    @Test
    public void parseTest_byeInstruction()
            throws NonsenseInputException, NoArgumentException, WrongDateFormatException {
        ArrayList<String> parserOutput = Parser.parse("bye");
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("bye");
        assertEquals(expectedOutput, parserOutput);
    }

    @Test
    public void parseTest_nonsenseInput_NonsenseInputExceptionThrown() {
        assertThrows(NonsenseInputException.class, () -> { Parser.parse("gibberish"); });
    }
}
