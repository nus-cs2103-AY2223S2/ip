public class DeleteOutOfIndexException extends DukeException{
    public DeleteOutOfIndexException (Throwable err) {
        super("Sorry! You selected a number that does not exists on the list.", err);
    }
}
