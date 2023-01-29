package Duke.Tasks;

import java.time.LocalDateTime;

/**
 * EventTask is a task that has a start and end date
 */
public class EventTask extends Task {
    LocalDateTime dueDate;
    LocalDateTime startDate;

    /**
     * Init function of EventTask
     *
     * @param taskName  name of this Event
     * @param startDate LocalDateTime to start of Event
     * @param dueDate   LocalDateTime to end of Event
     */
    public EventTask(String taskName, LocalDateTime startDate, LocalDateTime dueDate) {
        super(taskName);
        this.dueDate = dueDate;
        this.startDate = startDate;
    }

    /**
     * Loads Information from save file representation as a EventTask
     *
     * @param input Information from save file to load
     * @return EventTask generated from save file representation
     */
    public static Task loadData(String input) {
        String[] inputEvent = input.split("\\|", 5);
        Task newEvent = new EventTask(inputEvent[2], LocalDateTime.parse(inputEvent[3]),
                LocalDateTime.parse(inputEvent[4]));
        newEvent.loadCompletionStatus(inputEvent[1]);
        return newEvent;
    }

    /**
     * Converts EventTask to save file representation
     *
     * @return Save file representation of EventTask
     */
    @Override
    public String toSaveData() {
        return "E|" + (getCompletionStatus() ? "1" : "0") +
                "|" + taskName + "|" + startDate + "|" + dueDate;
    }

    /**
     * Converts EventTask to string for user
     *
     * @return Representation of EventTask for user
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + displayLocalDate(startDate)
                + " to: " + displayLocalDate(dueDate) + ")";
    }
}
