package duke.task;

public class Task {
    protected String item;
    protected boolean isComplete;

    protected String note;

    protected String types; // todo, deadline, events

    /**
     * Task is for all todo task
     * It is the super class of event and deadline
     * @param item is the name of the task
     * @param types is the type of the task mainly "T" for todo, "D" for deadline and "E" for event
     */

    public Task(String item, String types) {
        this.item = item;
        this.types = types;
        this.note = "blank";
        isComplete = false;
    }

    /**
     * gets the time for the task
     * @return "" as todo task does not have a timing attached to them
     */
    public String getTime() {
        return "";
    }

    /**
     * gets the name of the task
     * @return item
     */
    public String getItem() {
        return item;
    }

    /**
     * gets whether the task is mark or not
     * @return true for mark and false for unmark
     */

    public boolean getComplete() {
        return isComplete;
    }

    /**
     * gets the type of task
     * @return "T" for todo, "D" for deadline and "E" for event
     */
    public String getTypes() {
        return types;
    }

    @Override
    public String toString(){
        String getter;
        if (isComplete == false) {
            getter = "[ ] " + item;
        } else {
            getter = "[X] " + item;
        }

        if (types.equals("T")) {
            return "[T]" + getter;
        }

        return getter;
    }


    /**
     * changes isComplete to true
     */
    public void mark(){
        isComplete = true;
    }

    /**
     * changes isComplete to false
     */
    public void unmark(){
        isComplete = false;
    }

    /**
     * Add notes to the task
     * @param message to be added as notes
     */
    public void addNote(String message) {

        if (note.equals("blank")) {
            this.note = message;
        } else  {
            this.note = this.note + "\n" + message;
        }

    }

    /**
     * Returns the notes of the task
     * @return string of the notes in the task
     */
    public String getNote() {
        return note;
    }
}
