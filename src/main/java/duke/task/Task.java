package duke.task;
import duke.exception.DukeException;

public abstract class Task {
    protected String taskName;
    protected String type;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String getTaskName(){
        return this.taskName;
    }

    abstract public String encode();

    public static Task decode(String str) throws DukeException {
        String[] splitStr = str.split(" \\| ", 5);
        Task result = null;
        switch(splitStr[0]) {
            case "T":
                result = new ToDo(splitStr[2]);
                break;
            case "D":
                result = new Deadline(splitStr[2], splitStr[3]);
                break;
            case "E":
                result = new Event(splitStr[2], splitStr[3], splitStr[4]);
                break;
        }
        if (splitStr[1].equals("true")) {
            result.markDone();
        }
        return result;
    }


    @Override
    public String toString() {
        return String.format("%s %s", this.isDone ? "[X]" : "[ ]", this.taskName);
    }
}