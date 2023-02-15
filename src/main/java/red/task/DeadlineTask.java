package red.task;

import red.command.AddCommand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is for tasks that have a deadline.
 */
public class DeadlineTask extends Task {

    protected LocalDateTime dateTime = null;
    protected LocalDate date = null;

    /**
     * Constructor for a DeadlineTask that takes in a String description as well as String date
     * and String time.
     *
     * @param description The description of the EventTask.
     * @param date The date of the event.
     * @param time The time of the event.
     */
    public DeadlineTask(String description, String date, String time) {
        super(description);
        String[] dateStr = date.split("/", 3);
        String[] altDateStr = date.split("-", 3);
        assert altDateStr.length == 3;
        assert dateStr.length == 3;
        if(altDateStr.length != 3 && dateStr.length != 3) {
            throw new RuntimeException("Specification of the DeadlineTask date is incorrect\n");
        } else if (dateStr.length == 3) {
            this.dateTime = LocalDateTime.of( Integer.valueOf(dateStr[2]),
                    Integer.valueOf(dateStr[1]), Integer.valueOf(dateStr[0]), Integer.valueOf(time.substring(0,2)),
                    Integer.valueOf(time.substring(2)));

        } else if(altDateStr.length == 3) {
            LocalDate temp = LocalDate.parse(date);
            LocalTime tempTime = LocalTime.of(Integer.valueOf(time.substring(0,2)), Integer.valueOf(time.substring(2)));
            this.dateTime = LocalDateTime.of(temp,tempTime);

        }
    }

    /**
     * Constructor for a DeadlineTask that takes in a String description as well as
     * date and time in LocalDateTime format
     *
     * @param description The description of the EventTask.
     * @param dateTime The date and time of the event.
     */
    public DeadlineTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Constructor for a DeadlineTask that takes in a String description as well as
     * date in LocalDate format
     *
     * @param description The description of the EventTask.
     * @param date The date of the event.
     */
    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Constructor for a DeadlineTask that takes in a String description as well as String date.
     *
     * @param description The description of the EventTask.
     * @param date The date of the event.
     */
    public DeadlineTask(String description, String date) {
        super(description);
        String[] dateStr = date.split("/", 3);
        String[] altDateStr = date.split("-", 3);
        assert altDateStr.length == 3;
        assert dateStr.length == 3;
        if(altDateStr.length != 3 && dateStr.length != 3) {
            System.out.println(date);
            throw new RuntimeException("Specification of the DeadlineTask date is incorrect\n");
        } else if (dateStr.length == 3) {
            this.date = LocalDate.of( Integer.valueOf(dateStr[2]),
                    Integer.valueOf(dateStr[1]), Integer.valueOf(dateStr[0]));

        } else if(altDateStr.length == 3) {
            System.out.println(date);
            this.date = LocalDate.parse(date);

        }
    }

    @Override
    public String toString() {
        if(dateTime == null) {
            return "[D]" + super.toString() + " (Before: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return "[D]" + super.toString() + " (Before: " + dateTime  + ")";
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
        boolean isSameDate = false;
        boolean isSameDateTime = false;
        if(dateTime == null) {
            isSameDate = this.date.equals(checkedObj.date);
        }
        if(date == null) {
            isSameDateTime = this.dateTime.equals(checkedObj.dateTime);
        }



        if (isSameDescription && isSameDateTime) {
            return true;
        }
        if (isSameDescription && isSameDate) {
            return true;
        }

        return false;
    }
}
