public class Task {

    private final String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
        System.out.println(this);
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println(this);
    }

    @Override
    public String toString() {
        String checkMark = isDone ? "x" : " ";
        return "[" + checkMark + "] " + this.name;
    }
}
