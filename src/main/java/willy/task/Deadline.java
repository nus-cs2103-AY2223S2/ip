package willy.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline
 */
public class Deadline extends Task {
    private String by;
    private String code;
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates a deadline with a msg and by date
     * @param msg
     * @param by
     */
    public Deadline(String msg, String by) {
        super(msg);
        this.by = by;
        this.code = "[D]";
    }

    /**
     * Creates a Deadline with a msg and a LocalDate/LocalTime arg
     * @param msg
     * @param dateArr
     */
    public Deadline(String msg, String[] dateArr) {
        super(msg);
        this.code = "[D]";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dateArr.length - 1; i++) {
            if (dateArr[i].length() == 2 || dateArr[i].length() == 4) {
                sb.append(dateArr[i]);
            } else {
                sb.append("0");
                sb.append(dateArr[i]);
            }
            if (i < dateArr.length - 2) {
                sb.append("-");
            }
        }

        String date = sb.toString();
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.date = LocalDate.parse(date, f1);

        String time = dateArr[3];
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("HHmm");
        this.time = LocalTime.parse(time, f2);
        System.out.println(time);
    }

    /** 
     * Returns the string representation of the code
     * @return String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the string representation of the Deadline
     */
    @Override
    public String toString() {
        if (date != null && time != null) {
            return code + super.toString() + "(due on " + date + " at " + time + ")";
        } else {
            return code + super.toString() + "(" + by + ")";
        }
    }

}
