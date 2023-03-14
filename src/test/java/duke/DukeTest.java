package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class DukeTest {
//set up
    Duke duke = new Duke("test.txt");

    @Test
    public void testList() {
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
    public void testToDo() {
        assertEquals("I have added this new task:\n" + "[T][ ] buy bread\n"
                + "You now currently have 1 tasks.", duke.getResponse("todo buy bread"));
    }

    @Test
    public void testDeadline() {
        //add deadline task
        assertEquals("I have added this new task:\n" + "[D][ ] complete assignment (by: Mar 01 2023)\n"
                + "You now currently have 1 tasks.", duke.getResponse("deadline complete " +
                "assignment /by 2023-03-01"));
    }

    @Test
    public void testEvent() {
        //add event task
        assertEquals("I have added this new task:\n" + "[E][ ] birthday (from: 25th June to: 25th June)\n"
                + "You now currently have 1 tasks.", duke.getResponse("event birthday" +
                " /from 25th June /to 25th June"));
    }

    @Test
    public void testDoAfter() {
        assertEquals("I have added this new task:\n" + "[DA][ ] celebrate end of mid terms (after:Mid terms)\n"
                + "You now currently have 1 tasks.", duke.getResponse("doafter celebrate end of " +
                "mid terms /after Mid terms"));
    }

    @Test
    public void testGoodByeMessage() {
        assertEquals("Goodbye! :)\n", duke.getResponse("bye"));
    }

}

