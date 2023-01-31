package task;
import helpers.Ui;

public class Task {
    protected boolean mark;
    protected String content;

    public Task(String content) {
        this.content = content;
        this.mark = false;
    }

    public Task(String content, boolean alternative) {
        this.content = content;
        this.mark = alternative;
    }

    public boolean getMark() {
        return this.mark;
    }

    public void setMark() {
        this.mark = !this.mark;
        String outputStr;
        if (mark == true) {
            outputStr = "NICE! You finished this: \n"
                    + "[" + markSign(this.mark) + "] " + this.content;
        } else {
            outputStr = "Ok, you have undone this: \n"
                    + "[" + markSign(this.mark) + "] " + this.content;
        }
        System.out.println(Ui.formatStr(outputStr));
    }

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
