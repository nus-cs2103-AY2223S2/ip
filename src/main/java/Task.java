public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void makeCompleted() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        String completedString = "";
        if (this.isCompleted) {
            completedString += "[X] ";
        } else {
            completedString += "[ ] ";
        }

        completedString += this.name;
        return completedString;
    }
    public String parse() {
        String completedString = "";
        if (this.isCompleted) {
            completedString += "[X] ";
        } else {
            completedString += "[ ] ";
        }

        completedString += this.name;
        return completedString;
    }

}
