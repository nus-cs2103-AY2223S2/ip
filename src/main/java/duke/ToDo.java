package duke;
class ToDo extends Task {

    // Factory method
    public static ToDo create(String command) throws TaskNameNotSpecified {
        try {
            return command.indexOf("todo") == -1 ? new ToDo(command) : new ToDo(command.substring(5));
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskNameNotSpecified("ToDo description cannot be empty.");
        }
    }
    
    public ToDo(String taskName) {
        super(taskName, "T");
    }

    @Override
    public String stringFields() {
        return "";
    }
}