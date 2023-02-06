package DukeHelpfulCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Event extends Task{
    // tasks that start at a specific date/time and ends at a specific date/time

    private LocalDateTime startDateTime = null;
    private LocalDateTime endDateTime = null;

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
            "hh:mm a dd-MM-yy", "hh:mm a dd/MM/yy"};

    public Event(String name, String startDateTime, String endDateTime) {
        super(name);
        this.startDateTime = formatDateTime(startDateTime);
        while (this.startDateTime == null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Sorry, I don't understand when this Event starts.\nPlease enter the Date and Time:\n");
            String dueTime = scanner.nextLine();
            this.startDateTime = formatDateTime(dueTime);
        }
        this.endDateTime = formatDateTime(endDateTime);
        while (this.endDateTime == null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Sorry, I don't understand when this Event ends.\nPlease enter the Date and Time:\n");
            String dueTime = scanner.nextLine();
            this.endDateTime = formatDateTime(dueTime);
        }
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

    public String toString() {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd MMM yy HHmm");
        return "[E] " + super.toString() + " (from: " + this.startDateTime.format(dtFormatter) + " to: " + this.endDateTime.format(dtFormatter) + ")";
    }

}
