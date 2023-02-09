package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void taskDescriptionSuccess() throws EmptyDescription {
        boolean isNotEmpty = Parser.checkEmptyDescription(new String[]{"todo", "borrow", "book"});
        assertTrue(isNotEmpty);
    }

    @Test
    public void taskDescriptionException() {
        EmptyDescription exception = assertThrows(EmptyDescription.class,
                () -> Parser.checkEmptyDescription(new String[]{"todo"}));
        assertEquals("OOPS!!! The description of todo cannot be empty.", exception.toString());
    }
}
