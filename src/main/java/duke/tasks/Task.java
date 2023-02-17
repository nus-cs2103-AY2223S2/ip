package duke.tasks;

import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;

/**
 * Represents a generic task class with a name and whether the task has been marked.
 * @author lukkesreysandeur
 */
public class Task {
    /** The name of the task. */
    protected String name;
    /** Boolean indicating if the task has been marked done. */
    protected boolean isDone;

    /**
     * Initilaises the task object.
     *
     * @param name A string representing the name of the task.
     */
    public Task(String name) {
        assert name != null;
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks a task as done; does nothing if it was already marked.
     *
     * @return Response indicating if the task was successfully marked done or
     *         if the task was already marked done.
     */
    public String markAsDone() {
        if (isDone) {
            return String.format("Perhaps you forgot, but this task was already marked done!:\n\t%s", this);
        }
        isDone = true;
        return String.format("Well done! I've marked this task as done:\n\t%s", this);
    }

    /**
     * Marks a task as not done.
     *
     * @return Response indicating if the task was successfully marked not done or
     *         if the task was already marked not done.
     */
    public String markNotDone() {
        if (!isDone) {
            return String.format("No need to tell me, the task was not even marked as done!:\n\t%s", this);
        }
        isDone = false;
        return String.format("Okay, I have marked this task as not done:\n\t%s", this);
    }

    /**
     * Returns a status icon indicating if the task has been marked done.
     *
     * @return X if the task has been done, a space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), name);
    }

    /**
     * Converts the task into a text format that can be saved to a text file.
     *
     * @return A string representing the task in a different format.
     */
    public String toText() {
        return String.format("! %d %s\n", isDone ? 1 : 0, name);
    }

    /**
     * Converts string that has been formatted in a certain way into a task.
     *
     * @param taskText The string representing the task.
     * @return The newly created task from the text.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    public static Task fromText(String taskText) throws DukeInvalidInputException, DukeEmptyInputException {
        String[] params = taskText.split(" ", 3);
        String type = params[0];
        String status = params[1];
        Task newTask;

        switch (type) {
        case "T":
            newTask = Todo.createTodo(params[2]);
            break;
        case "D":
            newTask = Deadline.createDeadline(params[2]);
            break;
        case "E":
            newTask = Event.createEvent(params[2]);
            break;
        default:
            System.out.println("Incorrect format");
            return null;
        }
        if (status.equals("1")) {
            newTask.isDone = true;
        }
        return newTask;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return name;
    }
}
