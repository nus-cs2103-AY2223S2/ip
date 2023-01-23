package duke.taskmanager;

import duke.exceptions.emptyDescException;
import duke.exceptions.unrecogException;
import duke.exceptions.unspecTimeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Tasks {
    String icon = "[D]";

    String desc;
    String end;
    LocalDate date;
    String time;
    public Deadline(String str) throws Exception {
        super(str);
        if(!str.contains("deadline")) {
            throw new unrecogException("");
        }
        if (str.equals("deadline")) {
            throw new emptyDescException("");
        }

        if ((super.getDesc().split("/", 2)).length == 1) {
            throw new unspecTimeException("Please specify a deadline (/by ... )");
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
    public String icon() {
        return icon;
    }

    public String getDesc(){
        if(this.date==null) {
            return this.desc.split(" ", 2)[1] + "(" + this.end + ")";
        } else {
            return this.desc.split(" ", 2)[1] + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
            + this.time + ")";
        }
    }

    public String added() {
        return super.added() + " " + this.icon + super.completed() + " " + this.getDesc();
    }
    public String deleted() {
        return super.deleted() + " " + icon + super.completed() + " " + this.getDesc();
    }
}
