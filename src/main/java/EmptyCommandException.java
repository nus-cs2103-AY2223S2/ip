public class EmptyCommandException extends Exception {

    public EmptyCommandException(String taskType) {
            super("OOPS!!! The description of a " + taskType + " cannot be empty.");
    }

}
