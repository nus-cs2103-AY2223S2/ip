public class CommandNotFoundException extends DukeException{
    String command;

    CommandNotFoundException(String errorMessage, String command) {
        super(errorMessage);
        this.command = command;
    }
}
