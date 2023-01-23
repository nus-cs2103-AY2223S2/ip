public class Task {
    String task;
    String tag = "[ ]";
    Boolean done = false;

    public void genDscp(String input) throws DukeExceptions {
        this.task = input;
    }
    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String mark = this.done ? "X" : " ";
        return this.tag + "[" + mark + "] " + this.task;
    }
}
