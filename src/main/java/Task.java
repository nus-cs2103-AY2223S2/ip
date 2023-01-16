public abstract class Task {

    private boolean marked;
    private String content;

    public Task(String content) {
        this.marked = false;
        this.content = content;
    }

    public static Task create(char taskType, String content) {
        /**
         * @param taskType whether it is a Todo, Deadline or Event.
         * @param content what to put in the task.
         * @returns the output Task object.
         */
        String[] taskTypeAndContent = content.split(" ", 2);
        if (taskTypeAndContent.length <= 1) {
            throw new InputFormatException("Task Creation", "No further information was provided.", null);
        }
        switch (taskType) {
            case 'T':
                return Todo.create(taskTypeAndContent[1]);
            case 'D':
                return Deadline.create(taskTypeAndContent[1]);
            case 'E':
                return Event.create(taskTypeAndContent[1]);
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
