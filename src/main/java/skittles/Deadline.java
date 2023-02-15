package skittles;
//Deadline is one of 3 types of Task, so it must be a child of Task
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Deadline extends Task {

    private final Date byWhen;
    private final String dateline;

    public Deadline(String name, String dateline) {
        super(name);
        this.dateline = dateline;
        this.byWhen = convertStringIntoDateFormat(dateline);
    }

    /**
     * Converts a String input given by the user into a Date of a given format.
     * @param dateline String of date of deadline given by user.
     * @return A Date object with the same details as what the user has given.
     */
    public Date convertStringIntoDateFormat(String dateline) {
        Date date;
        DateFormat rightFormat;

        if (dateline.split(" ").length == 2) {
            rightFormat = new SimpleDateFormat("yyyy-MM-dd hhmm");
        } else {
            rightFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            date = rightFormat.parse(dateline);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * Converts a Date deadline into a String
     * @return A String object of the Date
     */
    public String convertDateIntoStringFormat() {
        if (byWhen == null) {
            return dateline;
        }

        DateFormat stringFormat;
        if (this.dateline.split(" ").length == 2) {
            stringFormat =  new SimpleDateFormat("MMM dd yyyy h.mm aa");
        } else {
            stringFormat = new SimpleDateFormat("MMM dd yyyy");
        }
        return stringFormat.format(this.byWhen);
    }

    /**
     * Converts the given Deadline into an appropriate format for txt file.
     * @return a String of the Deadline for input into a txt file.
     */
    @Override
    public String convertToText() {
        return String.format("D | %d | %s | %s", super.getDoneOrNot() ? 1 : 0, super.getName(),
                        this.byWhen);
    }

    @Override
    public String toString() {
        //making sure printing matches the expected format
        return "[D]" + super.toString() + " " + "(by: " + convertDateIntoStringFormat() + ")";
    }
}
