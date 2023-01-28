package duke;
import java.util.Scanner;

/**
 * Takes charge of the output and interactions with user
 *
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    String readLine() {
        return sc.nextLine();
    }

    /**
     * Prints welcome message to user when user first starts
     * the program
     */
    void printWelcomeMessage() {
        System.out.println("Hi, I'm Nero and I am an automated chat bot"
                + "\n" + "What would you like to do?");
    }

    /**
     * Prints goodbye message when user exits
     * the program with "bye" command
     */
    void printGoodbyeMessage() {
        System.out.println("Goodbye! Hope to see you again XOXO");
    }

    void printTasksMessage() {
        System.out.println("Here are all your tasks!");
    }

    /**
     * Prints the completed task
     * @param string the toString() of the task that is marked
     * as done
     */
    void printMarkedTaskMessage(String string) {
        System.out.println("Good job on completing this task!" + "\n" + string);
    }

    /**
     * Prints the uncompleted task
     * @param string The toString() of the task that is marked
     * as undone
     */
    void printUnmarkedTaskMessage(String string) {
        System.out.println("Remember to complete this task!!" + "\n" + string);
    }

    /**
     * Prints the added task and total tasks in the list
     * @param string The toString() of the task added
     * @param size The current size of the taskList
     */
    void printAddedTasks(String string, int size) {
        System.out.println(String.format("Got it! I've added this task to the list!"
                + "\n" + "%s" + "\n" + "Now you have %d tasks in the list!"
                + "\n", string, size));
    }

    /**
     * Prints the deleted task and total tasks in the list
     * @param string The toString() of the task deleted
     * @param size The current size of the taskList
     */
    void printDeletedTasks(String string, int size) {
        System.out.println(String.format("Alright, let me remove this task..."
                + "\n" + "%s" + "\n" + "Now you have %d tasks in the list!"
                + "\n", string, size));
    }

    void printWrongInput() {
        System.out.println("Wrong input!!");
    }

    void printFileNotFound() {
        System.out.println("File not found :(((");
    }

}
