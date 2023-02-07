package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{

    /**
     * Creates a TaskList of tasks with descriptions matching keyword, and returns list to Ui to be displayed.
     *
     * @param keyword the String to be compared with Tasks' descriptions
     * @return Duke's reply containing matching Tasks to be displayed on Gui
     */
    public String find(String keyword, Ui ui) {
        TaskList l = new TaskList();
        for (Task t : this) {
            if (t.isAMatch(keyword)) {
                l.add(t);
            }
        }
        return ui.showMatchingList(l);
    }
}
