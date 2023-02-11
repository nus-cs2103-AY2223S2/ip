package command;

import gui.Ui;
import runner.Duke;
import runner.TaskList;
import task.Task;

public class Find {
    private final Duke duke;

    public Find(Duke duke) {
        this.duke = duke;
    }

    /**
     * Actions when finding keywords.
     *
     * @param key Keyword input.
     * @returns All tasks containing the keyword in a list.
     */
    public String execute(String key) {
        TaskList ans = new TaskList();
        for (Task tk : duke.taskList.getList()) {
            if (tk.getMSG().contains(key)) {
                ans.add(tk);
            }
        }
        return Ui.showList(ans, 0);
    }
}
