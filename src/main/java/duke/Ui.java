package duke;
import java.util.Scanner;
import duke.task.Task;
public class Ui {
    public Ui() {

    }

    /**
     * Prints out the greeting when the program first runs.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Meow I'm Toto! What can I for you?");
    }

    /**
     * Prints out exit note after the user inputs "bye".
     */
    public void goodbye() {
        System.out.println("Byebye CATch you later!");
    }

    /**
     * Prints out note after a task is marked by the user.
     * @param task Task that is marked.
     */
    public void marked(Task task) {
        System.out.println("I've marked this task as done: " + task);
    }

    /**
     * Prints out note after a task is unmarked by the user.
     * @param task Task that is unmarked.
     */
    public void unmarked(Task task) {
        System.out.println("I've marked this task as not done yet: " + task);
    }

    /**
     * Prints out note after a task is deleted by the user.
     * @param task Task that is deleted.
     */
    public void deleted(Task task) {
        System.out.println("I've deleted this task: " + task);
    }

    /**
     * Prints out note after a task is added to the task list.
     * @param task Task that is added.
     */
    public void addedTask(Task task) {
    public void sayGoodbye() {
        System.out.println("Byebye CATch you later!");
    }

    public void showMarked(Task task) {
        System.out.println("I've marked this task as done: " + task);
    }

    public void showUnmarked(Task task) {
        System.out.println("I've marked this task as not done yet: " + task);
    }

    public void showDeleted(Task task) {
        System.out.println("I've deleted this task: " + task);
    }

    public void showAddTask(Task task) {
        System.out.println("Meow! Just added: \n" + task);
    }

    /**
     * Prints out error message when the program runs into an invalid input.
     * @param error Error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Returns an empty next line.
     * @return Next line string.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Asks the user when deadline task is due.
     */
    public void askBy() {
        System.out.println("By?");
    }

    /**
     * Asks the user when event task starts.
     */
    public void askFrom() {
        System.out.println("From?");
    }

    /**
     * Asks the user when event task ends.
     */
    public void askTo() {
        System.out.println("To?");
    }
}
