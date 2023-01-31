public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    public boolean emptyTask() {
        if (description.equals("")) {
            return true;
        } else return false;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I have marked this task as Done:\n" + "[X] " + this.getDescription() + "\n");
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("Ok! I have marked this task as not done yet:\n" + "[ ] " + this.getDescription() + "\n");
    }

    public String saveString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }
}
