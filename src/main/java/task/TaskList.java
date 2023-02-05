package task;

import exception.WillyException;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> tasks = new ArrayList<Task>();
    public Storage storage;

    public TaskList() {
        this.storage = new Storage();
        List<Task> tmp = storage.loadData();
        this.tasks = tmp;
    }

    public TaskList(Storage storage) {
        this.storage = storage;
        List<Task> tmp = storage.loadData();
        this.tasks = tmp;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markTask(int index) {
        Task t = tasks.get(index);
        t.mark();
        storage.save(this);
    }

    public void unmarkTask(int index) {
        Task t = tasks.get(index);
        t.unmark();
        storage.save(this);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
        storage.save(this);
        // System.out.println("Successfully deleted this task"); // chaneg this later
    }

    public void addTodo(String details) throws WillyException {
        Todo entry = new Todo(details);
        tasks.add(entry);
        storage.save(this);
        System.out.println(entry.toString());
        // System.out.format("Now you have %d things in your list%n", this.getTaskCount());
        // System.out.println("Successfully added a todo"); // chaneg
    }

    public void addDeadline(String details, String date) throws WillyException {
        Deadline entry = new Deadline(details, date);
        tasks.add(entry);
        storage.save(this);
        System.out.println(entry.toString());
        // System.out.println("Successfully added a deadline"); // chaneg this later
    }

    public void addDeadlineWithDate(String details, String[] dateArray) {
        Deadline entry = new Deadline(details, dateArray);
        tasks.add(entry);
        storage.save(this);
        System.out.println(entry.toString());
        // System.out.println("Successfully added a deadline with a date"); // chaneg
        // this later
    }

    public void addEvent(String details, String dateFrom, String dateTo) throws WillyException {
        Event entry = new Event(details, dateFrom, dateTo);
        tasks.add(entry);
        storage.save(this);
        System.out.println(entry.toString());
        // System.out.println("Successfully added an event"); // chaneg this later
    }

    @Override
    public String toString() {
        String str = "", msg = "";
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
