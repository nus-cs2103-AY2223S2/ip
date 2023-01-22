import Exceptions.EmptyCommandException;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidTimeFormatException;
import Exceptions.MissingDescriptionException;
import Tasks.*;
import java.io.IOException;

/**
 * @author Sun Yitong
 * @version 1.0
 */

/**
 * Represents class of Duke
 */
public class Duke {

    private Monitor monitor;
    private TaskTable table;

    /**
     * Constructor of Duke
     * @throws EmptyCommandException
     * @throws InvalidTimeFormatException
     * @throws InvalidCommandException
     * @throws MissingDescriptionException
     */
    public Duke(String path) throws EmptyCommandException, InvalidTimeFormatException, InvalidCommandException, MissingDescriptionException {
        Monitor monitor = new Monitor();
        Disk disk = new Disk(path);

        monitor.displayLogo();
        monitor.welcome();

        try {
            table = new TaskTable(disk.read());
        } catch (IOException e) {
            monitor.displayLoadingError();
            table = new TaskTable();
        }

        boolean running = true;
        while (running) {
            try {
                String command = monitor.getCommand();
                Task newTask = Interpreter.interpret(command, table);
                newTask.run(table, monitor, disk);
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
     * @param args the args
     * @throws EmptyCommandException
     * @throws InvalidTimeFormatException
     * @throws InvalidCommandException
     * @throws MissingDescriptionException
     */
    public static void main(String[] args) throws EmptyCommandException, InvalidTimeFormatException, InvalidCommandException, MissingDescriptionException {
            new Duke("data/tasks.txt");
    }

}
