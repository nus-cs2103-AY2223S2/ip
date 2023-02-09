package leo.storage;

import leo.leoexception.IncorrectMarkException;
import leo.leoexception.IncorrectUnmarkException;
import leo.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a Task. A <code>Task</code> object contains the String description.
 */
public class Task {

    private final String task;
    private boolean done;
    private TaskType type;

    /**
     * Constructor for creating a Task object.
     *
     * @param task Description of the Task.
     */
    public Task(String task) {
        this.task = task;
        this.done = false;
        this.type = TaskType.TODO;
    }

    /**
     * Returns the description of Task.
     *
     * @return Description of Task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns completion status of Task.
     *
     * @return Status of Task.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Marks Task that is done.
     *
     * @throws IncorrectMarkException If task is marked.
     */
    public void mark() throws IncorrectMarkException {
        if (this.done) {
            throw new IncorrectMarkException();
        }
        this.done = true;
    }

    /**
     * Unmarks Task that is not done.
     *
     * @throws IncorrectUnmarkException If task is not marked.
     */
    public void unmark() throws IncorrectUnmarkException {
        if (!this.done) {
            throw new IncorrectUnmarkException();
        }
        this.done = false;
    }

    /**
     * Returns the String representation of Task that is to be saved in the data file.
     *
     * @return String representation of Task.
     */
    public String saveFormat() {
        return task;
    }

    /**
     * Returns the TaskType of Task.
     *
     * @return TaskType of Task.
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Set the TaskType of Task.
     *
     * @param tt TaskType to be set to the Task.
     */
    public void setType(TaskType tt) {
        this.type = tt;
    }

    /**
     * Returns the TaskType and status of Task in String representation.
     *
     * @return String representation of TaskType and status.
     */
    public String typeAndStatus() {
        return Ui.type(this) + Ui.completion(this);
    }
}
