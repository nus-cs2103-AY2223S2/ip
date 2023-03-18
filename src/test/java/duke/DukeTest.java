package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class DukeTest {
//set up
    Duke duke = new Duke("test.txt");

    @Test
    /**
     * Adds 4 tasks (to-do, deadline, event, do-after)  and then lists them to make sure that the list function is
     * working.
     */
    public void listTasks_NormalScenario() {
        duke.getResponse("todo buy bread");
        duke.getResponse("deadline complete " +
                "assignment /by 2023-03-01");
        duke.getResponse("event birthday" +
                " /from 25th June /to 25th June");
        duke.getResponse("doafter celebrate end of " +
                "mid terms /after Mid terms");
        assertEquals("These are the current tasks: \n" +
                "1): [T][ ] buy bread\n" +
                "2): [D][ ] complete assignment (by: Mar 01 2023)\n" +
                "3): [E][ ] birthday (from: 25th June to: 25th June)\n" +
                "4): [DA][ ] celebrate end of mid terms (after:Mid terms)\n", duke.getResponse("list"));
    }

    @Test
    /**
     * Tests if a to-do task can be successfully added.
     */
    public void addToDo() {
        assertEquals("I have added this new task:\n" + "[T][ ] buy bread\n"
                + "You now currently have 1 tasks.", duke.getResponse("todo buy bread"));
    }

    @Test
    /**
     * Tests if a deadline task can be successfully added.
     */
    public void addDeadline() {
        //add deadline task
        assertEquals("I have added this new task:\n" + "[D][ ] complete assignment (by: Mar 01 2023)\n"
                + "You now currently have 1 tasks.", duke.getResponse("deadline complete " +
                "assignment /by 2023-03-01"));
    }

    @Test
    /**
     * Tests if an event task can be successfully added.
     */
    public void addEvent() {
        //add event task
        assertEquals("I have added this new task:\n" + "[E][ ] birthday (from: 25th June to: 25th June)\n"
                + "You now currently have 1 tasks.", duke.getResponse("event birthday" +
                " /from 25th June /to 25th June"));
    }

    @Test
    /**
     * Tests if a do-after task can be successfully added.
     */
    public void addDoAfter() {
        assertEquals("I have added this new task:\n" + "[DA][ ] celebrate end of mid terms (after:Mid terms)\n"
                + "You now currently have 1 tasks.", duke.getResponse("doafter celebrate end of " +
                "mid terms /after Mid terms"));
    }
}

