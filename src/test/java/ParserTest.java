import duke.Storage;
import duke.Ui;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import duke.TaskList;
import duke.Parser;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    /**
     * test if task can be deleted through user input and command generated is correct
     * @throws FileNotFoundException when path name cannot be found
     */
    @Test
    public void deleteTask() throws FileNotFoundException {
        TaskList dummyList = new TaskList();
        Parser parser = new Parser();
        Storage dummyStorage = new Storage("data/duke.txt");
        Ui dummyUi = new Ui();

        dummyList.addTask(new Task("Item1","T"));
        dummyList.addTask(new Task("Item2","T"));
        dummyList.addTask(new Task("Item3","T"));
        dummyList.addTask(new Task("Item4","T"));

        Command c = parser.parse("delete 4", dummyList, dummyUi);
        assertEquals(true, c instanceof DeleteCommand);

        c.executeCommand(dummyList, dummyStorage, dummyUi);
        int actual = dummyList.getTasks().size();
        assertEquals(3,actual);

    }

    /**
     * test if task can be mark through user input and command generated is correct
     * @throws FileNotFoundException when path name cannot be found
     */
    @Test
    public void markTask() throws FileNotFoundException {
        TaskList dummyList = new TaskList();
        Parser parser = new Parser();
        Storage dummyStorage = new Storage("data/duke.txt");
        Ui dummyUi = new Ui();

        dummyList.addTask(new Task("Item1","T"));
        dummyList.addTask(new Task("Item2","T"));
        dummyList.addTask(new Task("Item3","T"));
        dummyList.addTask(new Task("Item4","T"));

        dummyUi.printText(dummyList.printList());
        Command c = parser.parse("mark 4", dummyList, dummyUi);
        assertEquals(false, c instanceof UnmarkCommand);
        assertEquals(true, c instanceof MarkCommand);

        c.executeCommand(dummyList, dummyStorage, dummyUi);
        boolean isMark = dummyList.getTasks().get(3).getComplete();

        assertEquals(true, isMark);
    }

}
