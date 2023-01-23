package duke.task;

import duke.Parser;
import duke.Ui;

import java.util.ArrayList;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates an arraylist of Task
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();

    public ArrayList<Task> getList () {
        return this.list;
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Nicely prints out all the task
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
    }

    /**
     * Prints out default message after adding a dask
     */
    public void addTask() {
        System.out.println("Got it. I've added this duke.task:\n" + list.get(list.size() - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Remove a task
     * @param i index of task in arraylist
     */
    public void delete(int i) {
        System.out.println("Noted. I've removed this duke.task:");
        System.out.println(list.get(i));
        list.remove(i);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Mark a task as done
     * @param i index of task in arraylist
     */
    public void mark(int i) {
        list.get(i).mark();
        System.out.println("Nice! I've marked this duke.task as done:\n" + list.get(i));
    }

    /**
     * Mark a task as undone
     * @param i index of task in arraylist
     */
    public void unmark(int i) {
        list.get(i).unmark();
        System.out.println("OK, I've marked this duke.task as not done yet:\n" + list.get(i));
    }

    public void addTodo(String command) {
        try {
            String description = Parser.getDescription(command);
            list.add(new Todo(description,false));
            this.addTask();
        } catch (Exception e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public void addDeadline(String command) {
        try {
            String[] parsed = Parser.parseDeadline(command);
            String deadline = parsed[1];
            String description = parsed[0];
            list.add(new Deadline(description, deadline, false));
            this.addTask();
        } catch (DateTimeParseException e) {
            Ui.wrongDateFormat();
        } catch (Exception e) {
            System.out.println("☹ OOPS!!! Unrecognizable formet\n Please write it in this format: deadline xxx /by " +
                    "YYYY-MM-DD HH:mm.");
        }
    }

    public void addEvent(String command) {
        try {
            String[] parsed = Parser.parseEvent(command);
            String description = parsed[0];
            String start = parsed[1];
            String end = parsed[2];
            list.add(new Event(description, start, end, false));
            this.addTask();
        } catch (DateTimeParseException e) {
            Ui.wrongDateFormat();
        } catch (Exception e) {
            System.out.println("☹ OOPS!!! Insufficient information or wrong format.");
        }
    }

}
