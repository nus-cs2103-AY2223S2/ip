package red.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    protected LocalDateTime dateTime = null;
    protected LocalDate date = null;

    public DeadlineTask(String description, String date, String time) {
        super(description);
        String[] dateStr = date.split("/", 3);
        String[] altDateStr = date.split("-", 3);
        if(altDateStr.length != 3 && dateStr.length != 3) {
            throw new RuntimeException("Specification of the DeadlineTask date is incorrect\n");
        } else if (dateStr.length == 3) {
            this.dateTime = LocalDateTime.of( Integer.valueOf(dateStr[2]),
                    Integer.valueOf(dateStr[1]), Integer.valueOf(dateStr[0]), Integer.valueOf(time.substring(0,2)),
                    Integer.valueOf(time.substring(2)));

        } else if(altDateStr.length == 3) {
            LocalDate temp = LocalDate.parse(date);
            LocalTime temptime = LocalTime.of(Integer.valueOf(time.substring(0,2)), Integer.valueOf(time.substring(2)));
            this.dateTime = LocalDateTime.of(temp,temptime);

        }
    }

    public DeadlineTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public DeadlineTask(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public DeadlineTask(String description, String date) {
        super(description);
        String[] dateStr = date.split("/", 3);
        String[] altDateStr = date.split("-", 3);
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
}
