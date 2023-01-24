package model;

public class ToDo extends Task {
    private final String taskDescription;
    public ToDo(String description) {
        this.taskDescription = description;
    }
    @Override
    public String getDescription() {
        return this.taskDescription;
    }
}
