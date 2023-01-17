public class SelectOutOfIndexException extends DukeException{
    public SelectOutOfIndexException (Throwable err) {
        super("Sorry! You selected a number that does not exists on the list.", err);
    }
}
