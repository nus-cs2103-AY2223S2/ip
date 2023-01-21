public class Task {
    public static int total = 0;
    private boolean done;
    private String name;

    public Task(String name) {
        this.name = name;
        this.total++;
    }

    public String mark() {
        this.done = true;
        return ("NOM NOM NOM! I've marked this task as done:\n" + this.toString());
    }

    public String unmark() {
        this.done = false;
        return ("NOM NOM NOM! I've marked this task as not done yet:\n" + this.toString());
    }

    public String status() {
        if (!done) {
            return ("[ ]" + name);
        } else {
            return ("[X]" + name);
        }
    }

}
