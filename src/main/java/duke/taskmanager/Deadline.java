package duke.taskmanager;

import duke.exceptions.EmptyDescException;
import duke.exceptions.UnrecogException;
import duke.exceptions.UnspecTimeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class of parent class Tasks
 */
public class Deadline extends Tasks {
    String icon = "[D]";

    String desc;
    String end;
    LocalDate date;
    String time;

    /*constructor for deadline*/
    public Deadline(String str) throws Exception {
        super(str);
        if(!str.contains("deadline")) {
            throw new UnrecogException("");
        }
        if (str.equals("deadline")) {
            throw new EmptyDescException("");
        }

        if ((super.getDesc().split("/", 2)).length == 1) {
            throw new UnspecTimeException("Please specify a deadline (/by ... )");
        }
        String dline = super.getDesc().split("/", 2)[1];
        this.desc = super.getDesc().split("/",2)[0];
        this.end =  dline.replaceFirst(" ", ": ");
        try {
            String day = dline.substring(3).split(" ")[0];
            String time = dline.substring(3).split(" ")[1];
            this.date = LocalDate.parse(day);
            this.time = time;
        } catch(Exception ignored) {

        }
    }
    /*Returns icon representing the Deadline class of Tasks*/
    public String icon() {
        return icon;
    }

    /*Returns description of Deadline with timeframe*/
    public String getDesc(){
        if(this.date==null) {
            return this.desc.split(" ", 2)[1] + "(" + this.end + ")";
        } else {
            return this.desc.split(" ", 2)[1] + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
            + this.time + ")";
        }
    }

    /*Returns a message specific to when an Deadline task is added to TaskList*/
    public String added() {
        return super.added() + " " + this.icon + super.completed() + " " + this.getDesc();
    }

    /*Returns a message specific to when a Deadline task is removed from TaskList*/
    public String deleted() {
        return super.deleted() + " " + icon + super.completed() + " " + this.getDesc();
    }
}
