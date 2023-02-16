package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseTodo_normalInput_returnTodo() {
        String input = "todo homework";
        try {
            Task expected = new Todo("homework");
            Task actual = Parser.parseTodo(input);
            assertEquals(expected.description, actual.description);
        } catch (BobException e) {
            System.out.println("Error occurred!");
        }
    }
    @Test
    public void parseTodo_invalidInput_throwException() {
        String invalidInput = "todo";
        BobException e = assertThrows(BobException.class, () -> Parser.parseTodo(invalidInput));
        assertEquals("Error: Invalid todo command!", e.getMessage());
    }
}
