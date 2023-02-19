package duke;

import java.util.Arrays;

public class Command {
    private final String description;
    private final String[] arguments;

    /**
     * Constructs a Command with the specified description and any necessary arguments.
     * 
     * @param description The Command description (eg: todo, list, mark, etc)
     * @param arguments The necessary arguments for the command
     *                  (eg: command "deadline" can have argument "11/02/2023 1200"
     */
    public Command(String description, String ... arguments) {
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
    public String[] getArguments() {
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Command command = (Command) o;

        if (!description.equals(command.description)) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(arguments, command.getArguments());
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }

    @Override
    public String toString() {
        return "Command{" +
                "description='" + description + '\'' +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}
