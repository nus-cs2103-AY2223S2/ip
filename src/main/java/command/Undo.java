package command;

import gui.Ui;
import runner.Riddle;

public class Undo {
    private final Riddle riddle;

    /**
     * Constructor for Undo.
     * @param riddle
     */
    public Undo(Riddle riddle) {
        this.riddle = riddle;
    }

    /**
     * Actions when undo.
     * @return Message shown.
     */
    public String execute() {
        String input = riddle.getRecentInput();
        if (input.equals("")) {
            return "Nothing needed to undo";
        }
        try {
            String[] segments = input.split(" ", 2);
            String first = segments[0];
            String arg = segments[1];
            switch (first) {
                case "mark":
                    return new Unmark(this.riddle).execute(arg);
                case "unmark":
                    return new Mark(this.riddle).execute(arg);
                case "delete":
                    riddle.taskList.insert(riddle.getDeletedTask(), Integer.parseInt(arg)-1);
                    return Ui.addMSG(riddle.getDeletedTask(), riddle.taskList.size());
                case "todo":
                case "deadline":
                case "event":
                    return new Delete(riddle).execute(Integer.toString(riddle.taskList.size()));
                default:
                    return "OOPS!!! I don't know this command :-(";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! Arguments not enough.";
        }
    }

}
