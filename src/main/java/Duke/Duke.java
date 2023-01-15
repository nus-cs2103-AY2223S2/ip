package Duke;

import Duke.Exceptions.EmptyCommandException;
import Duke.Exceptions.InvalidCommandException;
import Duke.Exceptions.InvalidTimeFormatException;
import Duke.Exceptions.MissingDescriptionException;
import Duke.Tasks.Task;

/**
 * @author Sun Yitong
 * @version 1.0
 */

/**
 * Main class of Duke
 * main method
 */
public class Duke {

    private Monitor monitor;
    private TaskTable table;

    /**
     * Constructor of Duke
     */
    public Duke() throws EmptyCommandException, InvalidTimeFormatException, InvalidCommandException, MissingDescriptionException {
        Monitor monitor = new Monitor();

        monitor.displayLogo();
        monitor.welcome();

        table = new TaskTable();
        boolean running = true;
        while (running) {
            try {
                String command = monitor.getCommand();
                Task newTask = Interpreter.interpret(command, table);
                newTask.run(table, monitor);
                running = !newTask.exited; // if newTask exits stop running
            } catch (NullPointerException | InvalidTimeFormatException | MissingDescriptionException |
                     EmptyCommandException | InvalidCommandException e) {
                continue;
            }
        }
        monitor.bye();


    }

    public void execute() {

        monitor.displayLogo();
        monitor.welcome();

        table = new TaskTable();
        boolean running = true;
        while (running) {
            try {
                String command = monitor.getCommand();
                Task newTask = Interpreter.interpret(command, table);
                newTask.run(table, monitor);
                running = !newTask.exited; // if newTask exits stop running
            } catch (NullPointerException | InvalidTimeFormatException | MissingDescriptionException |
                     EmptyCommandException | InvalidCommandException e) {
                continue;
            }
        }
        monitor.bye();

    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) throws EmptyCommandException, InvalidTimeFormatException, InvalidCommandException, MissingDescriptionException {
            new Duke();
    }

}
