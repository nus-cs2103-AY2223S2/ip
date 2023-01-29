package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public Todo addTodo(String title) {
        Todo newTodo = new Todo(title);
        tasks.add(newTodo);
        return newTodo;
    }

    public Deadline addDeadline(String title, LocalDate by) {
        Deadline newDeadline = new Deadline(title, by);
        tasks.add(newDeadline);
        return newDeadline;
    }
    public Event addEvent(String title, LocalDate from, LocalDate to) {
        Event newEvent = new Event(title, from, to);
        tasks.add(newEvent);
        return newEvent;
    }

    public Task tickTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.tick();
        return task;
    }

    public Task untickTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.untick();
        return task;
    }
    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    public TaskList find(String query) {
        ArrayList<Task> foundTasks = this.tasks.stream().filter(t -> t.getTitle().contains(query))
                                                        .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(foundTasks);
    }
}
