package red.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class is for tasks that have a deadline.
 */
public class DeadlineTask extends Task {

    protected LocalDate date = null;
    protected String formattedDate;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructor for a DeadlineTask that takes in a String description as well as String date
     * and String time.
     *
     * @param description The description of the EventTask.
     * @param date The date of the event.
     */
    public DeadlineTask(String description, String date) {
        super(description);
        this.date = getLocalDate(date);
        this.formattedDate = this.date.format(dateTimeFormatter);
    }

    /**
     * Constructor for a DeadlineTask that takes in a String description as well as LocalDate date
     *
     * @param description The description of the EventTask.
     * @param date The date of the event.
     */
    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.formattedDate = this.date.format(dateTimeFormatter);
    }

    /**
     * Parses through String to obtain a LocalDate object
     *
     * @param date String representation of time
     * @return LocalDate Object of specified time
     */
    public LocalDate getLocalDate(String date) {
        String[] dateStr = date.split("/",3);
        if (dateStr.length < 3) {
            throw new RuntimeException("Specification of the EventTask date is incorrect\n");
        }
        return LocalDate.of( Integer.valueOf(dateStr[2]), Integer.valueOf(dateStr[1]), Integer.valueOf(dateStr[0]));

    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (Before: " + formattedDate  + ")";
    }

    /**
     * Compares this object to the specified object.
     *
     * @param obj the object to compare with.
     * @return true if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeadlineTask)) {
            return false;
        }
        DeadlineTask checkedObj = (DeadlineTask) obj;
        boolean isSameDescription = this.description.equals(checkedObj.description);
        boolean isSameDate = this.date.equals(checkedObj.date);
        boolean isSame = isSameDescription && isSameDate;

        if(isSame) {
            return true;
        }

        return false;
    }
}
