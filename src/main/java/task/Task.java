package task;

public abstract class Task {
    private boolean isDone = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String getFileRepresentation();

    protected String getName() {
        return this.name;
    };

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.name;
    }

    public static Task createTaskFromStringRepresentation(String stringRepresentation) {
        char typeOfTask = stringRepresentation.charAt(0);
        String[] details = stringRepresentation.split("@");
        Task newTask = null;
        switch (typeOfTask) {
        case 'T':
            newTask = new Todo(details[2]);
            break;
        case 'E':
            newTask = new Event(details[2], details[3], details[4]);
            break;
        case 'D':
            newTask = new Deadline(details[2], details[3]);
            break;
        default:
            break;
        }
        newTask.setDone(details[1].equals("1"));
        return newTask;
    }

}
