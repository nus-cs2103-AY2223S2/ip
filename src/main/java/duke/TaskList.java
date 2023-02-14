package duke;

import duke.exceptions.TaskNotFoundException;
import duke.tasks.*;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * This is a class responsible for keeping track of the tasks in Duke.
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected int counter;

    /**
     * Constructor for TaskList class.
     *
     * @param tasks an ArrayList of task that contains previously saved tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.counter = tasks.size();
    }

    /**
     * Adds a Todo to tasks if the Todo does not already exist.
     *
     * @param description description of the Todo.
     * @return a message to show whether the Todo is successfully added or not.
     */
    public String addNewTodo(String description) {
        Todo todo = new Todo(description);
        if (this.detectDuplicatedTask(todo)) {
            return Ui.showTaskAlreadyExist();
        } else {
            tasks.add(todo);
            this.counter++;
            return Ui.showTaskAdded(todo, counter);
        }
    }

    /**
     * Adds a Deadline to tasks if the Deadline does not already exist.
     *
     * @param description description of the Deadline.
     * @param by date of the Deadline.
     * @return a message to show whether the Deadline is successfully added or not.
     */
    public String addNewDeadline(String description, LocalDate by) {
        Deadline deadline = new Deadline(description, by);
        if (this.detectDuplicatedTask(deadline)) {
            return Ui.showTaskAlreadyExist();
        } else {
            tasks.add(deadline);
            this.counter++;
            return Ui.showTaskAdded(deadline, counter);
        }
    }

    /**
     * Adds an Event to tasks if the Event does not already exist.
     *
     * @param description description of the Event.
     * @param from start date of Event.
     * @param to end date of Event.
     * @return a message to show whether the Event is successfully added or not.
     */
    public String addNewEvent(String description, LocalDate from, LocalDate to) {
        Event event = new Event(description, from, to);
        if (this.detectDuplicatedTask(event)) {
            return Ui.showTaskAlreadyExist();
        } else {
            tasks.add(event);
            this.counter++;
            return Ui.showTaskAdded(event, counter);
        }
    }

    /**
     * Checks if the newly added task already exist
     *
     * @param newTask task that is newly added.
     * @return true if the new task already exists in tasks.
     */
    private boolean detectDuplicatedTask(Task newTask) {
        for (Task task : tasks) {
            if (newTask.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lists out everything in tasks.
     *
     * @return a String containing all the tasks.
     */
    public String showTaskList() {
        return Ui.showTaskList(this.tasks);
    }

    /**
     * Deletes a Task from tasks.
     *
     * @param taskNumber the number representing the task being deleted.
     * @return a message to show task is successfully deleted.
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available.
     */
    public String deleteTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter || taskNumber <= 0) {
            throw new TaskNotFoundException("Task " + taskNumber +" does not exist");
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        counter--;
        return Ui.showTaskDeleted(removedTask, this.counter);
    }

    /**
     * Marks a Task in tasks.
     *
     * @param taskNumber the number representing the task being marked.
     * @return a message to show task is successfully marked.
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available.
     */
    public String markTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter || taskNumber <= 0) {
            throw new TaskNotFoundException("Task " + taskNumber +" does not exist");
        }
        Task task = tasks.get(taskNumber - 1);
        task.setDone(true);
        return Ui.showTaskMarked(task, true);
    }

    /**
     * Unmarks a Task in tasks.
     *
     * @param taskNumber the number representing the task being unmarked.
     * @return a message to show task is successfully unmarked.
     * @throws TaskNotFoundException when taskNumber exceeds the total number of tasks available.
     */
    public String unmarkTask(int taskNumber) throws TaskNotFoundException {
        if (taskNumber > this.counter || taskNumber <= 0) {
            throw new TaskNotFoundException("Task " + taskNumber +" does not exist");
        }
        Task task = tasks.get(taskNumber - 1);
        task.setDone(false);
        return Ui.showTaskMarked(task, false);
    }

    /**
     * Lists out tasks that contains the keyword.
     *
     * @param keyword the word used for filtering tasks.
     * @return a String containing all the filtered tasks.
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
