package userinteraction;

import storage.Storage;
import storage.TaskList;
import task.Task;

import java.util.Scanner;

/**
 * Ui that displays commands' message.
 */
public class Ui {
    private final String LOGO = "\t  ____        _        \n"
            + "\t |  _ \\ _   _| | _____ \n"
            + "\t | | | | | | | |/ / _ \\\n"
            + "\t | |_| | |_| |   <  __/\n"
            + "\t |____/ \\__,_|_|\\_\\___|\n";

    private final String BYE_MSG = "\t Bye. Hope to see you again soon!";

    private final Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Display the welcome message.
     *
     * @return Returns the welcome message.
     */
    public String printWelcomeMsg() {
        String str = LOGO + "\t Hello! I am Duke.\n"
                + "\t What can I do for you?\n";
        return str;
    }

    /**
     * Display adding task message.
     *
     * @param taskList Stores all tasks.
     * @param task The specific task needs to be displayed.
     * @return The add task message being displayed.
     */
    public String printAddTaskMsg(TaskList taskList, Task task) {
        String str = "\t Got it. I've added this task:\n  " + "\t\t "
                + task.toString() + "\n\t Now you have "
                + taskList.getSize() + " tasks in the list\n";
        return str;
    }

    /**
     * Display mark or unmark task message.
     *
     * @param isMarked Checks whether the task marked.
     * @param task The specific task needs to be displayed.
     * @return The mark or unmark message being displayed.
     */
    public String printMarkTaskMsg(boolean isMarked, Task task) {
        String str = "";
        if (isMarked) {
            str = "\t Nice! I've marked this task as done: \n"
                    + "\t\t " + task.toString() + "\n";
        } else {
            str = "\t OK, I've marked this task as not done yet: \n"
                    + "\t\t " + task.toString() + "\n";
        }
        return str;
    }

    /**
     * Display delete task message.
     *
     * @param task The specific task needs to be displayed.
     * @param size The size of the available tasks.
     * @return The delete task message being displayed.
     */
    public String printDeleteTaskMsg(Task task, int size) {
        String str = "\t Noted. I've removed this task:\n" + "\t\t "
                + task.toString() + "\n\t Now you have "
                + size + " tasks in the list.\n";
        return str;
    }

    /**
     * Display the find task message.
     *
     * @return The find task message being displayed.
     */
    public String printFindTaskMsg() {
        String str = "\t Here are the matching tasks in your list:";
        return str;
    }

    /**
     * Display the invalid command message.
     *
     * @return The invalid command message being displayed.
     */
    public String printWrongMsg() {
        String str = "\t ☹ OOPS!!! I'm sorry, but I don't know what that means.\n";
        return str;
    }

    /**
     * Display the explanation of the commands.
     *
     * @param storage Saves explanation in a file.
     * @return The explanation of all commands being displayed.
     */
    public String printHelpMsg(Storage storage) {
        return storage.loadHelpExplanationFile();
    }
    public String printByeMsg() {
        return BYE_MSG;
    }
}
