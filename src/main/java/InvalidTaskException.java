public class InvalidTaskException extends PeppaException {
    public InvalidTaskException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String commands = "";
        for (String command : Peppa.commands) {
            commands += "\n> " + command;
        }
        return super.getMessage() + commands;
    }
}
