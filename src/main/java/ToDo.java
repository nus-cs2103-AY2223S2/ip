public class ToDo extends Task {
    public ToDo(String title) {
        super(title);
    }

    public ToDo(String title, boolean isDone) { super(title, isDone); }

    @Override
    public String toDiskFormat() {
        return String.format("T|%d|%s", super.getIsDone() ? 1 : 0, super.getTitle());
    }

    @Override
    public String toString() {
        return String.format("[%s][T] %s", super.getIsDone() ? "X" : " ", super.getTitle());
    }
}
