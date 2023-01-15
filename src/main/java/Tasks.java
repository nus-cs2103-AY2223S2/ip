public class Tasks {
    protected final String desc;
    protected Boolean done;

    public Tasks(String var1) {
        this.desc = var1;
        this.done = false;
    }

    public String getDesc() {
        return this.desc;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String symbol() {
        return this.done ? "[X]" : "[ ]";
    }

    public String icon() {
        return "";
    }

    public String mssg() {
        return "Got it I've added this task: \n";
    }
}
