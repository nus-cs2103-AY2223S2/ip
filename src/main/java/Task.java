public abstract class Task {
    private String title;
    private boolean done = false;

    Task(String title) throws DukeException {
        if (title.trim().length() == 0) {
            throw new DukeException(Views.EMPTY_ERR_STRING.eng());
        }
        this.title = title.trim();
    }

    Task(String title, boolean done) throws DukeException {
        if (title.trim().length() == 0) {
            throw new DukeException(Views.EMPTY_ERR_STRING.eng());
        }
        this.title = title.trim();
        this.done = done;
    }

    void markAsDone() {
        this.done = true;
    }

    void unmarkAsDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String returnString = "";
        if (done) {
            returnString = "[X] ";
        } else {
            returnString = "[ ] ";
        }
        return returnString + title;
    }
}
