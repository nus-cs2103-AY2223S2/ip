public abstract class Task {

    private boolean marked;
    private String content;

    public Task(String content) {
        this.marked = false;
        this.content = content;
    }

    public static Task create(String taskType, String content) {
        /**
         * @param taskType whether it is a Todo, Deadline or Event.
         * @param content what to put in the task.
         * @returns the output Task object.
         */
        String[] taskTypeAndContent = Parser.handleMissingField(content, " ","content", "Task Creation");
        switch (taskType) {
        case "todo":
            return Todo.create(taskTypeAndContent[1]);
        case "deadline":
            return Deadline.create(taskTypeAndContent[1]);
        case "event":
            return Event.create(taskTypeAndContent[1]);
        default:
            return null;
        }
    }



    public void mark(boolean isToMark) {
        /**
         * @param toMark whether to mark or unmark the task.
         */
        this.marked = isToMark;
        System.out.println(String.format("OK %smark for you already: ", isToMark ? "" : "un") + this);
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
