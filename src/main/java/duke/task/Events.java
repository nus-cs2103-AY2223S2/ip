package duke.task;

import duke.utilities.Parser;

/**
 * The type Events.
 */
public class Events extends Task {

    private static final int DATEFORMAT = 3;
    /**
     * The Raw input.
     */
    public final String rawInput;
    /**
     * The Start.
     */
    private String start;
    /**
     * The End.
     */
    private String end;

    /**
     * Instantiates a new Events.
     *
     * @param name the name
     * @param done the done
     */
    public Events(String name, boolean done) {
        super(name, done);
        rawInput = name;
        extract();
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    private void extract() {
        //requies modifcation for fomatting
        try {
            String[] tokens = taskName.split("/");
            String[] startdate = tokens[1].split(" ");
            String[] enddate = tokens[2].split(" ");
            taskName = tokens[0];
            start = startdate.length == DATEFORMAT ? "(" + startdate[0] + ": " + startdate[1]
                    + " " + startdate[2] + " "
                    : "(" + startdate[0] + ": " + startdate[1] + " "
                    + startdate[2] + " " + startdate[3] + " ";
            end = enddate[0] + ": " + enddate[1] + ")";
        } catch (ArrayIndexOutOfBoundsException e) {
            //exception forwarded to Task manager;
        }
    }


    @Override
    public void add() {
        messageAdd = Parser.ADDED_THIS_TASK
                + Parser.EVENT_UNMARKED_SPACED + taskName + start + end;
    }

    @Override
    public void display() {
        if (done) {
            messageDisplay = Parser.EVENT_MARKED + taskName + start + end;
        } else {
            messageDisplay = Parser.EVENT_UNMARKED + taskName + start + end;
        }
    }

    @Override
    public void delete() {
        if (done) {
            messageDelete = Parser.REMOVED_THIS_TASK
                    + Parser.EVENT_MARKED_SPACED + taskName + start + end;
        } else {
            messageDelete = Parser.REMOVED_THIS_TASK
                    + Parser.EVENT_UNMARKED_SPACED + taskName + start + end;
        }
    }

    @Override
    public void marked() {
        messageMarked = Parser.MARKED_THIS_TASK_AS_DONE
                + Parser.EVENT_MARKED_SPACED + taskName + start + end;
        done = true;
    }

    @Override
    public void unmarked() {
        messageUnmarked = Parser.MARKED_THIS_TASK_AS_NOT_DONE_YET
                + Parser.EVENT_UNMARKED_SPACED + taskName + start + end;
        done = false;
    }
}
