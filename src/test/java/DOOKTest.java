// package ;

import DukeHelpfulCode.Commands.Command;
import DukeHelpfulCode.Exceptions.NoSuchTaskException;
import DukeHelpfulCode.Exceptions.TaskAlrMarkException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import DukeHelpfulCode.Commands.AddCommandParser;
import DukeHelpfulCode.Commands.ErrorCommand;
import DukeHelpfulCode.Utilities.TaskList;

public class DOOKTest {

    AddCommandParser addCommandParser = new AddCommandParser();
    TaskList taskList = new TaskList();

    @Test
    public void addNoTaskName() {
        String noTaskName = "add todo";
        Command c = AddCommandParser.parse(noTaskName.split(" "));
        try {
            assertEquals(c.execute(taskList), "Sorry, no name for this task is given.\n");
        } catch (Exception e) {
            // do nothing, its not gonna throw
        }
    }

    @Test
    public void addEventNoDates() {
        String eventNoDate = "add event party ";
        Command c = AddCommandParser.parse(eventNoDate.split(" "));
        try {
            assertEquals(c.execute(taskList), "Sorry, I don't understand when this Event starts or ends.");
        } catch (Exception e) {
            // do nothing, its not gonna throw
        }
    }
}
