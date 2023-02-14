package duke;


import java.time.LocalDate;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Ui class deals with interactions with the user.
 */
public class Ui {
    private final Stage stage;

    /**
     * A Constructor for the Ui class.
     *
     * @param stage The Stage of the application.
     */
    public Ui(Stage stage) {
        this.stage = stage;
    }

    /**
     * Prints welcome message to user of Duke.
     *
     * @return Welcome message.
     */
    public static String welcomeMessage() {
        return "\tHello! I'm Duke\n"
                + "\tHow can I help you?\n";
    }

    /**
     * Prints bye message to user of Duke and closes Duke.
     *
     * @return bye message.
     */
    public String byeMessage() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> stage.close());
        delay.play();
        return "\t Bye! Hope to see you soon!\n";
    }

    /**
     * Outputs mark message to user.
     *
     * @param task Task to be marked Done.
     * @return mark message.
     */
    public String showMark(Task task) {
        return "\tHooray! I've marked this task as completed!:\n\t  " + task.toString();
    }

    /**
     * Outputs unmark message to user.
     *
     * @param task Task to be marked Undone.
     * @return unmark message.
     */
    public String showUnmark(Task task) {
        return "\tAlright, I've marked this task as not completed yet:\n\t  " + task.toString();
    }

    /**
     * Outputs add message to user.
     *
     * @param task Task to be added.
     * @param size Size of the taskList
     * @return Add task message
     */
    public String showAdd(Task task, int size) {
        return "\tHeard! I've added this task:\n\t  "
                + task.toString() + "\n\tNow you have "
                + size + " task(s) in the list.";
    }

    /**
     * Outputs deleted message to user.
     *
     * @param task Task to be deleted.
     * @param size Size of the taskList.
     * @return Deletion message.
     */
    public String showDelete(Task task, int size) {
        return "\tUnderstood! I've removed this task:\n\t  "
                + task.toString() + "\n\tNow you have "
                + size + " task(s) in the list.";
    }
    /**
     * Outputs deleted message to user.
     *
     * @param task Task to be deleted from findList.
     * @return Deletion message.
     */
    public String showFindDelete(Task task) {
        return "\tUnderstood! I've removed this task:\n\t  "
                + task.toString();
    }
    /**
     * Outputs the taskList to user.
     *
     * @param taskList TaskList which contains the list of tasks.
     * @return String representation of list of tasks.
     */
    public String showList(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "\tThere is nothing in ur list currently";
        } else {
            return taskList.printList();
        }
    }

    /**
     * Prints the list of tasks found using the FindCommand.
     *
     * @param listOfTasksFound ArrayList consisting of strings of tasks found using FindCommand.
     * @return String representation of task in find list.
     */
    public String printFindList(ArrayList<Task> listOfTasksFound) {
        int i = 1;
        StringBuilder s;
        s = new StringBuilder("\tHere are the matching tasks in your list:");
        for (Task task : listOfTasksFound) {
            s.append("\n\t").append(i).append(". ").append(task.toString());
            i++;
        }
        s.append("\n\tThere are ").append(listOfTasksFound.size()).append(" matching task(s) in your list.");
        return s.toString();
    }

    /**
     * Prints the list of tasks with deadlines, if a task has deadline, it will output the number of days to deadline.
     * @param deadlineList list of tasks with deadlines.
     * @return String message
     */

    public String printDeadlineList(ArrayList<Task> deadlineList) {
        int size = deadlineList.size();
        if (size == 0) {
            return "\tYou do not have any task with deadlines!";
        }
        StringBuilder s = new StringBuilder("\tHere are your tasks with deadlines: \n");
        for (Task task: deadlineList) {
            if (task instanceof Deadline) {
                s.append(task).append(" - This is due in ").append(LocalDate.now().until(((Deadline) task)
                        .getDeadline()).getDays()).append(" day(s) ! \n");
            } else {
                s.append(task.toString()).append(" - This event starts in ").append(LocalDate.now().until(((Event) task)
                        .getStartDate()).getDays()).append(" day(s) ! \n");
            }
        }
        return String.format("%s \tYou have %d task(s) that have deadlines/start dates", s, size);
    }
}
