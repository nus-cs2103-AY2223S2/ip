package berry.task;

public abstract class Task {

    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println(this.toString());
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String interpretTaskToString();

    public static Task interpretStringToTask(String s) {
        String isDoneStatus = s.split(" \\| ")[1];
        boolean isDone = isDoneStatus.equals("X") ? true : false;
        if (s.split(" \\| ").length == 3) {
            return new Todo(s.split(" \\| ")[2], isDone);
        } else if (s.split(" \\| ").length == 4) {
            return new Deadline(s.split(" \\| ")[2], isDone, s.split(" \\| ")[3]);
        } else {
            return new Event(s.split(" \\| ")[2], isDone, s.split(" \\| ")[3], s.split(" \\| ")[4]);
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}