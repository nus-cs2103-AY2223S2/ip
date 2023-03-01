package duke;

import java.util.Scanner;

/**
 * The type Ui.
 */
public class Ui {

    /**
     * Shows welcome.
     */
    public static void showWelcome() {
        showLine();
        System.out.println(" Hello! I'm Duke\n" + " What can I do for you?");
        showLine();
    }

    /**
     * Shows line.
     */
    public static void showLine() {
        System.out.println("\n____________________________________________________________\n");
    }

    /**
     * Shows error message.
     *
     * @param errorMessage the error message
     */
    public static String showError(String errorMessage) {
        return "ahhhhh!!! " + errorMessage;
    }

    /**
     * Reads user input and returns the corresponding command string.
     *
     * @return the string
     */
    public static String readCommand() {
        Scanner userInput = new Scanner(System.in);
        String newInput = userInput.nextLine();
        return newInput;
    }

    /**
     * Shows loading error.
     */
    public static void showLoadingError() {
        showError("an error occurred while loading file from given filepath (⊙.⊙(☉̃ₒ☉)⊙.⊙)");
    }

    /**
     * Shows added task.
     *
     * @param newTaskString    the new task
     * @param numOfTasks the num of tasks
     */
    public static String showTask(String newTaskString, int numOfTasks, String newList) {
        return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list.\n\n%s",
                newTaskString, numOfTasks, newList);
    }

    /**
     * Says goodbye.
     */
    public static String sayGoodbye() {
        return "byeeee. hope to see you again soon! (ɔ◔‿◔)ɔ ♥";
    }

    /**
     * Shows action done by Duke.
     *
     * @param action the action
     */
    public static void showAction(String action) {
        System.out.println(action);
    }

}
