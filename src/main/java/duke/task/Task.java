package duke.task;

public class Task {
    boolean done;
    String checked = "[X]";
    String unchecked = "[ ]";
    String title;
    String type;

    public Task(String title) {
        this.title = title;
        this.done = false;
        this.type = "";
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public boolean isMarked() {
        return this.done;
    }

    /**
     * Method for checking if a substring exists in the title.
     *
     * @param subString to search for.
     * @return boolean representing result of the search.
     */
    public boolean contains(String subString) {
        return this.title.contains(subString);
    }

    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title;
    }
    @Override
    public String toString() {
        return this.type + (done ? checked : unchecked) + " "  + this.title;
    }

}
