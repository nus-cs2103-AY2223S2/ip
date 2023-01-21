package Task;

public abstract class Task {
    private boolean marked;
    private String content;

    public Task(String content) {
        this.marked = false;
        this.content = content;
    }

    public static Task create(char taskType, String content) {
        /**
         * @param taskType whether it is a Task.Todo, Task.Deadline or Task.Event.
         * @param content what to put in the task.
         * @returns the output Task.Task object.
         */
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

    public void mark(boolean isToMark) {
        /**
         * @param isToMark whether to mark or unmark the task.
         */
        this.marked = isToMark;
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

    public abstract String toStorageString();
}
