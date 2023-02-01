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
    public String addNewTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        this.counter++;
        return Ui.showTaskAdded(todo, counter);
    }

    /**
     * Adds a Deadline to tasks
     *
     * @param description description of the Deadline
     * @param by date of the Deadline
     */
    public String addNewDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        this.counter++;
        return Ui.showTaskAdded(deadline, counter);
    }

    /**
     * Adds an Event to tasks
     *
     * @param description description of the Event
     * @param from start date of Event
     * @param to end date of Event
     */
    public String addNewEvent(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        this.counter++;
        return Ui.showTaskAdded(event, counter);
    }

    /**
     * Lists out everything in tasks
     */
    public String showTaskList() {
        return Ui.showTaskList(this.tasks);
    }

    /**
     * Deletes a Task from tasks
     *
     * @param taskNumber the number representing the task being deleted
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available
     */
    public String deleteTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        counter--;
        return Ui.showTaskDeleted(removedTask, this.counter);
    }

    /**
     * Marks a Task in tasks
     *
     * @param taskNumber the number representing the task being deleted
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available
     */
    public String markTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task task = tasks.get(taskNumber - 1);
        task.setDone(true);
        return Ui.showTaskMarked(task, true);
    }

    /**
     * Unmarks a Task in tasks
     *
     * @param taskNumber the number representing the task being deleted
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available
     */
    public String unmarkTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter) {
            throw(new TaskNotFoundException("Task " + taskNumber +" does not exist"));
        }
        Task task = tasks.get(taskNumber - 1);
        task.setDone(false);
        return Ui.showTaskMarked(task, false);
    }

    /**
     * List out tasks that contains the keyword
     *
     * @param keyword the word used for filtering tasks
     */
    public String findTask(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        return Ui.showTaskList(filteredTasks);
    }
}
