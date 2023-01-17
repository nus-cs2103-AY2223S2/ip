public class Command {
    private final String fullCommand;
    public final String baseCommand;
    public final String body;

    public Command(String commandString) {
        this.fullCommand = commandString;
        String[] temp = commandString.split(" ", 2);
        this.baseCommand = temp[0];
        this.body = temp.length > 1 ? temp[1] : "";
    }

    @Override
    public String toString() {
        return fullCommand;
    }
}
