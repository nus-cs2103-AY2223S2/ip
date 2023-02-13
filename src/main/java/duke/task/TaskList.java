package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.Parser;
import duke.Ui;



/**
 * Encapsulates an arraylist of Task with the various methods
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Nicely prints out all the task
     */
    public String list() {
        String res = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            res += i + 1 + ". " + list.get(i) +  "\n";
        }
        return res;
    }

    /**
     * Prints out default message after adding a dask
     */
    public String addTask() {
        return "Got it. I've added this duke.task:\n" + list.get(list.size() - 1)
                + "\n" + "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Remove a task
     * @param i index of task in arraylist
     */
    public String delete(int i) {
        String res = "Noted. I've removed this duke.task:\n";
        res += list.get(i) + "\n";
        list.remove(i);
        res += "Now you have " + list.size() + " tasks in the list.";
        return res;
    }

    /**
     * Mark a task as done
     * @param i index of task in arraylist
     */
    public String mark(int i) {
        Task task = list.get(i);
        assert task != null;
        task.mark();
        return "Nice! I've marked this duke.task as done:\n" + list.get(i);
    }

    /**
     * Mark a task as undone
     * @param i index of task in arraylist
     */
    public String unmark(int i) {
        Task task = list.get(i);
        assert task != null;
        task.unmark();
        return "OK, I've marked this duke.task as not done yet:\n" + list.get(i);
    }

    /**
     * Add a todo task into tasklist
     * @param command the full command
     */

    public String addTodo(String command) {
        try {
            String description = Parser.getDescription(command);
            list.add(new Todo(description, false));
            return this.addTask();
        } catch (Exception e) {
            return "OOPS!!! The description of a todo cannot be empty.";
        }
    }

    /**
     * Add a deadline task into tasklist
     * @param command the full command
     */
    public String addDeadline(String command) {
        try {
            String[] parsed = Parser.parseDeadline(command);
            assert parsed.length == 2;
            String deadline = parsed[1];
            String description = parsed[0];
            list.add(new Deadline(description, deadline, false));
            return this.addTask();
        } catch (DateTimeParseException e) {
            return Ui.wrongDateFormat();
        } catch (Exception e) {
            return "OOPS!!! Unrecognizable formet\n Please write it in this format: deadline xxx /by "
                    + "YYYY-MM-DD HH:mm.";
        }
    }

    /**
     * View tasks on a specific date
     */
    public String view(String command) {
        try {
            String d = command.split(" ")[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(d, formatter);
            String res = "Here are the tasks happening on that day\n";
            for (Task t: list) {
                if (t instanceof Deadline) {
                    Deadline temp = (Deadline) t;
                    if (temp.deadline.toLocalDate().equals(date)) {
                        res += temp + "\n";
                    }
                }
                if (t instanceof Event) {
                    Event temp = (Event) t;
                    if (temp.begin.toLocalDate().equals(date)
                            || temp.end.toLocalDate().equals(date)) {
                        res += temp + "\n";
                    }
                }
            }
            return res;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Add an event task into tasklist
     * @param command the full command
     */
    public String addEvent(String command) {
        try {
            String[] parsed = Parser.parseEvent(command);
            assert parsed.length == 3;
            String description = parsed[0];
            String start = parsed[1];
            String end = parsed[2];
            list.add(new Event(description, start, end, false));
            return this.addTask();
        } catch (DateTimeParseException e) {
            return Ui.wrongDateFormat();
        } catch (Exception e) {
            return "OOPS!!! Insufficient information or wrong format.";
        }
    }

    /**
     * Finds tasks with matching regex and prints them out
     * @param command the command with the find keyword
     */
    public String find(String command) {
        String keyword = Parser.parseQuery(command);
        ArrayList<Task> temp = new ArrayList<Task>();
        for (Task t: list) {
            if (t.description.matches("(.*)" + keyword + "(.*)")) {
                temp.add(t);
            }
        }
        if (temp.size() == 0) {
            return "No matching task!";
        }
        String res = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < temp.size(); i++) {
            res += i + 1 + ". " + temp.get(i);
        }
        return res;
    }
}
