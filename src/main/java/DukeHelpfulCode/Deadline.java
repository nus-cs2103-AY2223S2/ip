package DukeHelpfulCode;

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
            "hh:mm a dd-MM-yy", "hh:mm a dd/MM/yy"};

    public Deadline(String name, String dueDate) {
        super(name);
        this.dateTime = formatDateTime(dueDate);
        while (this.dateTime == null){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Sorry, I don't understand when you need this by.\nPlease enter the Date and Time:\n");
            String dueTime = scanner.nextLine();
            this.dateTime = formatDateTime(dueTime);
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

    public String toString(){
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd MMM yy HHmm");
        return "[D] " + super.toString() + " (by: " + this.dateTime.format(dtFormatter) + ")";
    }

}
