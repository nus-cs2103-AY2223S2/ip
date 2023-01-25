package duke;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.*;

import java.time.LocalDate;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected int counter;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.counter = tasks.size();
    }

    public void addNewTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        this.counter++;
        Ui.showTaskAdded(todo, counter);
    }

    public void addNewDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        this.counter++;
        Ui.showTaskAdded(deadline, counter);
    }

    public void addNewEvent(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        this.counter++;
        Ui.showTaskAdded(event, counter);
    }

    public void showTaskList() {
        Ui.showTaskList(this.tasks);
    }

    public void deleteTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        counter--;
        Ui.showTaskDeleted(removedTask, this.counter);
    }

    public void markTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task task = tasks.get(taskNumber - 1);
        task.setDone(true);
        Ui.showTaskMarked(task, true);
    }

    public void unmarkTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task task = tasks.get(taskNumber - 1);
        task.setDone(false);
        Ui.showTaskMarked(task, false);
    }
}
