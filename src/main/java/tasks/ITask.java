package tasks;

public abstract class ITask {
    final TaskTypes _type;
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
    public static String convertTaskTypeEnumToStr(ITask.TaskTypes type) {
        switch (type) {
            case ToDos: return "todo";
            case Deadlines: return "deadline";
            case Events: return "event";
            case Unknown: return "unknown";
        }
        return "unknown";
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


    public ITask(String description, ITask.TaskTypes type) {
        this.description = description;
        this.isDone = false;
        _type = type;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;

    }
}