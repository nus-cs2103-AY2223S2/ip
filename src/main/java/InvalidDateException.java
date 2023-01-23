public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Invalid Date Format, please input it as YYYY-MM-DD;\nFor events, make sure start date <= end date!");
    }
}