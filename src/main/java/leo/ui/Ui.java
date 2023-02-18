package leo.ui;

import leo.storage.Task;

/**
 * Customise the user interface of Leo.
 */
public class Ui {

    /**
     * Returns String representation of TaskType.
     *
     * @param t Task to convert TaskType from.
     * @return String representation of TaskType.
     */
    public static String type(Task t) {
        switch (t.getType()) {
        case TODO:
            return "[T]";
        case DEADLINE:
            return "[D]";
        case EVENT:
            return "[E]";
        default:
            return null;
        }
    }

    /**
     * Returns String representation of status of Task.
     *
     * @param t Task to retrieve completion status from.
     * @return String representation of done status.
     */
    public static String completion(Task t) {
        return (t.isDone() ? "[X] " : "[ ] ");
    }

}
