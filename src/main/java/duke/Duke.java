package duke;

import java.util.Scanner;

/**
 * Class that defines the Duke task list manager
 */
public class Duke {

    /** Array of tasks */
    private static TaskList taskList = new TaskList();
    /** Number of tasks in list */
    private static int listIndex = 0;

    /**
     * Adds a task to the taskList.
     *
     * @param listItem Task to be added to taskList
     */
    public static void addToList(Task listItem) {
        taskList.addTask(listItem);
        listIndex++;
        System.out.println("> Duke's response:");
        System.out.println("I've added the following task to your list:");
        System.out.println(listItem.toString());
        System.out.println("Current tasks count: " + (listIndex));
        System.out.println("--------------------------------\n");
    }

    /**
     * Removes the task at a specified position in taskList
     *
     * @param pos Position in taskList at which the task to be removed is stored
     */
    public static void removeFromList(int pos) {
        Task curr = taskList.deleteTask(pos - 1);
        listIndex--;
        System.out.println("> Duke's response:");
        System.out.println("I've removed the following task from your list:");
        System.out.println(curr.toString());
        System.out.println("Current tasks count: " + (listIndex));
        System.out.println("--------------------------------\n");
    }

    /**
     * Prints the contents of the taskList
     */
    public static void displayList() {
        int pos = 0;
        System.out.println("Here are the tasks in your list:");
        while (pos < taskList.size()) {
            System.out.println((pos + 1) + ". " + taskList.getTask(pos).toString());
            pos++;
        }
        System.out.println("End of list!\n");
    }

    /**
     * Throws an exception
     *
     * @param exceptionType Describes the type of DukeException to be thrown
     * @throws DukeException If input is invalid
     */
    public static void throwException(String exceptionType) throws DukeException {
        throw new DukeException(exceptionType);
    }

    /**
     * Greets user, awaits user input and updates/displays taskList accordingly.
     *
     * @param args Commands from user, to interact with taskList
     */
    public static void main(String[] args) {
        Storage fileManager = new Storage();
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);

        taskList = fileManager.read();
        listIndex = taskList.size();
        Ui.greet();

        String userInput =  sc.nextLine();
        String exitCommand = "bye";

        while (!userInput.equals(exitCommand)) {
            parser.parse(userInput, taskList, fileManager);
            userInput = sc.nextLine();
        }
        Ui.exit();

    }
}
