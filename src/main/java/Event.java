import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructor to instantiate an Event object
     * @param description of Event
     * @param from in the format of a string in the format of day/month/year time eg. 02/05/2019 1800
     * @param to in the format of a string in the format of day/month/year time eg. 02/05/2019 1800
     */


    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getTime(){
        return from + " - " + to;
    }


    public String dateCovert(String str){
        // String to LocalDate
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime date = LocalDateTime.parse(str, format1);
        // DateTime to String
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
        return formattedDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateCovert(from) + " to: " + dateCovert(to) + ")";
    }
}