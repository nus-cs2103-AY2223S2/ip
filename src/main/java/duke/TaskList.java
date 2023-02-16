package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a list of tasks.
     * @return list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks
     * @return number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Adds a new todo to the task list
     * @param title of todo
     * @return new todo
     */
    public Todo addTodo(String title) {
        Todo newTodo = new Todo(title);
        tasks.add(newTodo);
        assert newTodo.getTitle().equals(title);
        return newTodo;
    }

    /**
     * Adds a new deadline to the task list
     * @param title of task
     * @param by date to finish deadline by
     * @return new deadline
     */
    public Deadline addDeadline(String title, LocalDate by) {
        Deadline newDeadline = new Deadline(title, by);
        tasks.add(newDeadline);
        assert newDeadline.getTitle().equals(title);
        return newDeadline;
    }

    /**
     * Adds a new event to the task list
     * @param title of event
     * @param from date event starts
     * @param to date event ends
     * @return new event
     */
    public Event addEvent(String title, LocalDate from, LocalDate to) {
        Event newEvent = new Event(title, from, to);
        tasks.add(newEvent);
        assert newEvent.getTitle().equals(title);
        return newEvent;
    }

    /**
     * Mark a task as done
     * @param taskIndex the index of the task to be marked
     * @return the task that was marked
     */
    public Task tickTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.tick();
        return task;
    }

    /**
     * Mark a task as not done
     * @param taskIndex the index of the task to be marked
     * @return the task that was marked
     */
    public Task untickTask(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.untick();
        return task;
    }

    /**
     * Delete a task form the task list
     * @param taskIndex index of the task to be deleted
     * @return deleted task
     */
    public Task deleteTask(int taskIndex) {
        return tasks.remove(taskIndex);
    }

    public TaskList find(String query) {
        ArrayList<Task> foundTasks = this.tasks.stream().filter(t -> t.getTitle().contains(query))
                                                        .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(foundTasks);
    }
}
