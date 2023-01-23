public class InvalidTaskException extends DukeException {

    public InvalidTaskException(int taskNumber) {
        super("Sorry, the task number " + taskNumber + " has not been added/cannot be added! Please try again");
    }

    @Override
    public String getExceptionType() {
        return "Invalid task number";
    }
}
