package duke.tasks;

public abstract class ITask {
    public enum TaskTypes {
        ToDos,
        Deadlines,
        Events,
        Unknown

    }

    public static ITask.TaskTypes convertTaskTypeCmdToEnum(String cmd) {
        switch (cmd) {
            case "todo": return TaskTypes.ToDos;
            case "deadline": return TaskTypes.Deadlines;
            case "event": return TaskTypes.Events;
        }
        return TaskTypes.Unknown;
    }



    private final String description;

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUnDone() {
        isDone = false;
    }

    private boolean isDone;


    public ITask(String description) {
        this.description = description;
        this.isDone = false;
    }
    public ITask(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    abstract public String toSaveFormat();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;

    }
}