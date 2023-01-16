public abstract class Task {

    private boolean marked;
    private String content;

    public Task(String content) {
        this.marked = false;
        this.content = content;
    }

    public static Task create(char taskType, String content) {
        switch (taskType) {
            case 'T':
                return Todo.create(content);
            case 'D':
                return Deadline.create(content);
            case 'E':
                return Event.create(content);
            default:
                return null;
        }
    }

    public void mark(boolean toMark) {
        this.marked = toMark;
        System.out.println(String.format("OK %smarked for you already:", toMark ? "" : "un"));
        System.out.println(this);
    }

    public boolean isMarked() {
        return this.marked;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        String markedStatus = this.isMarked() ? "X" : " ";
        return String.format("[%s] %s", markedStatus, this.getContent());
    }
}
