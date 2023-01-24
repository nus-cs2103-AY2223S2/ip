import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime datetimeFrom;
    protected LocalDateTime datetimeTo;


    public Event(String desc) {

        super(desc.substring(0, desc.indexOf("/from") - 1));
        String[] datetimeFrom = desc.substring(desc.indexOf("/from") + 6, desc.indexOf(" /to")).split(" ");
        String[] datetimeTo = desc.substring(desc.indexOf("/to") + 4).split(" ");

        String timeFrom;
        if (datetimeFrom.length == 1) { // if time not indicated, set default to 0000
            timeFrom = "0000";
        } else {
            timeFrom = datetimeFrom[1];
        }

        String timeTo;
        if (datetimeTo.length == 1) { // if time not indicated, set default to 2359
            timeTo = "2359";
        } else {
            timeTo = datetimeTo[1];
        }

        // to add error for this after overhauling task creation process
        String dateFrom = datetimeFrom[0];
        String dateTo = datetimeTo[0];

        String[] ddmmyyyyFrom;
        try {
            ddmmyyyyFrom = dateFrom.split("-");
        } catch (Exception err) { // processing tasks.txt
            ddmmyyyyFrom = dateFrom.split("/");
        }

        String[] ddmmyyyyTo;
        try {
            ddmmyyyyTo = dateTo.split("-");
        } catch (Exception err) { // processing tasks.txt
            ddmmyyyyTo = dateTo.split("/");
        }

        this.datetimeFrom = LocalDateTime.of(Integer.parseInt(ddmmyyyyFrom[2]), Integer.parseInt(ddmmyyyyFrom[1]),
                Integer.parseInt(ddmmyyyyFrom[0]),
                Integer.parseInt(timeFrom.substring(0,2)), Integer.parseInt(timeFrom.substring(2)));

        this.datetimeTo = LocalDateTime.of(Integer.parseInt(ddmmyyyyTo[2]), Integer.parseInt(ddmmyyyyTo[1]),
                Integer.parseInt(ddmmyyyyTo[0]),
                Integer.parseInt(timeTo.substring(0,2)), Integer.parseInt(timeTo.substring(2)));
    }

    public String toString() {

        // this is awfully messy + inefficient. to clean up!!!!!

        String dayFrom = "" + datetimeFrom.getDayOfMonth();
        if (dayFrom.length() == 1) {
            dayFrom = "0" + dayFrom;
        }
        String monthFrom = "" + datetimeFrom.getMonthValue();
        if (monthFrom.length() == 1) {
            monthFrom = "0" + monthFrom;
        }

        String dateFrom = dayFrom + "/" + monthFrom + "/" + datetimeFrom.getYear();

        String minFrom = "" + datetimeFrom.getMinute();
        if (minFrom.length() == 1) {
            minFrom = "0" + minFrom;
        }
        String timeFrom = datetimeFrom.getHour() + "" + minFrom;

        String dayTo = "" + datetimeTo.getDayOfMonth();
        if (dayTo.length() == 1) {
            dayTo = "0" + dayTo;
        }
        String monthTo = "" + datetimeTo.getMonthValue();
        if (monthTo.length() == 1) {
            monthTo = "0" + monthTo;
        }

        String dateTo = dayTo + "/" + monthTo + "/" + datetimeTo.getYear();

        String minTo = "" + datetimeTo.getMinute();
        if (minTo.length() == 1) {
            minTo = "0" + minTo;
        }
        String timeTo = datetimeTo.getHour() + "" + minTo;
        return "[E]" + super.toString() + String.format(" (from: %s %s to: %s %s)", dateFrom, timeFrom, dateTo, timeTo);
    }

}