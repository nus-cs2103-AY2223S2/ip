public class Task {

    private int id;
    private String task;
    private boolean done;

    public Task(int id, String task) {
        this.id = id;
        this.task = task;
        this.done = false;
    }

    public String printTask() {
        return task;
    }

    public String printDone() {
        return done ? "[x]" : "[ ]";
    }

    public void markDone() {
        this.done = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  [x]: " + printTask());
    }

    public void unmark() {
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  [ ]: " + printTask());
        this.done = false;
    }

    public int getId() {
        return this.id;
    }
}
