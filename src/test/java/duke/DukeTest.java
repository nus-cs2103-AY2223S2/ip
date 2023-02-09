package duke;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DukeTest {
    @Test
    public void addTodoTest() {
        Duke duke = new Duke();
        Message msg = new Message("todo return book");
        try {
            Message response = duke.respondToMessage(msg);
            assertEquals("""
                    Got it. I've added this task:
                    [T][ ] return book
                    Now you have 1 tasks in the list
                    """, response.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
