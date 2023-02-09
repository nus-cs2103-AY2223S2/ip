package duke.taskmanager;

import duke.exceptions.EmptyDescException;
import duke.exceptions.UnrecogException;
import duke.exceptions.UnspecTimeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Event class of parent class Tasks
 */
public class Event extends Tasks{
    String icon = "[E]";
    String desc;
    String timeframe;
    String time1, time2;
    LocalDate date1, date2;

    /*constructor for event*/
    public Event(String str) throws Exception{
        super(str);

        if(!str.contains("event")) {
            throw new UnrecogException("");
        }
        if (str.equals("event")) {
            throw new EmptyDescException("");
        }

        if ((super.getDesc().split("/", 2)).length == 1) {
            throw new UnspecTimeException("Please specify a timeframe (from/ ... to/ ...)");
        }

        String evnt = super.getDesc().split("/", 2)[1];
        String start = evnt.split("/", 2)[0].replaceFirst(" ", ": ");
        String end = evnt.split("/", 2)[1].replaceFirst(" ", ": ");
        this.desc = super.getDesc().split("/",2)[0];
        this.timeframe = start + end;
        try {
            String d1 = start.substring(6).split(" ")[0];
            this.time1 = start.substring(6).split(" ")[1];
            String d2 = end.substring(4).split(" ")[0];
            System.out.println(d1);
            System.out.println(d2);
            this.time2 = end.substring(4).split(" ")[1];
            this.date1 = LocalDate.parse(d1);
            this.date2 = LocalDate.parse(d2);

            this.timeframe = "from: " + date1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + time1 +
                    " to: " + date2.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "+ time2;

        } catch(Exception ignored) {

        }
    }

    /*Returns icon representing the Event class of Tasks*/
    public String icon() {
        return icon;
    }

    /*Returns description of Event with timeframe*/
    public String getDesc() {
        return this.desc.split(" ", 2)[1]+ "("+this.timeframe+")";
    }

    /*Returns a message specific to when an Event task is added to TaskList*/
    public String added() {
        return super.added() + " " + icon + super.completed() + " " + this.desc.split(" ", 2)[1]+
                "("+this.timeframe+")";
    }

    /*Returns a message specific to when an Event task is removed from TaskList*/
    public String deleted(){
        return super.deleted() +  " " + icon + super.completed() + " " + this.desc.split(" ", 2)[1]+
                "("+this.timeframe+")";
    }
}
