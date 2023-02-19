public class Task {
    String description;
    String tag = " ";
    boolean done = false;

    public void genDscp(String input) throws DukeExceptions {
        this.description = input;
    }
    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isMarked() { return this.done;}

    public String getTag() {return this.tag;}

    public String getDescription() {return this.description;}

    @Override
    public String toString() {
        String mark = this.done ? "X" : " ";
        return "[" + this.tag + "]" + "[" + mark + "] " + this.description;
    }
}
