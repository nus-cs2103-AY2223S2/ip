package duke.tasks;
import duke.helpers.Ui;

/**
 * Represents a Task object in TaskList, has 3 subclasses (Todo, Event, Deadline)
 */
public class Task {
    protected boolean isMark;
    protected String content;

    /**
     * Constructs an object of the Task class. By default, isMark field is false.
     */
    public Task(String content) {
        this.content = content;
        this.isMark = false;
    }

    /**
     * Constructs an object of the Task class.
     *
     * @param alternative sets the boolean field isMark into true/false to initialize the Task obj.
     */
    public Task(String content, boolean alternative) {
        this.content = content;
        this.isMark = alternative;
    }

    public String getContent() {
        return this.content;
    }

    public boolean getMark() {
        return this.isMark;
    }

    /**
     * Allows for changing of the "isMark" field within the Task class. Inverts the isMark.
     */
    public String setMark() {
        this.isMark = !this.isMark;
        String outputStr;
        if (isMark == true) {
            outputStr = "NICE! You finished this: \n"
                    + "[" + markSign(this.isMark) + "] " + this.content;
        } else {
            outputStr = "Ok, you have undone this: \n"
                    + "[" + markSign(this.isMark) + "] " + this.content;
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
        return ". [" + markSign(this.isMark) + "] " + this.content;
    }

    public String printRecord() {
        return this.toString();
    }
}
