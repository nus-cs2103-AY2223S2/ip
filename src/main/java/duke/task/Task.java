package duke.task;

/**
 * A class for a checklist.
 */
public class Task {
    /** Number of tasks in the list currently. **/
    public static int total = 0;
    private boolean isDone;
    private String name;

    /**
     * Constructor of Task.
     *
     * @param name name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.total++;
    }

    /**
     * Constructor of Task.
     *
     * @param name name of the task.
     * @param isDone indicates whether the task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.total++;
    }

    /**
     * Returns the status and name of the Task.
     *
     * @return a string to state the Task as done.
     */
    public String setDone() {
        this.isDone = true;
        return ("NOM NOM NOM! I've marked this task as done:\n" + this.toString());
    }

    /**
     * Returns the status and name of the uncompleted Task.
     *
     * @return a string to state Task is not done.
     */
    public String setNotDone() {
        this.isDone = false;
        return ("NOM NOM NOM! I've marked this task as not done yet:\n" + this.toString());
    }

    /**
     * Returns the status and name of the Task.
     *
     * @return a string regarding the status of the Task.
     */
    public String status() {
        if (!isDone) {
            return ("[ ]" + name);
        } else {
            return ("[X]" + name);
        }
    }

    /**
     * Returns a string after a removal of a task.
     *
     * @return a string after removal of a task.
     */
    public String removeTask() {
        return "duke.task.Task has been removed";
    }
}
