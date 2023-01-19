public class Task {
    public String name;
    public boolean done;
    public String taskType;

    public Task(String task) {
        name = task;
        done = false;
        taskType = "";
    }

    public Task(String task, String type) {
        name = task;
        done = false;
        taskType = type;
    }

    void mark() {
        done = true;
    }

    void unmark() {
        done = false;
    }

    public String toString() {
        String doneStatus;
        if (done) {
            doneStatus = "X";
        } else {
            doneStatus = " ";
        }
        if (taskType == "") {
            return String.format("[%s] %s", doneStatus, name);
        } else {
            return String.format("[%s][%s] %s", taskType, doneStatus, name);
        }
    }
}
