package duke.task;

import duke.enums.Views;
import duke.DukeException;

public abstract class Task {
    private String title;
    private boolean isDone = false;

    Task(String title) throws DukeException {
        if (title.trim().length() == 0) {
            throw new DukeException(Views.EMPTY_ERR_STRING.eng());
        }
        this.title = title.trim();
    }

    Task(String title, boolean isDone) throws DukeException {
        if (title.trim().length() == 0) {
            throw new DukeException(Views.EMPTY_ERR_STRING.eng());
        }
        this.title = title.trim();
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String toExport() {
        return this.toString();
    }

    @Override
    public String toString() {
        String returnString = "";
        if (isDone) {
            returnString = "[X] ";
        } else {
            returnString = "[ ] ";
        }
        return returnString + title;
    }
}
