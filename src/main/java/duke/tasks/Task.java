package duke.tasks;

import duke.dukeexceptions.DukeExceptions;

/**
 * A task class that specific task inherit from.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task with a description
     *
     * @param description the desctiption of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string denoting whether the task is done
     *
     * @return "X" for task that are done and "" for task that are not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets a task to be done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets a task to be not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * retrieves the description of the task.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * formats the task into a string to be saved locally
     */
    public abstract String saveTask();

    /**
     * decodes a task that has been formatted to be saved locally back into a task.
     *
     * @return the task that has been decoded
     */
    public static Task decode(String taskString) throws DukeExceptions {
        String[] taskStringSplit = taskString.split(" \\| ", 4);
        //temporarily set the currTask to null.
        Task currTask = null;

        switch(taskStringSplit[0]) {
        case "T":
            currTask = new ToDo(taskStringSplit[2]);
            break;

        case "D":
            currTask = new Deadline(taskStringSplit[2], taskStringSplit[3]);
            break;

        case "E":
            String[] fromToSplit = taskStringSplit[3].split(" - ");
            currTask = new Event(taskStringSplit[2], fromToSplit[0], fromToSplit[1]);
            break;

        default:
            throw new DukeExceptions("Loading of file failed.");
        }

        if (taskStringSplit[1].equals("1")) {
            currTask.setDone();
        }
        return currTask;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone && description.equals(task.description);
    }
}

