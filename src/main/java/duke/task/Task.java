package duke.task;//Reference from the partial solution provided on CS2103 module website

public class Task {
    protected String description;
    protected boolean isDone;
    protected String typeofTask = "";
    protected String doneStr;

    public Task(String description, String typeOfTask, String doneStr) {
        this.description = description;
        this.doneStr = doneStr;
        checkIsDoneStr();
        this.typeofTask = typeOfTask;

    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean checkIsDoneStr() {
        if (this.doneStr.equals("X")) {
            this.isDone = true;
        }
        else this.isDone = false;
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n");
    }

    public String toString() {
        if (typeofTask.equals(""))
            return "[" + this.getStatusIcon() + "]" + " " + this.description;
        else { return "[" + typeofTask + "]" + "[" + this.getStatusIcon() + "]" + " " + this.description; }
    }

}
