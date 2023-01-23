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

    @Override
    public String formatForSave() {
        return taskType + "<>" + super.formatForSave();
    }
}

