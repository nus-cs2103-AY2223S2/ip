package duke.task;

import duke.Ui;

public abstract class Task {
    private boolean isMarked;
    private String content;

    public Task(String Task_content) {
        this.content = Task_content;
        this.isMarked = false;
    }

    public Task(Boolean isMarked, String Task_content) {
        this.content = Task_content;
        this.isMarked = isMarked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public String get_content () {
        return content;
    }

    public void mark() {
        isMarked = true;
        Ui.displayMark(this);
    }

    public void unmark() {
        isMarked = false;
        Ui.displayUnmark(this);
    }

    public abstract String addDivider();

    public boolean is_Marked() {
        return isMarked;
    }

    public String toString() {
        if(isMarked) {
            return "[X] " + content;
        } else {
            return "[ ] " + content;
        }
    }

}
