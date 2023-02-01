package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{

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
