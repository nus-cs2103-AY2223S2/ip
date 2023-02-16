package duke.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A todo task object that stores its description and on whether is it done or not. The task can be marked as done or
 * unmarked as not done.
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task with the given description.
     *
     * @param description The description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for a todo task with the provided description and its status of completion.
     *
     * @param description The description of the todo task
     * @param isMarked    Whether the todo task is done or not
     */
    public Todo(String description, boolean isMarked) {
        this(description);
        this.isDone = isMarked;
    }

    /**
     * Checks on whether the string representation of the todo task is the same format as the one exported. If it is,
     * then a new task is created with the described properties. Otherwise, return an empty task.
     *
     * @param data String representation of a todo task
     * @return A todo task object that describes the given data of the task
     */
    public static Task readFromData(String data) {
        Pattern pattern = Pattern.compile("(marked:) (.*) ; (description:) (.*)");
        Matcher matcher = pattern.matcher(data);
        if (matcher.matches()) {
            boolean isMarked = matcher.group(2).equals("1");
            String description = matcher.group(4);
            return new Todo(description, isMarked);
        }

        //noinspection AssertWithSideEffects
        assert !matcher.matches();

        return Task.EMPTY_TASK;
    }

    /**
     * Returns the string representation of the todo object. The details of its description and completeness of the
     * task is included.
     *
     * @return String representation of the event object for display
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Converts the todo object into a string representation that can be processed and imported back as a todo object
     * in the future. The details of its descirption and completeness is included.
     *
     * @return String representation of event object for storage
     */
    @Override
    public String toData() {
        return String.format("Todo | marked: %s ; description: %s", getMarkedStatus(), description);
    }
}
