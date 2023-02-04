package duke;

public class WrongFormatTimeException extends DukeException {
    WrongFormatTimeException() {}

    @Override
    public String toString() {
        return "Input String is not correct format for time\n";
    }
}
