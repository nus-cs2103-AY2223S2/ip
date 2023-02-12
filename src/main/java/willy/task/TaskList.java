package willy.task;

import java.util.ArrayList;
import java.util.List;

import willy.storage.Storage;

/**
 * Represents a tasklist
 */
public class TaskList {
    private static Storage storage;
    protected List<Task> tasks = new ArrayList<Task>();

    /**
     * Creates a tasklist with a storage
     */
    public TaskList() {
        this.storage = new Storage();
        List<Task> tmp = storage.loadData();
        this.tasks = tmp;
    }

    /**
     * Create a tasklist with a specified storage to be used
     * 
     * @param storage
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        List<Task> tmp = storage.loadData();
        this.tasks = tmp;
    }

    /**
     * Get task count
     * 
     * @return int
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Based on the task index, will return the task
     * 
     * @param index
     * @return the task at the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the index as done
     * 
     * @param index
     */
    public String markTask(int index) {
        Task t = tasks.get(index);
        String str = t.mark();
        storage.save(this);
        return str;
    }

    /**
     * Unmarks the task at the index as undone
     * 
     * @param index
     */
    public String unmarkTask(int index) {
        Task t = tasks.get(index);
        String str = t.unmark();
        storage.save(this);
        return str;
    }

    /**
     * delete task
     * 
     * @param index
     */
    public String deleteTask(int index) {
        tasks.remove(index);
        storage.save(this);
        return "Successfully deleted this task";
        // System.out.println("Successfully deleted this task"); // chaneg this later
    }

    /**
     * Command to add a todo to the tasklist
     * 
     * @param details
     */
    public String addTodo(String details) {
        Todo entry = new Todo(details);
        tasks.add(entry);
        storage.save(this);
        // System.out.println(entry.toString());
        String str = entry.toString();
        return str;
        // System.out.format("Now you have %d things in your list%n",
        // this.getTaskCount());
        // System.out.println("Successfully added a todo"); // chaneg
    }

    /**
     * Command to add a deadline to the tasklist
     * 
     * @param details
     * @param date
     */
    public String addDeadline(String details, String date) {
        Deadline entry = new Deadline(details, date);
        tasks.add(entry);
        storage.save(this);
        // System.out.println(entry.toString());
        String str = entry.toString();
        return str;
        // System.out.println("Successfully added a deadline"); // chaneg this later
    }

    /**
     * Command to add a deadline with a date format to the tasklist
     * 
     * @param details
     * @param dateArray
     */
    public String addDeadlineWithDate(String details, String[] dateArray) {
        Deadline entry = new Deadline(details, dateArray);
        tasks.add(entry);
        storage.save(this);
        // System.out.println(entry.toString());
        String str = entry.toString();
        return str;
        // System.out.println("Successfully added a deadline with a date"); // chaneg
        // this later
    }

    /**
     * Command to add a event to the tasklist
     * 
     * @param details
     * @param dateFrom
     * @param dateTo
     */
    public String addEvent(String details, String dateFrom, String dateTo) {
        Event entry = new Event(details, dateFrom, dateTo);
        tasks.add(entry);
        storage.save(this);
        // System.out.println(entry.toString());
        String str = entry.toString();
        return str;
        // System.out.println("Successfully added an event"); // chaneg this later
    }

    /**
     * print all tasks in a list of strings that contains the keyword
     * 
     * @param keyword
     */
    public String findTasks(String keyword) {
        // System.out.println("Here are the matching tasks in your list");

        String str = "Here are the matching tasks in your list\n";
        String msg = "";
        int counter = 1;
        for (int i = 0; i < getTaskCount(); i++) {
            if (this.getTask(i).toString().contains(keyword)) {
                msg = "#" + counter + ". " + this.getTask(i);

                if (i == getTaskCount() - 1) {
                    // Marks the end of the tList
                    str += msg;
                    counter++;
                } else {
                    str += msg + "\n";
                    counter++;
                }
            }

        }
        // System.out.println(str);
        return str;
    }

    /**
     * Converts the tasklist into a combined string representation (need to check)
     */
    @Override
    public String toString() {
        String str = "";
        String msg = "";
        for (int i = 0; i < getTaskCount(); i++) {
            msg = "#" + (i + 1) + ". " + this.getTask(i);

            if (i == getTaskCount() - 1) {
                // Marks the end of the tList
                str += msg;
            } else {
                str += msg + "\n";
            }
        }
        return str;
    }

}
