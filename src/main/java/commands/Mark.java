package commands;
import exceptions.DukeMarkOutOfBounds;
import tasks.Task;

import java.util.ArrayList;

public class Mark implements Command {
    private ArrayList<Task> tasks;
    private int index;

    public Mark(int index, ArrayList<Task> tasks){
        this.tasks = tasks;
        this.index = index;
    }

    public String execute() throws DukeMarkOutOfBounds {
        try {
            Task toMark = tasks.get(index);
            return toMark.mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMarkOutOfBounds(index);
        }
    }
}
