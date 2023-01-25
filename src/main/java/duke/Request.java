import javax.smartcardio.CommandAPDU;

public abstract class Request {
    public enum Commands {
        LIST, UNMARK, MARK, TODO, DEADLINE, EVENT, DELETE, NULL
    };

    String[] values;
    Commands command;

    abstract public String[] unwrwap();

    public Request(Commands command, String request) {
        this.values = request.split(" ");
        this.command = command;
    }

    public Commands getCommand() {
        return this.command;
    }
}
