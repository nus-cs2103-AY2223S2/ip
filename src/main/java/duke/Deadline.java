package duke;
class Deadline extends Task {
    String dueDate;

    // Factory method
    public static Deadline create(String command) throws TaskNameNotSpecified, DeadlineByNotSpecified {
        int indexOfBy = command.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DeadlineByNotSpecified("Deadline task requires keyword '/by'");
        } 

        String taskName = command.substring(9, indexOfBy - 1);
        if (taskName.equals("")) {
            throw new TaskNameNotSpecified("Deadline description canont be empty.");
        }

        try {
            String dueDate = command.substring(indexOfBy + 4);
            if (dueDate.equals("")) {
                throw new DeadlineByNotSpecified("Due date field cannot be empty");
            }
            return new Deadline(taskName, dueDate);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeadlineByNotSpecified("Due date field cannot be empty.");
        }

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