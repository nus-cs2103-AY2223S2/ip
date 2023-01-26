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
            if (!Parser.isValidCommand(input)) {
                display.somethingWentWrong();
                break;
            }

            if (Parser.isTaskCommand(input)) {
                Task task = Parser.toTask(input);
                TaskList.addTask(task);
                display.line();
            } else if (input.equals("bye")) {
                display.farewell();
                break;
            } else if (input.equals("list")) {
                tasks.printTaskList();
                display.line();
            } else if (input.startsWith("mark")) {
                int taskNumber = Parser.indexToMark(input);
                tasks.taskMarkedAtIndex(--taskNumber);
                display.line();
            } else if (input.startsWith("unmark")) {
                int taskNumber = Parser.indexToUnmark(input);
                tasks.taskUnmarkedAtIndex(--taskNumber);
                display.line();
            } else if (input.startsWith("delete")) {
                int taskNumber = Parser.indexToDelete(input);
                tasks.deleteTaskAtIndex(--taskNumber);
                display.line();
            } else {
                display.somethingWentWrong(); // code should not run here
            }
            // Auto-save state in file
            store.autoSave(tasks);
        }
    }
}

