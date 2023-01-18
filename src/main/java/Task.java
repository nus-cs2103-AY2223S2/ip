public class Task {

    private final String name;
    private boolean done;
    private static int count = 0;

    public Task(String name) {
        this.name = name;
        done = false;
        count++;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public int numberTask() {
        return count;
    }

    @Override
    public String toString() {
        return done ? "[X] " + name
                    : "[ ] " + name;
    }

}
