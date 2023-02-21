package luffy.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import luffy.storage.TaskList;
import luffy.task.Deadline;
import luffy.task.Event;
import luffy.task.Task;
import luffy.task.Todo;

/**
 * Class contains variables and methods related to interactions with the user.
 */
public class Ui {
    private static final String LINE = "________________________________";
    private final Scanner input;

    /**
     * Creates an instance of Ui.
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Prints a line.
     * @return LINE.
     */
    public String showLine() {
        return LINE + "\n";
    }

    /**
     * Prints error message from an exception.
     * @param errorMsg String containing error message of exception.
     * @return String containing error message.
     */
    public String showError(String errorMsg) {
        return errorMsg + "\n";
    }

    /**
     * Prints exit message.
     * @return String containing exit message.
     */
    public String showExit() {
        return "Bye bye! Luffy will go to sleep now!\nHope to see you again soon!! :>\n";
    }

    /**
     * Prints TaskList of Luffy.
     * @param taskList TaskList to be printed.
     * @return Response after printing lst.
     */
    public String showList(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.printList() + "\n";
    }

    /**
     * Prints command message after mark command is executed successfully.
     * @param task task marked as done.
     * @return Response after task marked as done.
     */
    public String showMarkedTask(Task task) {
        return "Okay! I've marked this task as done:\n" + task.toString() + "\n";
    }

    /**
     * Prints response after unnmark commamnd is successfully executed.
     * @param task task marked as unDone.
     * @return Response after task marked as undone.
     */
    public String showUnmarkedTask(Task task) {
        return "Okay! I've marked this task as not done yet:\n"
                + task.toString() + "\n";
    }

    /**
     * Prints command message after delete command is successfully executed.
     * @param task task deleted.
     * @param size current size of the list.
     * @return Response after delete is successful.
     */
    public String showDeletedTask(Task task, int size) {
        return "Okay! I've removed this task from the list:\n" + task.toString() + "\n"
                + String.format("Now you have %d tasks in the list!\n", size);
    }

    /**
     * Prints command message after todoCommand is successfully executed.
     * @param todo Todo task created.
     * @param size current size of the list.
     * @return Response after todo successfully created.
     */
    public String showTodo(Todo todo, int size) {
        return "Got it! I've added: \n" + " " + todo.toString() + "\n"
                + String.format("Now you have %d tasks in the list!\n", size);
    }

    /**
     * Prints command message after deadline command is successfully executed.
     * @param deadline Deadline task created.
     * @param size current size of the list.
     * @return Response after deadline successfully created.
     */
    public String showDeadline(Deadline deadline, int size) {
        return "Got it! I've added: \n" + " " + deadline.toString() + "\n"
                + String.format("Now you have %d tasks in the list!\n", size);
    }

    /**
     * Prints command message after event command is successfully executed.
     * @param event Event task created.
     * @param size current size of the list.
     * @return Response after event successfully created.
     */
    public String showEvent(Event event, int size) {
        return "Got it! I've added: \n" + " " + event.toString() + "\n"
                + String.format("Now you have %d tasks in the list!\n", size);
    }

    public String showSchedule(TaskList taskList, LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "Here is your schedule for " + formattedDate + ":)\n"
                + taskList.printSchedule(date) + "\n";
    }
}
