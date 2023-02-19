package duke.exception;

abstract class DukeException extends Exception{
    protected String message;

    public String getMessage() {
        return this.message;
    }
}
