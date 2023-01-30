package alfred.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class AddCommandTest {
    private final AddCommand addCommand = new AddCommand("deadline return book");
    @Test
    public void isExit() {
        assertFalse(addCommand.isExit());
    }
}
