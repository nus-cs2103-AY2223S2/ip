public class DukeMissingEventDateException extends DukeException{
    public DukeMissingEventDateException() {
        super("\t OOPS!!! The start/end date of an event cannot be empty.");

    }
}