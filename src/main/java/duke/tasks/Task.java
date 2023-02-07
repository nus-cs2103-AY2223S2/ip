package duke.tasks;
import duke.helpers.Ui;

/**
 * Represents a Task object in TaskList, has 3 subclasses (Todo, Event, Deadline)
 */
public class Task {
    protected boolean mark;
    protected String content;

    /**
     * Constructs an object of the Task class. By default, mark field is false.
     */
    public Task(String content) {
        this.content = content;
        this.mark = false;
    }

    /**
     * Constructs an object of the Task class.
     *
     * @param alternative sets the boolean field mark into true/false to initialize the Task obj.
     */
    public Task(String content, boolean alternative) {
        this.content = content;
        this.mark = alternative;
    }

    public String getContent() {
        return this.content;
    }

    public boolean getMark() {
        return this.mark;
    }

    /**
     * Allows for changing of the "mark" field within the Task class. Inverts the mark.
     */
    public String setMark() {
        this.mark = !this.mark;
        String outputStr;
        if (mark == true) {
            outputStr = "NICE! You finished this: \n"
                    + "[" + markSign(this.mark) + "] " + this.content;
        } else {
            outputStr = "Ok, you have undone this: \n"
                    + "[" + markSign(this.mark) + "] " + this.content;
        }
        return Ui.formatStr(outputStr);
    }

    /**
     * returns true/false depending on the char read in.
     *
     * @param markBool represents the char read in (could be X or white space)
     */
    public String markSign(boolean markBool) {
        if(markBool == true) return "X";
        else return " ";
    }

    public String toString() {
        return ". [" + markSign(this.mark) + "] " + this.content;
    }

    public String printRecord() {
        return this.toString();
    }
}
