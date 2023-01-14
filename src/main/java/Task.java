public class Task {
    private String content;

    Task(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
