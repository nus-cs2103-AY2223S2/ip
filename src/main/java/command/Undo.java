package command;

import gui.Ui;
import runner.Duke;

public class Undo {
    private final Duke duke;

    /**
     * Constructor for Undo.
     * @param duke
     */
    public Undo(Duke duke) {
        this.duke = duke;
    }

    /**
     * Actions when undo.
     * @return Message shown.
     */
    public String execute() {
        String input = duke.getRecentInput();
        if (input.equals("")) {
            return "Nothing needed to undo";
        }
        try {
            String[] segments = input.split(" ", 2);
            String first = segments[0];
            String arg = segments[1];
            switch (first) {
                case "mark":
                    return new Unmark(this.duke).execute(arg);
                case "unmark":
                    return new Mark(this.duke).execute(arg);
                case "delete":
                    duke.taskList.insert(duke.getDeletedTask(), Integer.parseInt(arg)-1);
                    return Ui.addMSG(duke.getDeletedTask(), duke.taskList.size());
                case "todo":
                case "deadline":
                case "event":
                    return new Delete(duke).execute(Integer.toString(duke.taskList.size()));
                default:
                    return "OOPS!!! I don't know this command :-(";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! Arguments not enough.";
        }
    }

}
