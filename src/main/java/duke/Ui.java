package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

public class Ui {
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private static String GREET_MSG = "Hello! I am Duke Nice To Meet You\n";
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private static String BYE_MSG = "Bye! Hope to See You Again!";
    @SuppressWarnings("checkstyle:VisibilityModifier")
    private static String ADD_MSG = "Got it fam! I've added this task:\n ";
    private static Integer PRODUCTIVITY_CHECK = 5;
    private static String PRODUCTIVITY_MSG = "You got to be more Productive!\n";


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
     * @param errorMessage DukeException's error messages.
     */
    public String showError(String errorMessage) {
        return errorMessage;
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
     * @param numTasks number of total tasks after addition.
     * @param addedTask the task that's to be added .
     */
    public String printAddTask(Task addedTask, Integer numTasks) {
        return ADD_MSG + addedTask.toString() + "\nYou currently have " + numTasks + " tasks in this list!\n";
    }

    /**
     * Prints the task that's been unmarked by the user.
     *
     * @param unmarkedTask the task thats been unmarked by user.
     */
    public String printUnmarkTask(Task unmarkedTask) {
        return "Ok! I have marked this task as not done yet:\n"
               + unmarkedTask.toString() + "\n";
    }

    /**
     * Prints the task that's been deleted by the user.
     *
     * @param removedTask the tasks that's removed.
     * @param numTasks number of total tasks after deletion.
     */
    public String printDeleteTask(Task removedTask, Integer numTasks) {
        return "Noted!I have deleted the task for you:\n "
                + removedTask.toString() + "\nyou currently have " + numTasks
                 + " tasks in this list!\n";
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

    public String printStatistics(TaskList taskList) {
        ArrayList<Integer> statistics = taskList.checkStatistics();
        int urgentTasks = statistics.get(0);
        int addedTasks = statistics.get(1);
        int completedTasks = statistics.get(2);

        StringBuilder message = new StringBuilder(String.format("You have %d Urgent Tasks\n", urgentTasks));

        if (urgentTasks >= PRODUCTIVITY_CHECK) {
            message.append(PRODUCTIVITY_MSG);
        }
        String secondMessage = String.format("You have added %d Tasks within this week!\n"
                        + "You have completed %d tasks within this week! Good Job!",
                addedTasks, completedTasks);
        message.append(secondMessage);
        return message.toString();
    }
}
