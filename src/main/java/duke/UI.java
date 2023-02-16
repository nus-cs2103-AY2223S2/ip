package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * UI deals with the interactions with the user, including starting the process,
 * adding tasks into the task list, removing the task from the task list,
 * marking and unmarking tasks, and exiting the process
 */
public class UI {

    /**
     * Prints out the starting message when the Duke application begins
     */
    public String start() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Prints out the list of task from the TaskList
     * @param list Task List
     */
    public String showList(TasksList list) {
        assert !list.equals(null);
        String output = "Here are the task(s) in your list:\n";
        for (int i = 1; i <= list.getSize(); i++) {
            output = output + i + "." + list.getTask(i - 1).toString() + "\n";
        }
        return output;
    }

    /**
     * Prints out the list of tasks from the task list that contains a certain
     * keyword
     */
    public String findTask(TasksList list, String keyword) {
        assert !list.equals(null) && keyword.length() > 0;
        int counter = 1;
        String output = "";
        for (int i = 0; i < list.getSize(); i++) {
            String[] taskDescription = list.getTask(i).getDescription().split(" ");
            for (String word : taskDescription) {
                if (keyword.equals(word)) {
                    output = output + counter + "." + list.getTask(i).toString() + "\n";
                    counter++;
                    break;
                }
            }
        }
        return output;
    }

    /**
     * Mark the task in the certain index of the Task List as done
     * @param list Task List
     * @param index index of the task
     */
    public String mark(TasksList list, int index) {
        assert !list.equals(null) && index > -1;
        list.getTask(index - 1).markAsDone();
        return "Nice! I've marked this task as done:\n  " + list.getTask(index - 1).toString();
    }

    /**
     * Mark the task in the certain index of the Task List as undone
     * @param list Task List
     * @param index index of the task
     */
    public String unmark(TasksList list, int index) {
        assert !list.equals(null) && index > -1;
        list.getTask(index - 1).markAsUndone();
        return "OK, I've marked this task as not done yet:\n  " + list.getTask(index - 1).toString();
    }

    /**
     * Adds a Todo Object into the Task List
     * @param list Task List
     * @param name Description of the todo task
     */
    public String addTodo(TasksList list, String name) {
        assert !list.equals(null) && name.length() > 0;
        list.addTask(new Todo(name));
        return "Got it. I've added this task:\n  " + list.getTask(list.getSize() - 1).toString()
            + "\n" + "Now you have " + list.getSize() + " task(s) in the list.";
    }

    /**
     * Adds a Deadline Object into the Task List
     * @param list Task List
     * @param name Description of the Deadline task
     * @param deadline Date and Time of the deadline
     */
    public String addDeadline(TasksList list, String name, String deadline) {
        assert !list.equals(null) && name.length() > 0 && deadline.length() > 0;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime formattedDeadline = LocalDateTime.parse(deadline, dateTimeFormatter);
        list.addTask(new Deadline(name, formattedDeadline));
        return "Got it. I've added this task:\n  " + list.getTask(list.getSize() - 1).toString()
            + "\n" + "Now you have " + list.getSize() + " task(s) in the list.";
    }

    /**
     * Adds an Event Object into the Task List
     * @param list Task List
     * @param name Description of the event task
     * @param start Date and time of the start of the event
     * @param end Date and time of the end of the event
     */
    public String addEvent(TasksList list, String name, String start, String end) {
        assert !list.equals(null) && name.length() > 0 && start.length() > 0 && end.length() > 0;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime formattedstartTime = LocalDateTime.parse(start, dateTimeFormatter);
        LocalDateTime formattedendTime = LocalDateTime.parse(end, dateTimeFormatter);
        list.addTask(new Event(name, formattedstartTime, formattedendTime));
        return "Got it. I've added this task:\n  " + list.getTask(list.getSize() - 1).toString()
            + "\n" + "Now you have " + list.getSize() + " task(s) in the list.";
    }

    /**
     * Removes a task from the Task List
     * @param list Task List
     * @param index Index of the task
     */
    public String removeTask(TasksList list, int index) {
        assert !list.equals(null) && index > -1;
        return "Noted. I've removed this task :\n  " + list.removeTask(index - 1).toString()
            + "Now you have " + list.getSize() + " task(s) in the list.";
    }

    /**
     * Prints the exit message when the Duke Application closes
     */
    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Adds a period task into the data file
     * @param list list of tasks
     * @param description describes the period task
     * @param start starting date and time
     * @param end ending date and time
     * @return String representation after adding the task into the data file
     */
    public String addPeriod(TasksList list, String description, String start, String end) {
        assert !list.equals(null) && description.length() > 0 && start.length() > 0 && end.length() > 0;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime formattedstartTime = LocalDateTime.parse(start, dateTimeFormatter);
        LocalDateTime formattedendTime = LocalDateTime.parse(end, dateTimeFormatter);
        list.addTask(new Period(description, formattedstartTime, formattedendTime));
        return "Got it. I've added this task:\n  " + list.getTask(list.getSize() - 1).toString()
            + "\n" + "Now you have " + list.getSize() + " task(s) in the list.";
    }
}
