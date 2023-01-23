package sebastian.task;

public class Todo extends Task{
    private static final String taskType = "T";

    public Todo(int isCompleted, String taskDescription) {
        super(taskDescription, isCompleted);
    }

    @Override
    public String toString() {
        return "["+ taskType +"]" + super.toString();
    }

    /**
     * Format the to-do into a suitable String representation to be written to the hard disk
     * @return the formatted String representation
     */
    @Override
    public String formatForSave() {
        return taskType + "<>" + super.formatForSave();
    }
}

