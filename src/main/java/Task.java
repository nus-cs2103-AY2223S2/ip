public class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) throws MissingDescriptionException {
        if (description == "" || description == " ") {
            throw new MissingDescriptionException();
        } else {
            this.description = description;
            this.isDone = false;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}
