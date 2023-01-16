public class Task {
    private String taskName;
    private boolean tag;

    public Task(String taskName) {
        this.taskName = taskName;
        this.tag = false;
    }
 
    public void mark() {
        this.tag = true;
        System.out.println("Duke: Nice! I've marked this task as done:" + "\n" + this.tag() + " " + taskName);
    }

    public void unmark() {
        this.tag = false;
        System.out.println("Duke: Ok! I've marked this task as not done yet:" + "\n" + this.tag() + " " + taskName);
    }

    protected String tag() {
        if(this.tag) {
            return "[X]";
        }
        else {
            return "[]";
        }
    }

    @Override
    public String toString() {
        return this.tag() + " " + taskName;
    }
}
