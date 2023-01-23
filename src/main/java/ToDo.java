public class ToDo extends Task {

    // Factory method
    public static ToDo create(String commandInput) throws TaskNameNotSpecified {
        try {
            return new ToDo(parseCmd(commandInput)[1]);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskNameNotSpecified("ToDo description cannot be empty.");
        }
    }

    public static String[] parseCmd(String commandInput) {
        return commandInput.split(" ");
    }
    
    public ToDo(String taskName) {
        super(taskName, "T");
    }

    @Override
    public String stringFields() {
        return "";
    }
}