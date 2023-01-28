package crystal.task;

import crystal.CrystalException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {

    //protected String by;
    public LocalDateTime by;


    public Deadline(String description, String by) throws CrystalException {
        super(description);

        try {
            LocalDateTime d1 = LocalDateTime.parse(by.trim());
            this.by = d1;
        } catch (Exception e) {
            throw new CrystalException("Please change the input date format to yyyy-MMM-dThh:mm!");
        }




    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")) + ")";
    }

    @Override
    public String toPrint() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
