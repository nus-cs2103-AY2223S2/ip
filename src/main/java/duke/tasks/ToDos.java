package duke.tasks;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return super.getStatusIcon();
    }


    /**
     * Produces a String that adheres to our Storage formatting
     * holding all the relevant information.
     *
     * @return the String of the specific task for saving
     */
    @Override
    public String saveString() {
        return String.format("T|%s|%s", super.saveString(), super.description);
    }

    /**
     * All the Information of the ToDos task
     *
     * @return a String of all the information of the ToDos task to be printed by the Ui
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
