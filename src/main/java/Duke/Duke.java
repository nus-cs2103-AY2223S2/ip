package Duke;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import Duke.Exceptions.*;
import Duke.Tasks.Disk;
import Duke.Tasks.Monitor;
import Duke.Tasks.Task;
import Duke.Tasks.TaskTable;


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

        // coc.start();

        boolean running = true;
        while (running) {
            try {
                String command = monitor.getCommand();
                Task newTask = Interpreter.interpret(command, table);
                newTask.run(table, monitor, disk);
                running = !newTask.exited; // if newTask exits stop running
            } catch (InvalidCommandException | InvalidTimeFormatException |
                     MissingDescriptionException | OutRangeException | NullPointerException e) {
                continue;
            }
        }
        monitor.bye();
    }

    /**
     * The main method
     * @param args the args
     * @throws InvalidTimeFormatException
     * @throws InvalidCommandException
     * @throws MissingDescriptionException
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt");
    }

    public String getResponse(String input) {
        String message;
        try {
            Task t = Interpreter.interpret(input, table);
            message = t.run(table, monitor, disk);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
            message = "    ____________________________________________________________\n" +
                    e.getMessage() +
                    "\n    ____________________________________________________________\n";
        }
        return message;
    }
}
