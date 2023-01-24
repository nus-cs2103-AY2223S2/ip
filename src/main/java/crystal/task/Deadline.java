package crystal.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {

    //protected String by;
    protected String by;


    public Deadline(String description, String by) {
        super(description);

        if (by.contains("day")) {
            if (by.endsWith("day") || by.endsWith("day ")) {
                this.by = by;
            } else {
                //For day with time
                try {
                    String[] temp = by.split("\\s+");
                    String day = temp[1];
                    String time = temp[2];
                    DateFormat f = new SimpleDateFormat("hhmm");
                    Date d = f.parse(time);
                    DateFormat f2 = new SimpleDateFormat("hhmm aa");
                    this.by = day + " " + f2.format(d);

                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Incorrect format!");
                }

            }

        } else if (by.contains("/")) {

            try {

                DateFormat f = new SimpleDateFormat("yyyy/MM/dd hhmm");
                Date d = f.parse(by);
                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat time = new SimpleDateFormat("hhmm aa");
                String k = date.format(d);
                LocalDate d1 = LocalDate.parse(k.trim());
                String t = time.format(d);
                this.by = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + t;

            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Incorrect format!");
            }

        } else {
            try {
                DateFormat f = new SimpleDateFormat("yyyy-MM-dd hhmm");
                Date d = f.parse(by);
                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                DateFormat time = new SimpleDateFormat("hhmm aa");
                String k = date.format(d);
                LocalDate d1 = LocalDate.parse(k.trim());
                String t = time.format(d);
                this.by = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + t;

            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Incorrect format!");
            }
        }


    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
