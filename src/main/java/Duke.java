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
        init();
        while (isRunning) {
            execute(scanner.nextLine());
        }
        exit();
    }
    private void init() {
        scanner = new Scanner(System.in);
        isRunning = true;
        tasks = new TaskList();
        System.out.println("Hello!");
    }

    private void execute(String input) {
        try {
            Command command = new Command(input);
            switch (command.getName()) {
                case NO_OP:
                    break;
                case BYE:
                    isRunning = false;
                    break;
                case TODO:
                    addTask(new Todo(
                            command.getArgumentValue(Command.Argument.TODO)));
                    break;
                case DEADLINE:
                    addTask(new Deadline(
                            command.getArgumentValue(Command.Argument.DEADLINE),
                            command.getArgumentValue(Command.Argument.BY)));
                    break;
                case EVENT:
                    addTask(new Event(
                            command.getArgumentValue(Command.Argument.EVENT),
                            command.getArgumentValue(Command.Argument.FROM),
                            command.getArgumentValue(Command.Argument.TO)));
                    break;
                case LIST:
                    showTasks();
                    break;
                case MARK:
                    toggleTask(tasks.get(Integer.parseInt(
                            command.getArgumentValue(Command.Argument.MARK))));
                    break;
                case DELETE:
                    deleteTask(tasks.get(Integer.parseInt(
                            command.getArgumentValue(Command.Argument.DELETE))));
                    break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exit() {
        scanner.close();
        tasks.save();
        System.out.println("Good bye!");
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println("Added task.Task " + task);
    }

    private void showTasks() {
        int index = 1;
        for (Task task : tasks) {
           System.out.println(index + ". " + task);
           index += 1;
        }
    }

    private void toggleTask(Task task) {
        task.toggleDone();
        System.out.println("Marked task.Task " + task + " as " + (task.getIsDone() ? "" : "not ") + "done");
    }

    private void deleteTask(Task task) {
        tasks.remove(task);
        System.out.println("Deleted task.Task " + task);
    }
}
