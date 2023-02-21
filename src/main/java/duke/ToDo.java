package duke;

import duke.exceptions.DukeyException;

public class ToDo extends Task {
    private static final String TYPE = "[T]";

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isMarked) {
        super(name, isMarked);
    }


    public static ToDo createToDo(Ui ui) throws DukeyException {
        String toDoName = ui.readTaskName("ToDo");
        return new ToDo(toDoName);
    }

    public static ToDo createToDoFromLog(String[] logStringArray) {
        String name = logStringArray[2];
        boolean isMarked = !logStringArray[1].equals("0");
        return new ToDo(name, isMarked);
    }

    @Override
    public String getMessageWhenAdded() {
        return "DukeyList just added a new todo:";
    }

    @Override
    public String getLogString() {
        return "T" + "," + getMarkedStatus() + "," + this.getName();
    }

    @Override
    public String toString() {
        if (this.isDone()) {
            return TYPE + "[X]" + " " + this.getName();
        }

        return TYPE + "[ ]" + " " + this.getName();
    }
}

