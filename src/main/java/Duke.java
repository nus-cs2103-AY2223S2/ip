/**
 * File name: Duke.java
 * @author: Jerome Neo
 * Description: The main class for the Duke application.
 */
import duke.task.Task;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static Storage store = new Storage();
    private static Ui display = new Ui();
    private static Parser logic = new Parser();
    private static TaskList tasks = new TaskList();

    public static void main(String[] args) throws IOException, DukeException {
        display.greeting();
        Scanner scanner = new Scanner(System.in);
        String input;
        while(true) {
            input = scanner.nextLine();
            if (!logic.isValidCommand(input)) {
                display.somethingWentWrong();
                break;
            }

            if (logic.isTaskCommand(input)) {
                Task task = logic.toTask(input);
                TaskList.addTask(task);
                display.line();
            } else if (input.equals("bye")) {
                display.farewell();
                break;
            } else if (input.equals("list")) {
                tasks.printTaskList();
                display.line();
            } else if (input.startsWith("mark")) {
                int taskNumber = logic.indexToMark(input);
                tasks.taskMarkedAtIndex(--taskNumber);
                display.line();
            } else if (input.startsWith("unmark")) {
                int taskNumber = logic.indexToUnmark(input);
                tasks.taskUnmarkedAtIndex(--taskNumber);
                display.line();
            } else if (input.startsWith("delete")) {
                int taskNumber = logic.indexToDelete(input);
                tasks.deleteTaskAtIndex(--taskNumber);
                display.line();
            } else {
                String description = logic.commandToDescription(input); // find command
                display.announceFindResult();
                tasks.matchDescription(description);
                display.line();
            }
            // Auto-save state in file
            store.autoSave(tasks);
        }
    }
}

