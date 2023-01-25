package duke.exceptions;

public class DukeEmptyTaskException extends DukeException {
    public static enum TaskType {
        TYPE_Todo,
        TYPE_Deadline,
        TYPE_Event,
        TYPE_Unknown
    }

    public DukeEmptyTaskException(TaskType type) {
        super(String.format("The description of a %s cannot be empty.",
                type.toString().replace("TYPE_", "")));
    }
}
