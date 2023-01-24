import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime datetime;
    public Deadline(String desc) {

        super(desc.substring(0, desc.indexOf("/by") - 1));
        String[] dateTime = desc.substring(desc.indexOf("/by") + 4).split(" ");
        String date = dateTime[0]; // accept as dd-mm-yyyy, print as dd/mm/yyyy
        String time;
        if (dateTime.length == 1) { // if time not indicated, set default to 2359
            time = "2359";
        } else {
            time = dateTime[1];
        }
        // to add error for this after overhauling task creation process
        String[] ddmmyyyy;
        try {
            ddmmyyyy = date.split("-");
        } catch (Exception err) { // processing tasks.txt
            ddmmyyyy = date.split("/");
        }
        this.datetime = LocalDateTime.of(Integer.parseInt(ddmmyyyy[2]), Integer.parseInt(ddmmyyyy[1]),
                Integer.parseInt(ddmmyyyy[0]),
                Integer.parseInt(time.substring(0,2)), Integer.parseInt(time.substring(2)));
    }

    public String toString() {
        // to switch to using DateTimeFormatter
        String day = "" + datetime.getDayOfMonth();
        if (day.length() == 1) {
            day = "0" + day;
        }
        String month = "" + datetime.getMonthValue();
        if (month.length() == 1) {
            month = "0" + month;
        }
        String date = day + "/" + month + "/" + datetime.getYear();
        String min = "" + datetime.getMinute();
        if (min.length() == 1) {
            min = "0" + min;
        }
        String time = datetime.getHour() + "" + min;
        return "[D]" + super.toString() + String.format(" (by: %s %s)", String.join("/", date), time);
    }

}