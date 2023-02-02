package duke;

import java.util.List;

public class Command {
    private final String description;
    private final List<String> arguments;

    /**
     * Constructs a Command with the specified description and any necessary arguments.
     * 
     * @param description The Command description (eg: todo, list, mark, etc)
     * @param arguments The necessary arguments for the command
     *                  (eg: command "deadline" can have argument "11/02/2023 1200"
     */
    public Command(String description, List<String> arguments) {
        this.description = description;
        this.arguments = arguments;
    }

    /**
     * Returns the description of the command as a String
     * @return Description of the command (eg: todo, list, mark, etc)
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the arguments of the command in an ArrayList
     * @return Arguments of the command
     */
    public List<String> getArguments() {
        return arguments;
    }

    /**
     * Compares if another object o is equal to this command.
     * Returns true if and only if both command description and arguments are equal.
     * @param o The object to be compared for equality with this command
     * @return true if the specified object is equal to this command
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;

        Command command = (Command) o;

        if (!description.equals(command.description)) return false;
        return arguments.equals(command.arguments);
    }

    /**
     * Returns the hash code value for this command.
     * @return The hash code value for this command.
     */
    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + arguments.hashCode();
        return result;
    }
}
