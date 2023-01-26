public class Task {
    private String done;
    private String name;

    public Task(String name) {
        this.done = " ";
        this.name = name;
    }

    public void setDone() {
        this.done = "X";
    }
    public void setUndone() {
        this.done = " ";
    }

    public boolean isDone() {
        if (this.done.equals(" ")) {
            return false;
        } else {
            return true;
        }
    }

    public String getName() {
        return this.name;
    }
    public String toSave() {
        if (this.done.equals("X")) {
            return "1 | " + this.name + "\n";
        } else {
            return "0 | " + this.name + "\n";
        }
    }

    @Override
    public String toString() {
        return "[" + done + "] " + name;
    }
}
