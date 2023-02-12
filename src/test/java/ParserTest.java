import duke.MainWindow;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Parser class
 */
public class ParserTest {

    /**
     * Tests whether a todo task can be mark.
     */
    @Test
    public void markTodoTask() {
        Storage storage = new Storage();
        MainWindow mainWindow = new MainWindow();
        TaskList taskList = new TaskList(storage.loadFile(), mainWindow);
        Parser parser = new Parser(mainWindow);
        try {
            parser.runCommand(taskList, storage, ("Todo Clean house").split(" "));
            String command = "mark " + taskList.getListOfTask().size();
            parser.runCommand(taskList, storage, command.split(" "));
            Task lastTask = taskList.getListOfTask().get(taskList.getListOfTask().size() - 1);
            assertEquals(lastTask.isDone(),true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Tests whether a task can be unmark.
     */
    @Test
    public void unmarkTask() {
        Storage storage = new Storage();
        MainWindow mainWindow = new MainWindow();
        TaskList taskList = new TaskList(storage.loadFile(),mainWindow);
        Parser parser = new Parser(mainWindow);
        try {
            String command = "unmark " + taskList.getListOfTask().size();
            parser.runCommand(taskList, storage, command.split(" "));
            Task lastTask = taskList.getListOfTask().get(taskList.getListOfTask().size() - 1);
            assertEquals(lastTask.isDone(),false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Tests whether a task can be deleted.
     */
    @Test
    public void deleteTask() {
        MainWindow mainWindow = new MainWindow();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.loadFile(),mainWindow);
        Parser parser = new Parser(mainWindow);
        try {
            int originalLen = taskList.getListOfTask().size();
            String command = "delete " + taskList.getListOfTask().size();
            parser.runCommand(taskList, storage, command.split(" "));
            assertEquals(originalLen - 1,taskList.getListOfTask().size());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
