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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;

        Command command = (Command) o;

        if (!description.equals(command.description)) return false;
        return arguments.equals(command.arguments);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + arguments.hashCode();
        return result;
    }
}
