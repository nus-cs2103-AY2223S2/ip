package duke;

public class TaskFormatException extends DukeException {
    TaskFormatException(){}

    @Override
    public String toString() {
        return "Sorry, the format of task is incorrect\n";
    }
}
