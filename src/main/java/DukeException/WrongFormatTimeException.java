package DukeException;


public class WrongFormatTimeException extends DukeException {
    public WrongFormatTimeException() {}

    @Override
    public String toString() {
        return "Input String is not correct format for time\n";
    }
}
