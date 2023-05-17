package command;

import gui.Ui;
import runner.Riddle;
import runner.TaskList;
import task.Task;

public class Find {
    private final Riddle riddle;

    /**
     * Constructor for Find.
     * @param riddle
     */
    public Find(Riddle riddle) {
        this.riddle = riddle;
    }

    /**
     * Actions when finding keywords.
     * @param key Keyword input.
     * @returns All tasks containing the keyword in a list.
     */
    public String execute(String key) {
        TaskList ans = new TaskList();
        for (Task tk : riddle.taskList.getList()) {
            if (tk.getMSG().contains(key)) {
                ans.add(tk);
            }
        }
        return Ui.showList(ans, 0);
    }
}
