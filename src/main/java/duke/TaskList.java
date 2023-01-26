package duke;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.*;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * This is a class responsible for keeping track of the tasks in Duke
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected int counter;

    /**
     * Constructor for TaskList class
     *
     * @param tasks an ArrayList of task that contains previously saved tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.counter = tasks.size();
    }

    /**
     * Adds a Todo to tasks
     *
     * @param description description of the Todo
     */
    public void addNewTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        this.counter++;
        Ui.showTaskAdded(todo, counter);
    }

    /**
     * Adds a Deadline to tasks
     *
     * @param description description of the Deadline
     * @param by date of the Deadline
     */
    public void addNewDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        this.counter++;
        Ui.showTaskAdded(deadline, counter);
    }

    /**
     * Adds an Event to tasks
     *
     * @param description description of the Event
     * @param from start date of Event
     * @param to end date of Event
     */
    public void addNewEvent(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        this.counter++;
        Ui.showTaskAdded(event, counter);
    }

    /**
     * Lists out everything in tasks
     */
    public void showTaskList() {
        Ui.showTaskList(this.tasks);
    }

    /**
     * Deletes a Task from tasks
     *
     * @param taskNumber the number representing the task being deleted
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available
     */
    public void deleteTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        counter--;
        Ui.showTaskDeleted(removedTask, this.counter);
    }

    /**
     * Marks a Task in tasks
     *
     * @param taskNumber the number representing the task being deleted
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available
     */
    public void markTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task task = tasks.get(taskNumber - 1);
        task.setDone(true);
        Ui.showTaskMarked(task, true);
    }

    /**
     * Unmarks a Task in tasks
     *
     * @param taskNumber the number representing the task being deleted
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available
     */
    public void unmarkTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task task = tasks.get(taskNumber - 1);
        task.setDone(false);
        Ui.showTaskMarked(task, false);
    }

    /**
     * List out tasks that contains the keyword
     *
     * @param keyword the word used for filtering tasks
     */
    public void findTask(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        Ui.showTaskList(filteredTasks);
    }
}
