package DukeExceptions;

public class DukeTaskIndexMissingException extends DukeException {

    public DukeTaskIndexMissingException(String type) {
        super(String.format(
                "An index was not provided for the %s action. Usage: %s {index}",
                type, type
        ));
    }
}
