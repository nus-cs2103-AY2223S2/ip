package pix.ui;

import java.time.LocalDate;

import pix.data.MyData;
import pix.tasks.Deadline;
import pix.tasks.Event;
import pix.tasks.Task;

/**
 * Ui class which deals with the interactions with user.
 */
public class Ui {
    /**
     * Returns bye string to be displayed.
     *
     * @return ByeCommand string to be displayed.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!\n\n";
    }

    /**
     * Returns add string to be displayed.
     *
     * @param description Description of task added.
     * @param listLen String of added tasks and number of tasks
     * @return String of added tasks and number of tasks in the list.
     */
    public String add(String description, int listLen) {
        return "Okay! I've added this task:\n\n"
                + description + "\n\n"
                + "Now you have " + listLen + " tasks in the list.\n";
    }

    /**
     * Returns string of task deleted and number of tasks after deletion.
     *
     * @param task Task to be deleted.
     * @param listLen Number of tasks after deleting task.
     * @return String of deleted task and number of tasks after deletion.
     */
    public String delete(Task task, int listLen) {
        return "Understood! The following task is now gone:\n\n"
                + task + "\n\n"
                + "Now you have " + listLen + " tasks in the list.\n";
    }

    /**
     * Returns a string of all the tasks.
     *
     * @param data Data containing ArrayList of tasks.
     * @return String of all the tasks.
     */
    public String list(MyData data) {
        StringBuilder listStringToDisplay = new StringBuilder();
        listStringToDisplay.append("Here are your tasks:\n\n");
        for (int i = 0; i < data.len(); i++) {
            listStringToDisplay.append(i + 1).append(". ").append(data.getTaskAtIndex(i)).append("\n\n");
        }
        return listStringToDisplay.toString();
    }

    /**
     * Returns string of lists tasks that occur or is
     * active during the given date.
     *
     * @param data Data containing ArrayList of tasks.
     * @param date Date of tasks to list.
     */
    public String listDate(MyData data, LocalDate date) {
        String listDateStringToDisplay = "Here are the tasks for the given date:\n\n";
        for (int i = 0; i < data.len(); i++) {
            Task task = data.getTaskAtIndex(i);
            if (task instanceof Deadline) {
                if (((Deadline) task).getDate().equals(date)) {
                    listDateStringToDisplay += (i + 1) + ". " + data.getTaskAtIndex(i) + "\n\n";
                }
            }
            if (task instanceof Event) {
                Event taskEvent = (Event) task;
                if (date.isAfter(taskEvent.getFromDate()) && date.isBefore(taskEvent.getToDate())) {
                    listDateStringToDisplay += (i + 1) + ". " + data.getTaskAtIndex(i) + "\n\n";
                }
            }
        }
        return listDateStringToDisplay;
    }

    /**
     * Return string of task that is marked.
     *
     * @param task Task to be marked.
     * @return String of task that is marked.
     */
    public String mark(Task task) {
        return "Well done! You have completed the following task:\n\n" + task;
    }

    /**
     * Return string of task that is unmarked.
     *
     * @param task Task to un-mark.
     * @return String of task that is unmarked.
     */
    public String unmark(Task task) {
        return "Stop procrastinating and complete the following task:\n\n" + task + "\n";
    }


    /**
     * Return tasks that contains the keyword.
     *
     * @param data Data containing ArrayList of tasks.
     * @param keyword Keyword to search by dueDate.
     * @return String of tasks that contains the keyword.
     */
    public String find(MyData data, String keyword) {
        StringBuilder findStringToDisplay = new StringBuilder();
        findStringToDisplay.append("Here are tasks containing the keyword '").append(keyword).append("'\n\n");
        for (int i = 0; i < data.len(); i++) {
            if (data.getTaskAtIndex(i).inDescription(keyword)) {
                findStringToDisplay.append(i + 1).append(". ").append(data.getTaskAtIndex(i)).append("\n\n");
            }
        }
        return findStringToDisplay.toString();
    }

    /**
     * Return list of commands, their description and formatting.
     *
     * @return String of list of commands, their description and formatting.
     */
    public String help() {
        StringBuilder listOfCommands = new StringBuilder();
        listOfCommands.append("todo - Adds a todo task.\nFormat: todo <Description>\n\n");
        listOfCommands.append("dline - Adds a deadline task. dueDate to be specified as yyyy-MM-dd HH:mm.\n"
                + "Format: dline <Description> / <dueDate>\n\n");
        listOfCommands.append("event - Adds an Event task. "
                + "eventStart and eventEnd to be specified as yyyy-MM-dd HH:mm.\n"
                + "Format: event <Description> / <eventStart> / <eventEnd>\n\n");
        listOfCommands.append("ls - Lists all the tasks.\nFormat: ls\n\n");
        listOfCommands.append("lsd - Lists deadlines that are due by the date and events that area "
                + "ongoing during the date. Date format as yyyy-MM-dd\nFormat: lsd <Date>\n\n");
        listOfCommands.append("mk - Marks a task as done.\nFormat: mk <Index>\n\n");
        listOfCommands.append("unmk - Unmarks a task.\nFormat: unmk <Index>\n\n");
        listOfCommands.append("rm - Removes a task from the list.\nFormat: rm <Index>\n\n");
        listOfCommands.append("find - Find tasks that contains a keyword.\nFormat: find <Keyword>\n\n");
        listOfCommands.append("bye - Exits pix.\nFormat: bye");
        return listOfCommands.toString();
    }
}
