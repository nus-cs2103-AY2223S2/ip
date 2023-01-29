package Duke.Tasks;

/**
 * The most basic task TodoTask implements task and accepts a task Name.
 */
public class TodoTask extends Task {

    public TodoTask(String taskName) {
        super(taskName);
    }

    /**
     * Init function for loadData
     *
     * @param input Save file representation of this TodoTask
     * @return TodoTask generated from Save file representation
     */
    public static Task loadData(String input) {
        String[] inputEvent = input.split("\\|", 3);


        Task newEvent = new TodoTask(inputEvent[2]);
        newEvent.loadCompletionStatus(inputEvent[1]);
        return newEvent;
    }

    /**
     * Converts this TodoTask to save file representation
     *
     * @return Converts this TodoTask to Save file representation
     */
    @Override
    public String toSaveData() {
        return "T|" + (getCompletionStatus() ? "1" : "0") +
                "|" + taskName;
    }

    /**
     * Converts this TodoTask to a string for user
     *
     * @return Converts this TodoTask to string for user
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
