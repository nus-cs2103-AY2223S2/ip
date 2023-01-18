public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        if (!isDone) {
            this.isDone = true;
            System.out.println("    ____________________________________________________________");
            System.out.println("        Nice! I've marked this task as done!");
            System.out.println(String.format("          [%s] %s", this.getStatusIcon(), this.description));
            System.out.println("    ____________________________________________________________");
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("        Task is already done sir!!");
            System.out.println("    ____________________________________________________________");
        }
    }

    public void markAsUndone() {
        if (isDone) {
            this.isDone = false;
            System.out.println("    ____________________________________________________________");
            System.out.println("        I've marked this task as undone, you lazy bum");
            System.out.println(String.format("          [%s] %s", this.getStatusIcon(), this.description));
            System.out.println("    ____________________________________________________________");
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("        you can't make an undone task more undone sir!!");
            System.out.println("    ____________________________________________________________");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
