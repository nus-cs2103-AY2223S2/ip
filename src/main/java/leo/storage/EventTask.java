package leo.storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event Task. A <code>EventTask</code> object corresponds to
 * a Task containing the String description and duration consisting of LocalDateTime from and LocalDateTime to.
 */
public class EventTask extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor to create an EventTask object.
     *
     * @param task Description of the Task.
     * @param from Start date of the Task.
     * @param to End date of the Task.
     */
    public EventTask(String task, LocalDateTime from, LocalDateTime to) {
        super(task);
        this.from = from;
        this.to = to;
        setType(TaskType.EVENT);
    }

    /**
     * Returns type, status, description and deadline of Task.
     *
     * @return String representation of EventTask.
     */
    @Override
    public String toString() {
        return typeAndStatus() + getTask() + " (" + duration() + ")";
    }

    /**
     * Returns formatted duration of EventTask obtained from LocalDateTime.
     *
     * @return String representation of the duration.
     */
    private String duration() {
        DateTimeFormatter formatterOne = DateTimeFormatter.ofPattern("EEE, MMM dd, hh:mm a");
        DateTimeFormatter formatterTwo = DateTimeFormatter.ofPattern("hh:mm a");
        if (sameDate(this.from, this.to)) {
            return formatterOne.format(this.from) + " - " + formatterTwo.format(this.to);
        }
        return formatterOne.format(this.from) + " - " + formatterOne.format(this.to);
    }

    /**
     * Checks whether both LocalDateTime provided are on the same day regardless of time.
     *
     * @param dtOne First LocalDateTime to be compared.
     * @param dtTwo Second LocalDateTime to be compared.
     * @return Both LocalDateTime are on the same day.
     */
    private boolean sameDate(LocalDateTime dtOne, LocalDateTime dtTwo) {
        boolean sameYear = dtOne.getYear() == dtTwo.getYear();
        return sameYear && dtOne.getDayOfYear() == dtTwo.getDayOfYear();
    }

    /**
     * Returns the String representation of Task that is to be saved in the data file.
     *
     * @return String representation of EventTask.
     */
    @Override
    public String saveFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
        String strFrom = formatter.format(this.from);
        String strTo = formatter.format(this.to);
        return typeAndStatus() + getTask() + " | " + strFrom + " | " + strTo + "\n";
    }

    public boolean withinDate(LocalDate day) {
        LocalDate start = this.from.toLocalDate();
        LocalDate end = this.to.toLocalDate();
        return !(day.isBefore(start) || day.isAfter(end));
    }
}
