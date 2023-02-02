package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    public static String HELLO = "Hello! I am Duke Nice To Meet You\n";
    public static String BYE = "Bye! Hope to See You Again!";
    public static String ADD = "Got it fam! I've added this task:\n ";


    private Scanner sc;

    /**
     * Creates a Ui class for Duke.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Fetches the next commands given by users.
     *
     * @return Command given by the users.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the various error messages to the user.
     *
     * @param e_message DukeException's error messages.
     */
    public void showError(String e_message) {
        System.out.println(e_message);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(HELLO);
    }

    /**
     * Says goodbye to the user.
     */
    public void bye() {
        System.out.println(BYE);
    }


    /**
     * Displays all the tasks in his TaskList.
     *
     * @param taskList his current TaskList.
     */
    public void showList(TaskList taskList) {
        try {
            taskList.printTasks();
        } catch (DukeException e) {
            this.showError(e.getMessage());
        }
        System.out.println("");
    }

    /**
     * Prints the task that's been completed by the user.
     *
     * @param markedTask.
     */
    public void printMarkTask(Task markedTask) {
        System.out.println("Nice! I have marked this task as Done:\n" + markedTask.toString() + "\n");
    }

    /**
     * Prints the task that's been added by the user.
     * Prints the total number of tasks in his TaskList after adding the recent task.
     *
     * @param num_tasks number of total tasks after addition.
     * @param to_add the task that's to be added .
     */
    public void printAddTask(Task to_add, Integer num_tasks) {
        System.out.println("Got it fam! I've added this task:\n " + to_add.toString());
        System.out.println("You currently have " + num_tasks + " tasks in this list!\n");
    }

    /**
     * Prints the task that's been unmarked by the user.
     *
     * @param unmarkedTask the task thats been unmarked by user.
     */
    public void printUnmarkTask(Task unmarkedTask) {
        System.out.println("Ok! I have marked this task as not done yet:\n" +
                unmarkedTask.toString() + "\n");
    }

    /**
     * Prints the task that's been deleted by the user.
     *
     * @param removedTask the tasks that's removed.
     * @param num_tasks number of total tasks after deletion.
     */
    public void printDeleteTask(Task removedTask, Integer num_tasks) {
        System.out.println("Noted!I have deleted the task for you:\n " +
                removedTask.toString() + "\nyou currently have " + num_tasks +
                " tasks in this list!\n");
    }
}
