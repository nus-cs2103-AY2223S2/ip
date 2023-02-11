package command;

import gui.Ui;
import runner.Duke;

public class Undo {
    private final Duke duke;

    public Undo(Duke duke) {
        this.duke = duke;
    }

    public String execute() {
        String s = duke.getRecentInput();
        if (s.equals("")) {
            return "Nothing needed to undo";
        }
        try {
            String[] segments = s.split(" ", 2);
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
                    return "OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! Arguments not enough.";
        }
    }

}
