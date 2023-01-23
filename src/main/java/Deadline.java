public class Deadline extends Task {
    String dueDate;

    // Factory method
    public static Deadline create(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String[] parseInfo = parseCmd(commandInput);
        return new Deadline(parseInfo[0], parseInfo[1]);
    }

    public static String[] parseCmd(String commandInput) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        String taskName;
        String dueDate;

        int indexOfBy = commandInput.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DeadlineByNotSpecified("Deadline task requires keyword '/by'");
        } 

        taskName = commandInput.substring(9, indexOfBy - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("Deadline description canont be empty.");
        }

        try {
            dueDate = commandInput.substring(indexOfBy + 4);
            if (dueDate.equals("")) {
                throw new DeadlineByNotSpecified("Due date field cannot be empty");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeadlineByNotSpecified("Due date field cannot be empty.");
        }

        String[] parseInfo = {taskName, dueDate};
        return parseInfo;

    }
    
    public Deadline(String taskName, String dueDate) {
        super(taskName, "D");
        this.dueDate = dueDate;
    }

    @Override
    public String stringFields() {
        return " (by: " + dueDate + ")"; 
    }
}
