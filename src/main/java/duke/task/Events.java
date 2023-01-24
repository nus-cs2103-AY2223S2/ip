package duke.task;

import duke.utilities.Parser;

public class Events extends Task {

    public final String raw;
    String start;
    String end;

    public Events(String name, boolean done) {
        super(name, done);
        raw = name;
        extract();
    }

    void extract() {
        try {
            String[] tokens = task_name.split("/");
            String[] startdate = tokens[1].split(" ");
            String[] enddate = tokens[2].split(" ");
            task_name = tokens[0];
            start = startdate.length == 3 ? "(" + startdate[0] + ": " + startdate[1] + " " + startdate[2] + " "
                    : "(" + startdate[0] + ": " + startdate[1] + " " + startdate[2] + " " + startdate[3] + " ";
            end = enddate[0] + ": " + enddate[1] + ")";
        } catch (ArrayIndexOutOfBoundsException e) {
            //exception forwarded to Task manager;
        }
    }


    @Override
    public void add() {
        message_add = Parser.add + Parser.eventMarked_spaced + task_name + start + end;
    }

    @Override
    public void display() {
        if (done)
            message_display = Parser.eventMarked + task_name + start + end;
        else
            message_display = Parser.eventUnmarked + task_name + start + end;
    }

    @Override
    public void delete() {
        if (done)
            message_delete = Parser.delete + Parser.eventMarked_spaced + task_name + start + end;
        else
            message_delete = Parser.delete + Parser.eventUnmarked_spaced + task_name + start + end;
    }

    @Override
    public void marked() {
        message_marked = Parser.mark + Parser.eventMarked_spaced + task_name + start + end;
        done = true;
    }

    @Override
    public void unmarked() {
        message_unmarked = Parser.unmark + Parser.eventUnmarked_spaced + task_name + start + end;
        done = false;
    }
}
