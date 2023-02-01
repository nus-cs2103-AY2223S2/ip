package task;

public class Task {
    private boolean checked;
    private String details;

    /**
     * Creates a new task object
     * @param details the specifics of what the task is
     */
    public Task(String details) {
        checked = false;
        this.details = details;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString(){
        String checkSymbol = " ";
        if (checked) {
            checkSymbol = "X";
        }
        return "[" + checkSymbol + "] " + details;
    }

    /**
     * Returns the details of the task in a format that can be stored easily later.
     * @return the string to be stored
     */
    public String toStorageString() {
        String checkSymbol = "0";
        if (checked) {
            checkSymbol = "1";
        }
        return checkSymbol + "#" + details;
    }
}
