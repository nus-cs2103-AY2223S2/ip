public abstract class Task {

    protected final String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task getTaskFromString(String task) {
        String[] taskParts = task.split("~");
        String taskType = taskParts[0];
        String mark = taskParts[1];
        String description = taskParts[2];

        Task answer = null;

        if (taskType.equals("T")) {
            answer = new ToDo(description);

        } else if (taskType.equals("D")) {
            answer = new Deadline(description, taskParts[3]);

        } else if (taskType.equals("E")){
            answer = new Event(description, taskParts[3], taskParts[4]);

        }

        if (answer != null && mark.equals("X")) {
            answer.mark();

        }

        return answer;

    }

    public void mark() {
        this.isDone = true;
    }
    
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "X" : " ";
        return "[" + mark + "] " + this.description;
    }

    public abstract String getFileRepresentation();

}
