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

        Command c = parser.commandExecute("delete 4", dummyList, dummyStorage);
        assertEquals(true, c instanceof DeleteCommand);

        c.executeCommand(dummyList, dummyStorage, dummyUi);
        int actual = dummyList.getTasks().size();
        assertEquals(3,actual);

    }

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

        Command c = parser.commandExecute("mark 4", dummyList, dummyStorage);
        assertEquals(false, c instanceof UnmarkCommand);
        assertEquals(true, c instanceof MarkCommand);

        c.executeCommand(dummyList, dummyStorage, dummyUi);
        boolean isMark = dummyList.getTasks().get(3).getStatus();

        assertEquals(true, isMark);
    }

}
