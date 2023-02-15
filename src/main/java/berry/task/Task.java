package berry.task;

import java.util.HashSet;
import java.util.Set;

import berry.exception.IncorrectDateException;

/**
 * Represents a task class.
 */
public abstract class Task {
    private static int INDEX_COMMAND_TYPE = 0;
    private static int INDEX_ISDONE = 1;
    private static int INDEX_DESCRIPTION = 2;

    private static int INDEX_DEADLINE_BY = 3;
    private static int INDEX_EVENT_FROM = 3;
    private static int INDEX_EVENT_TO = 4;

    protected boolean isDone;
    protected String description;
    protected HashSet<String> tags;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = null;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.tags = null;
    }

    public Task(String description, HashSet<String> tags) {
        this.description = description;
        this.isDone = false;
        this.tags = tags;
    }

    public Task(String description, boolean isDone, HashSet<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Marks a task as done.
     */
    public String markTaskAsDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Marks a task as not done.
     */
    public String markTaskAsNotDone() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Returns a representative string to indicate its isDone status.
     *
     * @return a string representing its isDone status
     */
    public String getStatusIcon() {
        return isDone ? "X" : "  ";
    }

    public HashSet<String> getTags() {
        return tags;
    }

    /**
     * Checks if the task description contains the keyword.
     *
     * @param keyword the string to check if description contains it
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
     * Translates a line in the data file to a task variable.
     *
     * @param fileLine is the line read from the file
     * @return a task class which is interpreted based on the file input
     */
    public static Task interpretTextToTask(String fileLine) throws IncorrectDateException {
        String[] arrOfVariables = fileLine.split(" \\| "); // Splits up based on " | "
        String taskType = arrOfVariables[INDEX_COMMAND_TYPE].trim();
        String isDoneStatus = arrOfVariables[INDEX_ISDONE];
        boolean isDone = isDoneStatus.equals("X") ? true : false;

        if (containTags(fileLine)) {
            String[] arrOfDetailsAndTags = fileLine.split(" \\|t", 2);
            arrOfVariables = arrOfDetailsAndTags[0].split(" \\| ");
            HashSet<String> tags = new HashSet<String>(Set.of(arrOfDetailsAndTags[1].split(" #")));
            tags.remove(0); // Remove empty hash

            return interpretWithTags(taskType, arrOfVariables, tags, isDone);
        }
        return interpretWithoutTags(taskType, arrOfVariables, isDone);
    }

    /**
     * Interprets a line from the text file into a Task Object.
     *
     * @param taskType The type of {@code Command}
     * @param arrOfVariables An array of variables which was separated by " | " in the file line
     * @param tags A set of tags which was separated by " | " in the file line
     * @param isDone Indicates the isDone status of the {@code Task} object
     * @return a {@code Task} object as interpreted from the file line
     */
    public static Task interpretWithTags(String taskType, String[] arrOfVariables,
                                         HashSet<String> tags, boolean isDone) throws IncorrectDateException {
        if (taskType.equals("T")) {
            return new Todo(arrOfVariables[INDEX_DESCRIPTION], isDone, tags);
        } else if (taskType.equals("D")) {
            return new Deadline(arrOfVariables[INDEX_DESCRIPTION], isDone, arrOfVariables[INDEX_DEADLINE_BY], tags);
        } else if (taskType.equals("E")) {
            return new Event(arrOfVariables[INDEX_DESCRIPTION], isDone, arrOfVariables[INDEX_EVENT_FROM],
                    arrOfVariables[INDEX_EVENT_TO], tags);
        }
        assert false : "Unreadable contents from storage file."; // Code should not reach this line.
        return null;
    }

    /**
     * Interprets a line from the text file into a Task Object.
     *
     * @param taskType the type of {@code Command}
     * @param arrOfVariables an array of variables which was separated by " | " in the file line
     * @param isDone indicates the isDone status of the {@code Task} object
     * @return a {@code Task} object as interpreted from the file line
     */
    public static Task interpretWithoutTags(String taskType, String[] arrOfVariables, boolean isDone)
            throws IncorrectDateException {
        if (taskType.equals("T")) {
            return new Todo(arrOfVariables[INDEX_DESCRIPTION], isDone);
        } else if (taskType.equals("D")) {
            return new Deadline(arrOfVariables[INDEX_DESCRIPTION], isDone, arrOfVariables[INDEX_DEADLINE_BY]);
        } else if (taskType.equals("E")) {
            return new Event(arrOfVariables[INDEX_DESCRIPTION], isDone, arrOfVariables[INDEX_EVENT_FROM],
                    arrOfVariables[INDEX_EVENT_TO]);
        }
        assert false : "Unreadable contents from storage file."; // Code should not reach this line.
        return null;
    }

    /**
     * Checks if the file line contains tags for the tasks
     *
     * @param input line of input from the file
     * @return true if there are tags for the task
     */
    public static boolean containTags(String input) {
        return input.contains(" |t ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
