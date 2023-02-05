package Willy.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;
    private String code;
    private LocalDate date;
    private LocalTime time;

    public Deadline(String msg, String by) {
        super(msg);
        this.by = by;
        this.code = "[D]";
    }

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

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        if (date != null && time != null) {
            return code + super.toString() + " (due on " + date + " at " + time + ")";
        } else {
            return code + super.toString() + " (" + by + ")";
        }
    }

}
