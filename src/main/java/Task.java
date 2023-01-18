public class Task {
    private String desc;
    private boolean done;

    public Task(String desc) {
        this.desc = desc;
        setDone(false);
    }

    public String getDescription() {
        return this.desc;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isDone() {   return this.done;   }

    public void toggleDoneOrNot() {
        if (this.isDone()) {
            setDone(false);
        } else {
            setDone(true);
        }
    }

    @Override
    public String toString() {
        String icon = done ? "[X]" : "[ ]";
        return icon + " " + this.getDescription();
    }
}
