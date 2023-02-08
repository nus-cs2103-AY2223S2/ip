package berry.task;

/**
 * Represents a task class.
 */
public abstract class Task {
    private static int NUMBER_VAR_IN_STORAGE_TODO = 3;
    private static int NUMBER_VAR_IN_STORAGE_DEADLINE = 4;
    private static int NUMBER_VAR_IN_STORAGE_EVENT = 5;

    // Indicates if the task is done
    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Marks a task as not done.
     */
    public String markAsNotDone() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Returns a representative string to indicate its isDone status.
     *
     * @return a string representing its isDone status
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /** Checks if the task description contains the keyword.
     *
     *  @param keyword the string to check if description contains it
     * @return true if keyword is contained
     */
    public boolean hasKeyword(String ... keyword) {
        for (String word : keyword) {
            if (description.contains(word)) {
                return true;

            }
        }
        return false;
    }

    /**
     * Translates a task to a formatted string to be saved into a file.
     *
     * @return a formatted string based on its task type
     */
    public abstract String interpretTaskToText();

    /**
     * Translates a given string in a certain format to a task variable.
     *
     * @param s is the line read from the file
     * @return a task class which is interpreted based on the file input
     */
    public static Task interpretTextToTask(String s) {
        String isDoneStatus = s.split(" \\| ")[1];
        boolean isDone = isDoneStatus.equals("X") ? true : false;
        if (s.split(" \\| ").length == NUMBER_VAR_IN_STORAGE_TODO) {
            return new Todo(s.split(" \\| ")[2], isDone);
        } else if (s.split(" \\| ").length == NUMBER_VAR_IN_STORAGE_DEADLINE) {
            return new Deadline(s.split(" \\| ")[2], isDone, s.split(" \\| ")[3]);
        } else if (s.split(" \\| ").length == NUMBER_VAR_IN_STORAGE_EVENT) {
            return new Event(s.split(" \\| ")[2], isDone, s.split(" \\| ")[3], s.split(" \\| ")[4]);
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}