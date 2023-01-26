package duke;

import java.util.List;

public class Command {
    private final String description;
    private final List<String> arguments;

    public Command(String description, List<String> arguments) {
        this.description = description;
        this.arguments = arguments;
    }
    public String getDescription() {
        return description;
    }
    public List<String> getArguments() {
        return arguments;
    }
}
