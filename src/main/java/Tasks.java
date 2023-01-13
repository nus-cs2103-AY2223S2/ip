public class Tasks {
    final protected String desc;
    protected Boolean done;
    public Tasks(String str) {
        this.desc = str;
        this.done = false;
    }
    public String getDesc(){
        return desc;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
    public String symbol() {
        if(done) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }
}
