public abstract class Task {
//    private TaskTypes _type;
//    public enum TaskTypes {
//        ToDos,
//        Deadlines,
//        Events,
//        Unknown
//
//    }
//    public String getTaskType() {
//        switch (_type) {
//            case ToDos: return "T";
//            case Deadlines: return "D";
//            case Events: return "E";
//            case Unknown: return " ";
//        }
//        return " ";
//    }

    private final String description;

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUnDone() {
        isDone = false;
    }

    private boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;

    }
}