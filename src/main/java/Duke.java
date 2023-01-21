import command.Command;

import java.io.IOException;
import java.util.Scanner;

import task.Task;
import task.TaskList;

/**
 * Duke command line tool that helps to track tasks.
 */
public class Duke {
    /** Scanner used by each duke */
    private final Scanner scanner;
    /** Whether the duke is still running or has been commanded to end */
    private boolean isRunning;

    /** Task list */
    private final TaskList tasks;

    /**
     * Constructs a duke.
     */
    public Duke() {
        scanner = new Scanner(System.in);
        isRunning = false;
        System.out.println("Hello!");
        tasks = new TaskList("./tasks.txt");
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
        System.out.println("Awaiting commands...");
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
            System.out.println("Added task: " + tasks.execute(command));
            break;
        case LIST:
            System.out.println(tasks);
            break;
        case MARK:
            Task task = tasks.execute(command);
            System.out.println("Marked task: " + task + " as " + (task.getIsDone() ? "" : "not ") + "done");
            break;
        case DELETE:
            System.out.println("Deleted task: " + tasks.execute(command));
            break;
        }
    }

    private void exit() {
        scanner.close();
        try {
            tasks.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Good bye!");
    }

}
