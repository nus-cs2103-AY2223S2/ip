package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

public class TaskList {

    static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu kk:mm");
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task markTask(int taskNum, boolean isDone) throws ArrayIndexOutOfBoundsException {
        Task taskToMark = tasks.get(taskNum - 1);
        if(isDone){
            taskToMark.mark();
        } else {
            taskToMark.unmark();
        }
        return taskToMark;
    }

    public Task deleteTask(int taskNum) {
        Task deleteTask = tasks.remove(taskNum);
        return deleteTask;
    }

    public Task addTodo(String name) {
        Todo newTask = new Todo(name);
        tasks.add(newTask);
        return newTask;
    }

    public Task addDeadline(String name, String deadline) {
        Deadline newTask = new Deadline(name, deadline, FORMAT);
        tasks.add(newTask);
        return newTask;
    }

    public Task addEvent(String name, String from, String to) {
        Event newTask = new Event(name, from, to, FORMAT);
        tasks.add(newTask);
        return newTask;
    }

    public String toStringList() {
        if(this.tasks.isEmpty()) {
            return "";
        }
        String str = "1. " + this.tasks.get(0).toString();
        for(int i = 1; i < this.tasks.size(); i++) {
            str = str + "\n" + (i + 1) + ". " + this.tasks.get(i);
        }
        return str;
    }

    @Override
    public String toString() {
        if(this.tasks.isEmpty()) {
            return "";
        }
        String str = this.tasks.get(0).toString();
        for(int i = 1; i < this.tasks.size(); i++) {
            str = str + "\n" + this.tasks.get(i);
        }
        return str;
    }

}
