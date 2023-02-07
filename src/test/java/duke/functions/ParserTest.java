package duke.functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void handleInputTest() {
        String[] expected = {"test", "best", "gestalest"};

        try {
            assertArrayEquals(expected, Parser.handleInput("testabestagestalest", "a", 3, 3));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            assertArrayEquals(expected, Parser.handleInput("testabestagestalest", "a", 3, 2));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            assertArrayEquals(expected, Parser.handleInput("testabestagestalest", "a", 3, 4));
            fail();
        } catch (Exception e) {

        }
    }

    @Test
    public void handleCommandTest() {
        try {
            String[] input = {"bye"};

            assertEquals("Goodbye, feel free to call the Duke again whenever you need.", Parser.handleCommand(input, new ToDoListStub()));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            String[] input = {"list"};

            assertEquals("TO DO LIST:\n", Parser.handleCommand(input, new ToDoListStub()));
        } catch (Exception e) {
            fail();
        }

        try {
            String[] input = {"todo"};

            assertEquals("The Duke has add the following task:\n - \nYou now have 1 task!\n",
                    Parser.handleCommand(input, new ToDoListStub()));
            fail();
        } catch (Exception e) {

        }

        try {
            String[] input = {"error"};

            assertEquals(false, Parser.handleCommand(input, new ToDoListStub()));
            fail();
        } catch (Exception e) {

        }
    }
}
