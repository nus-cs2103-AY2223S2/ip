package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    private static final String PREFIX = "G";


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    };

    public boolean mark() {
        isDone = !isDone;
        return isDone;
    }

    public void importMark() {
        isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String save() {
        StringBuilder response = new StringBuilder("");
        response.append(PREFIX + ",");
        response.append(isDone + ",");
        response.append(description + "\n");
        return response.toString();
    }

    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String toString() {
        return isDone
                ? "[" + getPrefix() + "]" + "[X] " + this.description
                : "[" + getPrefix() + "]" + "[ ] " + this.description;
    }
}
