package Duke.Tasks;

import java.time.LocalDateTime;

public class EventTask extends Task {
    LocalDateTime dueDate;
    LocalDateTime startDate;

    public EventTask(String taskName, LocalDateTime startDate, LocalDateTime dueDate) {
        super(taskName);
        this.dueDate = dueDate;
        this.startDate = startDate;
    }

    public static Task loadData(String input) {
        String[] inputEvent = input.split("\\|", 5);
        Task newEvent = new EventTask(inputEvent[2], LocalDateTime.parse(inputEvent[3]), LocalDateTime.parse(inputEvent[4]));
        newEvent.loadCompletionStatus(inputEvent[1]);
        return newEvent;
    }

    @Override
    public String toSaveData() {
        return "E|" + (getCompletionStatus() ? "1" : "0") +
                "|" + taskName + "|" + startDate + "|" + dueDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + displayLocalDate(startDate) + " to: " + displayLocalDate(dueDate) + ")";
    }
}
