public class Task {
    protected final String value;

    public Task(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
