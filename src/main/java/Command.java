import java.util.Arrays;

public class Command {
    private final String fullCommand;
    public final String baseCommand;
    public final String[] parameters;

    public Command(String commandString) {
        this.fullCommand = commandString;
        String[] splitCommand = commandString.split(" ");
        this.baseCommand = splitCommand[0];
        this.parameters = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);
    }

    @Override
    public String toString() {
        return fullCommand;
    }
}
