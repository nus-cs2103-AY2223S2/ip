import command.Command;

import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Duke command line tool that helps to track tasks.
 */
public class Duke {
    /** Scanner used by each duke */
    private Scanner scanner;
    /** Whether the duke is still running or has been commanded to end */
    private boolean isRunning;

    /** Task list */
    private TaskList tasks;

    /**
     * Constructs a duke.
     */
    public Duke() {
        scanner = new Scanner(System.in);
        isRunning = false;
        tasks = new TaskList("/data/tasks");
    }

    /**
     * Entry point to start and run duke.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the duke.
     */
    public void run() {
        isRunning = true;
        System.out.println("Hello!");
        while (isRunning) {
            try {
                execute(new Command(scanner.nextLine()));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        exit();
    }

    private void execute(Command command) {
        switch (command.getName()) {
        case NO_OP:
            break;
        case BYE:
            isRunning = false;
            break;
        case TODO:
            // FallThrough
        case DEADLINE:
            // FallThrough
        case EVENT:
            System.out.println("Added task.Task " + tasks.execute(command));
            break;
        case LIST:
            System.out.println(tasks);
            break;
        case MARK:
            Task task = tasks.execute(command);
            System.out.println("Marked task.Task " + task + " as " + (task.getIsDone() ? "" : "not ") + "done");
            break;
        case DELETE:
            System.out.println("Deleted task.Task " + tasks.execute(command));
            break;
        }
    }

    private void exit() {
        scanner.close();
        tasks.save();
        System.out.println("Good bye!");
    }

}
