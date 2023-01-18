public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public void makeCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        String completedString = "";
        if (this.completed) {
            completedString += "[X] ";
        } else {
            completedString += "[ ] ";
        }

        completedString += this.name;

        return completedString;
    }
}
