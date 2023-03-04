package duke;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.action.Task;
import duke.action.Todo;
import duke.command.Command;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Unit testing class for command testing
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class CommandTest {

    /**
     * Test if input given matches output when user enters "find" command
     * @throws DukeException when there are no similar tasks
     */
    @Test
    void testFindCommand() throws DukeException {
        String fakeInput = "find Foo";
        Task tempTask1 = new Todo("Foobar");
        Task tempTask2 = new Todo("Foo Foo Foo");
        Task tempTask3 = new Todo("Foooooooohuhuhuh");
        Task tempTask4 = new Todo("Bar");
        Task tempTask5 = new Todo("BarFooooo");
        List<Task> testList = new ArrayList<Task>();
        testList.add(tempTask1);
        testList.add(tempTask2);
        testList.add(tempTask3);
        testList.add(tempTask4);
        testList.add(tempTask5);
        Command command = new Parser().parse(fakeInput);
        Ui tempUi = new Ui();
        Storage fakeStorage = new Storage();
        TaskList tempTaskList = new TaskList(testList);
        command.execute(tempTaskList, tempUi, fakeStorage);
    }
}
