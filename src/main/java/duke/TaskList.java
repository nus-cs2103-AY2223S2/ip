package duke;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * List of tasks that the user can add, mark, find or delete.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Class constructor of Storage.
     * Initialize by adding a zeroth element so that the index of tasks can start from 1.
     */
    public TaskList() {
        this.list.add(new ToDo("zeroth"));
    }

    /**
     * Add a Todo Task to the TaskList.
     * @param input the content of the new Todo Task
     * @param needPrint the indicator that shows whether the message needs to be printed
     * @throws DukeException if the content is empty
     */
    public void add(String input, boolean needPrint) throws DukeException {
        if (isEmpty(input)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ToDo newTask = new ToDo(input);
        this.list.add(newTask);
        if (needPrint) {
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask.toString());
            System.out.println("Now you have " + (this.list.size() - 1) + " tasks in the list.");
        }
    }

    /**
     * Add a Deadline Task to the TaskList.
     * @param input the content of the new Deadline Task
     * @param deadline the deadline date and time of the new Deadline Task
     * @param needPrint the indicator that shows whether the message needs to be printed
     * @throws DukeException if the content is empty
     */
    public void add(String input, String deadline, boolean needPrint) throws DukeException {
        if (isEmpty(input) || isEmpty(deadline)) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }
        Deadline newTask = new Deadline(input, deadline);
        this.list.add(newTask);
        if (needPrint) {
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask.toString());
            System.out.println("Now you have " + (this.list.size() - 1) + " tasks in the list.");
        }
    }

    /**
     * Add a Event Task to the TaskList.
     * @param input the content of the new Event Task
     * @param from the start date and time of the new Event Task
     * @param to the end date and time of the new Event Task
     * @param needPrint the indicator that shows whether the message needs to be printed
     * @throws DukeException if the content is empty
     */
    public void add(String input, String from, String to, boolean needPrint) throws DukeException {
        if (input.equals("") || from.equals("") || to.equals("")) {
            throw new DukeException("The description and duration of an event cannot be empty.");
        }
        Event newTask = new Event(input, from, to);
        this.list.add(newTask);
        if (needPrint) {
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask.toString());
            System.out.println("Now you have " + (this.list.size() - 1) + " tasks in the list.");
        }
    }

    /**
     * Checks whether the string is an empty string, excluding spaces.
     * @param str the string to be checked
     * @return true when the string is empty
     */
    public boolean isEmpty(String str) {
        String temp = str.replaceAll("\\s+", "");
        if (temp.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Deletes a Task from the TaskList.
     * @param index the index of the Task to be deleted
     */
    public void delete(int index) {
        Task removedTask = this.list.get(index);
        this.list.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + (this.list.size() - 1) + " tasks in the list.");
    }

    /**
     * Returns a task with the given index.
     * @param index the Task to be returns
     * @return Task with the given index in the TaskList
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Finds tasks that contains the target string.
     * @param target the target string to find
     */
    public void find(String target) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        foundTasks.add(new ToDo("zeroth"));
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i < this.list.size(); i++) {
            if (this.list.get(i).getTask().contains(target)) {
                foundTasks.add(list.get(i));
            }
        }
        for (int i = 1; i < foundTasks.size(); i++) {
            System.out.println(i + ". " + foundTasks.get(i).toString());
        }
    }

    /**
     * Stringifies the TaskList.
     * @return the string representation of the TaskList
     */
    @Override
    public String toString() {
        String str = "Here are the tasks in your list:";
        for (int i = 1; i < this.list.size(); i++) {
            str += "\n";
            str += i + ". " + this.list.get(i).toString();
        }
        return str;
    }

    /**
     * Stringifies the TaskList with relevant format for the saved data file.
     * @return the string representation of the TaskList that follows the format for the saved data file
     */
    public String toTxtString() {
        String data = "";
        for (int i = 1; i < this.list.size(); i++) {
            data += this.list.get(i).toTxtString() + "\n";
        }
        return data;
    }

    /**
     * Returns the number of Tasks in this TaskList.
     * @return the number of Tasks in this TaskList
     */
    public int size() {
        return list.size();
    }
}
