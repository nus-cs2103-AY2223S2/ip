package DukeHelpfulCode.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Deadline extends Task{
    // tasks that need to be done before a specific date/time
    private LocalDateTime dateTime = null;
    private String[] possibleFormats = {
            "yyyy-MM-dd HHmm", "yyyy/MM/dd HHmm",
            "dd-MM-yyyy HHmm", "dd/MM/yyyy HHmm",
            "yyyy-MM-dd hh:mm a", "yyyy/MM/dd hh:mm a",
            "dd-MM-yyyy hh:mm a", "dd/MM/yyyy hh:mm a",
            "HHmm yyyy-MM-dd", "HHmm yyyy/MM/dd",
            "HHmm dd-MM-yyyy", "HHmm dd/MM/yyyy",
            "hh:mm a yyyy-MM-dd", "hh:mm a yyyy/MM/dd",
            "hh:mm a dd-MM-yyyy", "hh:mm a dd/MM/yyyy",
            "yy-MM-dd HHmm", "yy/MM/dd HHmm",
            "dd-MM-yy HHmm", "dd/MM/yy HHmm",
            "yy-MM-dd hh:mm a", "yy/MM/dd hh:mm a",
            "dd-MM-yy hh:mm a", "dd/MM/yy hh:mm a",
            "HHmm yy-MM-dd", "HHmm yy/MM/dd",
            "HHmm dd-MM-yy", "HHmm dd/MM/yy",
            "hh:mm a yy-MM-dd", "hh:mm a yy/MM/dd",
            "hh:mm a dd-MM-yy", "hh:mm a dd/MM/yy",
            "dd MMM yyyy hh:mm a"};

    public Deadline(String name, LocalDateTime dueDate) {
        super(name,"deadline");
        this.dateTime = dueDate;
    }

    public Deadline(String name, LocalDateTime dueDate, boolean isDone) {
        super(name, "deadline", isDone);
        this.dateTime = dueDate;
    }

    private LocalDateTime formatDateTime(String dueDate) {
        LocalDateTime dt = null;
        for (String format : possibleFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                dt = LocalDateTime.parse(dueDate, formatter);
                break;
            } catch (DateTimeParseException e) {
                // Do nothing, just continue to the next format
            }
        }
        return dt;
    }

    public String toString(){
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return "[D] " + super.toString() + " (by: " + this.dateTime.format(dtFormatter) + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline objTask = (Deadline) obj;
            return objTask.getName().equals(this.getName())
                    && objTask.dateTime.equals(this.dateTime);
        } else {
            return false;
        }
    }

}
