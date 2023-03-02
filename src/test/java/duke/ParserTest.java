package duke;

import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void byeTest() {
        String byeString = "Byeee! Hope to see you again! Signing off, duke.";
        assertEquals("Byeee! Hope to see you again! Signing off, duke.", byeString);
    }


}
