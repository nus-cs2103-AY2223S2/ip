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
    public void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints out the list of task from the TaskList
     * @param list Task List
     */
    public void showList(TasksList list) {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 1; i <= list.getSize(); i++) {
            System.out.println(i + "." + list.getTask(i-1).toString());
        }
    }

    /**
     * Prints out the list of tasks from the task list that contains a certain
     * keyword
     */
    public void findTask(TasksList list, String keyword) {
        int counter = 1;
        for (int i = 0; i < list.getSize(); i++) {
            
            String[] taskDescription = list.getTask(i).getDescription().split(" ");
            for (String word : taskDescription) {
                if(keyword.equals(word)) {
                    System.out.println(counter + "." + list.getTask(i).toString());
                    counter++;
                    break;
                }
            }
        }
    }

    /**
     * Mark the task in the certain index of the Task List as done
     * @param list Task List
     * @param index index of the task
     */
    public void mark(TasksList list, int index) {
        list.getTask(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n  " + list.getTask(index - 1).toString());
    }

    /**
     * Mark the task in the certain index of the Task List as undone
     * @param list Task List
     * @param index index of the task
     */
    public void unmark(TasksList list, int index) {
        list.getTask(index - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n  " + list.getTask(index - 1).toString());
    }

    /**
     * Adds a Todo Object into the Task List
     * @param list Task List
     * @param name Description of the todo task
     */
    public void addTodo(TasksList list, String name) {
        list.addTask(new Todo(name));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("Now you have " + list.getSize() + " task(s) in the list.");
    }

    /**
     * Adds a Deadline Object into the Task List
     * @param list Task List
     * @param name Description of the Deadline task
     * @param deadline Date and Time of the deadline
     */
    public void addDeadline(TasksList list, String name, String deadline) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime formattedDeadline = LocalDateTime.parse(deadline, dateTimeFormatter);
        list.addTask(new Deadline(name, formattedDeadline));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("Now you have " + list.getSize() + " task(s) in the list.");
    }

    /**
     * Adds an Event Object into the Task List
     * @param list Task List
     * @param name Description of the event task
     * @param start Date and time of the start of the event
     * @param end Date and time of the end of the event
     */
    public void addEvent(TasksList list, String name, String start, String end) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime formattedstartTime = LocalDateTime.parse(start, dateTimeFormatter);
        LocalDateTime formattedendTime= LocalDateTime.parse(end, dateTimeFormatter);
        list.addTask(new Event(name, formattedstartTime, formattedendTime));
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.getTask(list.getSize() - 1).toString());
        System.out.println("Now you have " + list.getSize() + " task(s) in the list.");
    }

    /**
     * Removes a task from the Task List
     * @param list Task List
     * @param index Index of the task
     */
    public void removeTask(TasksList list, int index) {
        System.out.println("Noted. I've removed this task :");
        System.out.println("  " + list.removeTask(index - 1).toString());
        System.out.println("Now you have " + list.getSize() + " task(s) in the list.");
    }

    /**
     * Prints the exit message when the Duke Application closes
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
