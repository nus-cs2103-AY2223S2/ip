public class Task {
    private String name;
    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String s = "";
        s += this.name;
        return s;
    }
}
