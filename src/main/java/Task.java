public class Task {
<<<<<<< .merge_file_a22244
    String task;
    String tag = "[ ]";
    Boolean done = false;

    public void genDscp(String input) throws DukeExceptions {
        this.task = input;
=======
    String description;
    String tag = " ";
    boolean done = false;

    public void genDscp(String input) throws DukeExceptions {
        this.description = input;
>>>>>>> .merge_file_a09328
    }
    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

<<<<<<< .merge_file_a22244
    @Override
    public String toString() {
        String mark = this.done ? "X" : " ";
        return this.tag + "[" + mark + "] " + this.task;
=======
    public boolean isMarked() { return this.done;}

    public String getTag() {return this.tag;}

    public String getDescription() {return this.description;}

    @Override
    public String toString() {
        String mark = this.done ? "X" : " ";
        return "[" + this.tag + "]" + "[" + mark + "] " + this.description;
>>>>>>> .merge_file_a09328
    }
}
