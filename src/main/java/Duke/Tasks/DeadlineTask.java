package Duke.Tasks;

import java.time.LocalDateTime;

/**
 * DeadlineTask is a task that only has a end date.
 */
public class DeadlineTask extends Task {

    LocalDateTime dueDate;

    /**
     * Init function of DeadlineTask
     *
     * @param taskName Name of this DeadlineTask
     * @param dueDate  LocalDateTime to complete this task by
     */
    public DeadlineTask(String taskName, LocalDateTime dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    /**
     * Converts the Information from save file to load as a DeadlineTask
     *
     * @param input Information from save file
     * @return the DeadlineTask loaded from save
     */
    public static Task loadData(String input) {
        String[] inputEvent = input.split("\\|", 4);
        Task newEvent = new DeadlineTask(inputEvent[2], LocalDateTime.parse(inputEvent[3]));
        newEvent.loadCompletionStatus(inputEvent[1]);
        return newEvent;
    }

    /**
     * Converts the DeadlineTask to a String to save in file
     *
     * @return Representation of DeadlineTask in save file
     */
    @Override
    public String toSaveData() {
        return "D|" + (getCompletionStatus() ? "1" : "0") +
                "|" + taskName + "|" + dueDate;
    }

    /**
     * Converts the DeadlineTask to a String to be shown to user
     *
     * @return Representation of DeadlineTask for user
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + displayLocalDate(dueDate) + ")";
    }

}
