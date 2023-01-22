package duke;
class DeadlineByNotSpecified extends TaskInitError {
    public DeadlineByNotSpecified(String errorMessage) {
        super(errorMessage);
    }
}