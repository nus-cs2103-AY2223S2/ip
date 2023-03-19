package duke;

import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    TaskStorage taskStorage = new TaskStorage();
    Parser parser = new Parser(taskStorage);
    @Test
    public void byeTest() {
        assertEquals("Byeee! Hope to see you again! Signing off, duke.", parser.bye());
    }

    @Test
    public void todoTest() {
        String inp = "todo read book";
        String response = parser.todo(inp);
        String supposedResponse = "Got it. I've added this task:\n" +
                " [T][ ] read book\n" +
                "Now you have 1 task(s) in the list.\n";
        assertEquals(response, supposedResponse);
    }

    @Test
    public void deadlineTest() {
        String inp = "deadline return book /by 2019-06-15";
        String response = parser.deadline(inp);
        String supposedResponse = "Got it. I've added this task:\n" +
                " [D][ ] return book (by: Jun 15 2019)\n" +
                "Now you have 1 task(s) in the list.\n";
        assertEquals(response, supposedResponse);
    }


}
