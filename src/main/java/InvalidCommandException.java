public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Command not recognised. Please input a valid command");
    }
}
