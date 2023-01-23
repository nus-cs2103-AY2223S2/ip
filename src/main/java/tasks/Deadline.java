package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task {

    protected String by;

    /**
     * Constructor that takes in description of deadline and
     * @param description of deadline
     * @param by in the format of a string in the format of day/month/year time eg. 02/05/2019 1800
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy(){
        return by;
    }
    /**
     * Method to covert String into formatted string in date format.
     * @param str with the exact format day/month/year time(24 hour format)
     */

    public String dateConvert(String str){
        // String to LocalDate
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(str, format1);
        // DateTime to String
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
        return formattedDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateConvert(by) + ")";
    }
}