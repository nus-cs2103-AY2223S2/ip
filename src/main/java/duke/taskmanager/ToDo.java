package duke.taskmanager;

import duke.exceptions.EmptyDescException;
import duke.exceptions.UnrecogException;

/**
 * ToDo class of parent Tasks class
 */
public class ToDo extends Tasks {
    String icon = "[T]";
    String desc;

    /*constructor for ToDo task*/
    public ToDo(String str) throws Exception {
        super(str);
        if(!str.contains("todo")) {
            throw new UnrecogException("");
        }
        if (str.equals("todo")) {
            throw new EmptyDescException("");
        }
        this.desc = str.split(" ", 2)[1];
    }

    /*Returns description of ToDo which has no timeframe */
    public String getDesc() {
        return this.desc;
    }

    /*Returns icon representing the ToDo class of Tasks*/
    public String icon() {
        return icon;
    }

    /*Returns a message specific to when an ToDo task is added to TaskList*/
    public String added() {
        return super.added() + " " + icon + super.completed() + " " + this.desc;
    }

    /*Returns a message specific to when an ToDo task is removed from TaskList*/
    public String deleted() {
        return super.deleted() + " " + icon + super.completed() + " " + this.desc;
    }
}
