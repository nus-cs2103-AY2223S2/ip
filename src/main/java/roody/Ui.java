package roody;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a CLI User Interface.
 */
public class Ui {
    /** Stores a list of strings to be printed */
    private List<String> printBuffer;

    /**
     * Creates a User Interface that prompts the user from CLI
     */
    public Ui() {
        this.printBuffer = new ArrayList<String>();
    }

    /**
     * Prints basic line
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints start of line
     */
    public void startNextLine() {
        System.out.print("=> ");
    }

    /**
     * Prints output to console using specified input.
     * @param input Input string to be printed.
     */
    public void speak(String input) {
        showLine();
        System.out.println(input);
    }

    /**
     * Prints output to console using specified input.
     * @param inputs List of input to be printed.
     */
    public void speak(List<String> inputs) {
        showLine();
        inputs.forEach(x -> System.out.println(x));
        inputs.clear();
    }

    /**
     * Prints a successful task addition to console for user.
     * @param task  Task that has been added.
     * @param listLength Length of the current list after addition.
     */
    public void showAddTask(Task task, int listLength) {
        printBuffer.add("Got it. I've added this task:");
        printBuffer.add(task.toString());
        printBuffer.add("Now you have " + listLength + " tasks in the list.");
        speak(printBuffer);
    }

    /**
     * Prints a successful task deletion to console for user.
     * @param task  Task that has been added.
     * @param listLength Length of the current list after deletion.
     */
    public void showDeleteTask(Task task, int listLength) {
        printBuffer.add("Noted. I've removed this task:");
        printBuffer.add(task.toString());
        printBuffer.add("Now you have " + listLength + " tasks in the list.");
        speak(printBuffer);
    }

    /**
     * Prints a successful marking/unmarking of task to console for user.
     * @param complete New status of task.
     * @param task  Task that has been added.
     */
    public void showMarkStatus(boolean complete, Task task) {
        if (complete) {
            printBuffer.add("Nice! I've marked this task as done:");
        } else {
            printBuffer.add("OK, I've marked this task as not done yet:");
        }
        printBuffer.add(task.toString());
        speak(printBuffer);
    }

    /**
     * Shows tasks that are found with relevant keywords to console.
     * @param list  List of found tasks.
     * @throws RoodyException If no matching tasks found.
     */
    public void showFoundTasks(ArrayList<Task> list) throws RoodyException {
        if (list.size() == 0) {
            throw new RoodyException("No matching tasks in your list!");
        }
        printBuffer.add("Here are the matching tasks in your list:");
        Integer listIndex = 0;
        for (Task task : list) {
            listIndex++;
            printBuffer.add(listIndex.toString() + '.' + task.toString());
        }
        speak(printBuffer);
    }

    /**
     * Prints tasks in given list to console.
     * @param list List of tasks to be printed.
     * @throws RoodyException If not tasks in list.
     */
    public void printList(ArrayList<Task> list) throws RoodyException {
        if (list.size() == 0) {
            throw new RoodyException("There doesn't seem to be any tasks in your list.");
        }
        printBuffer.add("Here are the tasks in your list:");
        Integer listIndex = 0;
        for (Task task : list) {
            listIndex++;
            printBuffer.add(listIndex.toString() + '.' + task.toString());
        }
        speak(printBuffer);
    }

    /**
     * Prints basic greeting.
     */
    public void greet() {
        this.printBuffer.add("Hello, I'm Roody!");
        this.printBuffer.add("What can I do for you?");
        speak(this.printBuffer);
        showLine();
    }

    /**
     * Prints basic farewell.
     */
    public void bye() {
        speak("Bye. Hope to see you again soon!");
        showLine();
    }
}
