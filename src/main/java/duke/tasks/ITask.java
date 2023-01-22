package duke.tasks;

public abstract class ITask {
    public enum TaskTypes {
        ToDos,
        Deadlines,
        Events,
        Unknown

    }
//    public String convertTaskTypeEnumToIcon() {
//        switch (_type) {
//            case ToDos: return "T";
//            case Deadlines: return "D";
//            case Events: return "E";
//            case Unknown: return " ";
//        }
//        return " ";
//    }
//    public static String convertTaskTypeEnumToStr(ITask.TaskTypes type) {
//        switch (type) {
//            case ToDos: return "todo";
//            case Deadlines: return "deadline";
//            case Events: return "event";
//            case Unknown: return "unknown";
//        }
//        return "unknown";
//    }

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