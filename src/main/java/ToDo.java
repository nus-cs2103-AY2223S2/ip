public class ToDo extends Task {

    // Factory method
    public static ToDo create(String commandInput) throws TaskNameNotSpecified {
        try {
            return new ToDo(parseCmd(commandInput)[0], false);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskNameNotSpecified("ToDo description cannot be empty.");
        }
    }

    public static String[] parseCmd(String commandInput) {
        String[] parseInfo = {commandInput.substring(5)};
        return parseInfo;
    }

    public ToDo(String taskName, boolean isDone) {
        super(taskName, "T");
        this.completed = isDone;
    }

    @Override
    public String stringFields() {
        return "";
    }
}