package duke.task;

import duke.TaskType;

public class ToDo extends Task {
    private String description;

    private ToDo(String description) {
        super(TaskType.TODO, description);
        this.description = description;
    }

    public static ToDo to(String str) {
        return new ToDo(str);
    }

    @Override
    public String taskToSavedForm() {
        String marked = super.getStatusIcon() == "X" ? "1" : "0";
        return "todo " + this.description + marked;
    }

    @Override
    public String toString() {
        String str = super.toString();
        //str += "\n";
        return str;
    }
}
