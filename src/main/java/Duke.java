import driver.Driver;
import storage.Storage;
import tasks.TaskList;

public class Duke {

    private static final String BAR =
            "    ____________________________________________________________";

    private static final String INDENTATION = "     ";
    private static final String NEW_LINE = "\n";

    private static void greet() {
        System.out.println(BAR);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(BAR);
        System.out.print(NEW_LINE);
    }

    public static void main(String[] args) {
        greet();
        // Storage storage = new Storage();
        TaskList taskList = Storage.readTaskList();
        Driver driver = new Driver();
        driver.run(taskList);
    }
}
