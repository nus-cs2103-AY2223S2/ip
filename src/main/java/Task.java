public class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String mark() {
        this.done = true;
        return ("NOM NOM NOM! I've marked this task as done:\n" + this.toString());
    }

    public String unmark() {
        this.done = false;
        return ("NOM NOM NOM! I've marked this task as not done yet:\n" + this.toString());
    }

    @Override
    public String toString() {
        if (!done) {
            return ("[ ] " + name);
        } else {
            return ("[X] " + name);
        }
    }
}
