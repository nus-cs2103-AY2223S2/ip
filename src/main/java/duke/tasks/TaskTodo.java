package duke.tasks;

public class TaskTodo extends Task {
    public TaskTodo(String description) {
        super(description);
    }

    public static TaskTodo loadFromString(String input) {
        String[] values = Task.decodeValues(input);
        boolean isDone = values[1].equals("1");
        String description = values[2];

        TaskTodo output = new TaskTodo(description);
        if (isDone) {
            output.markAsDone();
        }
        return output;
    }

    @Override
    public String encodeAsString() {
        return Task.encodeValues(new String[]{ 
            "T", 
            this.isDone ? "1" : "0", 
            this.description
        });
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
