package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Stores list of tasks in an ArrayList,
 * on which operations can be done to create, update, read and delete.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public String list() {
        String list = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            int currentNumber = i + 1;
            Task task = this.tasks.get(i);

            list = list + "\t" + currentNumber + "." + task + "\n";
        }
        return list;
    }


    public String delete(int index) {
        String output;
        Task task = this.tasks.get(index - 1);
        this.tasks.remove(index - 1);
        output = "Noted. I've removed this task:" + "\n\t" + task;
        output = output + "\nNow you have " + this.tasks.size() + " tasks in the list.";
        return output;
    }

    public String addToDo(String taskDetails) {
        String output;
        ToDo task = new ToDo(taskDetails);
        this.tasks.add(task);
        output = "\t" + task;
        output = output + "\nNow you have " + this.tasks.size() + " tasks in the list.";
        return output;
    }

    public String addDeadline(String description, String by) {
        String output;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(by, format);
        Deadline task = new Deadline(description, date);
        this.tasks.add(task);
        output = "\t" + task;
        output = output + "\nNow you have " + this.tasks.size() + " tasks in the list.";
        return output;
    }

    public String addEvent(String description, String from, String to) {
        String output;
        Event task = new Event(description, from, to);
        this.tasks.add(task);
        output = "\t" + task;
        output = output + "\nNow you have " + this.tasks.size() + " tasks in the list.";
        return output;
    }

    /**
     *  Filters the tasklist based on input and returns if task contains words from the input.
     * @param input String.
     */
    public String find(String input) {
        String output;
        int counter = 1;
        output = "Here are the matching tasks in your list:";
        for (Task task : tasks) {
            if (task.getDescription().contains(input)) {
                output = output + "\n\t" + counter + "." + task;
                counter++;
            }
        }
        return output;
    }

    public String setTaskStatus(int index, boolean isDone) {
        Task task = this.tasks.get(index - 1);
        task.setDone(isDone);
        return "\tOk, I have marked this task as " + (isDone ? "done" : "not done yet") + ":\n\t\t"
                + task;
    }

    public int listSize() {
        return this.tasks.size();
    }
}
