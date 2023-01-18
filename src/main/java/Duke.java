import com.sun.jdi.connect.IllegalConnectorArgumentsException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String LOGO = "______     ______     __     __    \n" +
            "/\\  __ \\   /\\  == \\   /\\ \\   /\\ \\   \n" +
            "\\ \\  __ \\  \\ \\  __<   \\ \\ \\  \\ \\ \\  \n" +
            " \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\  \\ \\_\\ \n" +
            "  \\/_/\\/_/   \\/_/ /_/   \\/_/   \\/_/ \n";

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds task to the current task list.
     * @param task Task to be created.
     */
    private void createTask(Task task) {
        tasks.add(task);
        System.out.println("Hey new task added!");
        System.out.println(task);
    }

    /**
     * Validate the presence of args after a command.
     * Does not precisely check validity of args for the command.
     * @param cmd User command.
     * @throws DukeException Exception thrown if no args were given.
     */
    private void validateNotEmptyArgs(String cmd) throws DukeException {
        if (cmd.split(" ").length <= 1) {
            throw new DukeException("You did not specify any details...");
        }
    }

    /**
     * Validate that index is within current set of tasks.
     * @param index Index of the task, 1-indexed.
     * @throws DukeException Exception thrown if index is invalid.
     */
    private void validateTaskIndex(Integer index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("No such task!");
        }
    }
    /**
     * Accepts a string that represents the user command, returns a boolean to
     * determine if the program should terminate immediately
     * @param cmd User command.
     * @return True if program should exit.
     */
    public boolean handleCommand(String cmd) throws DukeException {
        String firstCmd = cmd.split(" ")[0];
        int taskIndex;

        try {
            switch(DukeCommand.valueOf(firstCmd)) {
                case bye:
                    System.out.println("Till next time...");
                    return true;

                case todo:
                    validateNotEmptyArgs(cmd);
                    String description = cmd.substring(5);

                    createTask(new TodoTask(description));
                    break;

                case deadline:
                    validateNotEmptyArgs(cmd);
                    String deadlineArgs = cmd.substring(9);

                    // Validation of input
                    if (deadlineArgs.split(" /by ").length < 2) {
                        throw new DukeException("Insufficient details given...");
                    }

                    String deadlineDesc = deadlineArgs.split(" /by ")[0];
                    String deadlineBy = deadlineArgs.split(" /by ")[1];
                    createTask(new DeadlineTask(deadlineDesc, deadlineBy));
                    break;

                case event:
                    validateNotEmptyArgs(cmd);
                    String eventArgs = cmd.substring(6);

                    // Validation of input
                    if (eventArgs.split(" /from ").length < 2 || eventArgs.split(" /to ").length < 2) {
                        throw new DukeException("Insufficient details given...");
                    }

                    String eventDesc = eventArgs.split(" /from ")[0];
                    String eventFrom = eventArgs.split(" /from ")[1].split(" /to ")[0];
                    String eventBy = eventArgs.split(" /from ")[1].split(" /to ")[1];
                    createTask(new EventTask(eventDesc, eventFrom, eventBy));
                    break;

                case list:
                    System.out.println("Arii has retrieved your current tasks...");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                    }
                    break;

                case mark:
                    validateNotEmptyArgs(cmd);
                    taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                    validateTaskIndex(taskIndex);

                    tasks.get(taskIndex).setIsDone(true);

                    System.out.println("This task is now done, what's next?");
                    System.out.println(tasks.get(taskIndex));
                    break;

                case unmark:
                    validateNotEmptyArgs(cmd);
                    taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                    validateTaskIndex(taskIndex);

                    tasks.get(taskIndex).setIsDone(false);

                    System.out.println("This task is now not done, how disappointing...");
                    System.out.println(tasks.get(taskIndex));
                    break;

                case delete:
                    validateNotEmptyArgs(cmd);
                    taskIndex = Integer.parseInt(cmd.split(" ")[1]) - 1;
                    validateTaskIndex(taskIndex);

                    tasks.remove(taskIndex);

                    System.out.println("Task deleted. Are you skipping on work again?");
                    break;

                default:
                    throw new DukeException("Arii does not recognise this command...");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("That's not a number! Go count your numbers before trying again.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Arii does not recognise this command...");
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println("Hello, I am \n" + Duke.LOGO);
        System.out.println("How shall I assist you today?");

        boolean toExit = false;
        while (!toExit) {
            System.out.print("\n:> ");
            String cmd = scanner.nextLine();

            try {
                toExit = duke.handleCommand(cmd);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }
}
