package duke;

import duke.tasks.Task;

import javax.print.DocFlavor;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    public static String GREET_MSG = "Hello! I am Duke Nice To Meet You\n";
    public static String BYE_MSG = "Bye! Hope to See You Again!";
    public static String ADD_MSG = "Got it fam! I've added this task:\n ";

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
    public String showError(String e_message) {
        return e_message;
    }

    /**
     * Greets the user.
     *
     * @return
     */
    public String printGreet() {
        return GREET_MSG;
    }

    /**
     * Says goodbye to the user.
     */
    public String printBye() {
        return BYE_MSG;
    }


    /**
     * Displays all the tasks in his TaskList.
     *
     * @param taskList his current TaskList.
     */
    public String showList(TaskList taskList) throws DukeException {
        try {
            return taskList.printTasks();
        } catch (DukeException e) {
            return this.showError(e.getMessage());
        }
    }

    /**
     * Prints the task that's been completed by the user.
     *
     * @param markedTask task that is to be marked by the user
     */
    public String printMarkTask(Task markedTask) {
        return "Nice! I have marked this task as Done:\n" + markedTask.toString() + "\n";
    }

    /**
     * Prints the task that's been added by the user.
     * Prints the total number of tasks in his TaskList after adding the recent task.
     *
     * @param num_tasks number of total tasks after addition.
     * @param to_add the task that's to be added .
     */
    public String printAddTask(Task to_add, Integer num_tasks) {
        return ADD_MSG + to_add.toString() + "\nYou currently have " + num_tasks + " tasks in this list!\n";
    }

    /**
     * Prints the task that's been unmarked by the user.
     *
     * @param unmarkedTask the task thats been unmarked by user.
     */
    public String printUnmarkTask(Task unmarkedTask) {
        return "Ok! I have marked this task as not done yet:\n" +
                unmarkedTask.toString() + "\n";
    }

    /**
     * Prints the task that's been deleted by the user.
     *
     * @param removedTask the tasks that's removed.
     * @param num_tasks number of total tasks after deletion.
     */
    public String printDeleteTask(Task removedTask, Integer num_tasks) {
        return "Noted!I have deleted the task for you:\n " +
                removedTask.toString() + "\nyou currently have " + num_tasks +
                " tasks in this list!\n";
    }

    public String printTaskByKeyWord(TaskList taskList, String keyWord) throws DukeException {
        ArrayList<Task> allFoundTasks = taskList.getTasksByKeyWord(keyWord);
        int index = 1;
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : allFoundTasks) {
            sb.append(String.format("%d.%s\n", index, task.toString()));
            index++;
        }
        return sb.toString();
    }
}
