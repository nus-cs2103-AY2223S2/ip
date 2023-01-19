public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
     }

    public static Task of(String s) {
        String[] task = s.split(" ");
        String type = task[0];
        switch (type) {
            case "deadline":
                String appendDeadline = s.substring(9);
                String[] deadlineDetails = appendDeadline.split("/by");
                return new Deadline(deadlineDetails[0], deadlineDetails[1]);
            case "event":
                String appendEvent = s.substring(6);
                String[] eventDetails = appendEvent.split("/from");
                String[] toFrom = eventDetails[1].split("/to");
                return new Event(eventDetails[0], toFrom[0], toFrom[1]);
            default:
                String description = s.substring(5);
                return new Todo(description);
        }
    }
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}
