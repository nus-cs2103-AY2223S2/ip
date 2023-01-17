import java.util.Locale;

public abstract class Task {
    private final String taskStr;
    private boolean done;
    enum Type {
        TODO,
        EVENT,
        DEADLINE
    }

    /**
     * Constructor for a Task
     * Since Task is an abstract class this should NOT be called directory.
     * @param taskStr is the description of the task
     */
    public Task(String taskStr) {
        this.taskStr = taskStr;
        done = false;
    }

    /**
     * Factory builder for different types of tasks.
     * @param command is the input typed in by the user
     * @return the relevant Task subclass
     * @throws CatBotException if the input is malformed
     */
    public static Task fromCommand(String command) throws CatBotException {
        String[] cmd = command.split(" ", 2);
        if (cmd.length == 1) {
            throw new CatBotException("That's the wrong format!");
        }
        String[] temp;
        switch (cmd[0].toLowerCase(Locale.ROOT)) {
            case "todo":
                return new ToDo(cmd[1].strip());

            case "deadline":
                temp = cmd[1].split("/by", 2);
                if (temp.length != 2) {
                    throw new CatBotException("That's the wrong format!");
                }
                return new Deadline(temp[0].strip(), temp[1].strip());

            case "event":
                temp = cmd[1].split("/from|/to", 3);
                if (temp.length != 3) {
                    throw new CatBotException("That's the wrong format!");
                }
                return new Event(temp[0].strip(), temp[1].strip(), temp[2].strip());

            default:
                throw new CatBotException("I don't know what you mean >@w@<");
        }
    }

    /**
     * Setter for done
     * @param done is whether the task is marked as done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Internal method for getting the icon for a marked task
     * @return a string that should be placed in the slot indicating whether this task is marked
     */
    public String getStatusIcon() {
        return done ? ConsoleColors.GREEN + "✓" + ConsoleColors.RESET : " ";
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskStr;
    }
}
