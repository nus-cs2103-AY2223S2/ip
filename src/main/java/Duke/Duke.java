package duke;

import java.io.IOException;

import duke.Exceptions.*;
import duke.Tasks.Disk;
import duke.Tasks.Monitor;
import duke.Tasks.Task;
import duke.Tasks.TaskTable;


/**
 * Represents class of Duke.Duke
 */
public class Duke {

    private Monitor monitor;
    private TaskTable table;
    private Disk disk;


    /**
     * Constructor of Duke.Duke
     * @throws InvalidTimeFormatException
     * @throws InvalidCommandException
     * @throws MissingDescriptionException
     */
    public Duke(String path) {
        monitor = new Monitor();
        disk = new Disk(path);

        try {
            table = new TaskTable(disk.read());
        } catch (IOException e) {
            monitor.displayLoadingError();
            table = new TaskTable();
        }
    }

    public void execute() {

        monitor.displayLogo();
        monitor.welcome();

        boolean isRunning = true;
        while (isRunning) {
            try {
                String command = monitor.getCommand();
                Task newTask = Interpreter.interpret(command, table);
                newTask.run(table, monitor, disk);
                isRunning = !newTask.isExited; // if newTask exits stop isRunning
            } catch (InvalidCommandException | InvalidTimeFormatException |
                     MissingDescriptionException | OutRangeException | NullPointerException | DuplicateException e) {
                continue;
            }
        }
        monitor.bye();
    }

    /**
     * The main method
     * @param args the args
     */
    public static void main(String[] args) {
        Duke d = new Duke("data/tasks.txt");
        d.execute();

    }

    public String getResponse(String input) {
        String message;
        try {
            Task t = Interpreter.interpret(input, table);
            message = t.run(table, monitor, disk);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            message = "    ____________________________________________________________\n" +
                    e.getMessage() +
                    "\n    ____________________________________________________________\n";
        }
        return message;
    }
}
