import java.util.Locale;

public abstract class Task {
    private final String taskStr;
    private boolean done;
    enum Type {
        TODO,
        EVENT,
        DEADLINE
    }

    public Task(String taskStr) {
        this.taskStr = taskStr;
        done = false;
    }

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

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getStatusIcon() {
        return done ? ConsoleColors.GREEN + "✓" + ConsoleColors.RESET : " ";
    }
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskStr;
    }
}
