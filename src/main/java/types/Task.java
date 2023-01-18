package types;

public class Task {
    private final String name;

    private Task(String s) {
        this.name = s;
    }

    public static Task ofName(String s) {
        return new Task(s);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
