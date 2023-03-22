package DukeException;

public class TaskFormatException extends DukeException {
    public TaskFormatException(){}

    @Override
    public String toString() {
        return "Sorry, the format of task is incorrect\n";
    }
}
