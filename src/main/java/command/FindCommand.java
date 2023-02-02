package command;
// alphabetical
//todo
import tasks.TaskList;
import exceptions.DukeException;
import duke.Storage;
import duke.Ui;
import tasks.Task;

import java.util.ArrayList;

/***
 * Give users a way to find a task by searching for a keyword.
 */
public class FindCommand extends Command{

    private String keyword;

    /**
     * Constructor for find Command
     * @param keyword
     */
    public FindCommand(String keyword){
        super();
        this.keyword = keyword;
    }

    /***
     * Finds the relevant task with matching keyword
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */

    // a space before the for loop todo
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException{
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String s = t.getDescription();
            if (s.contains(keyword)) {
                res.add(t);
            }
        }
        return ui.printFind(res);
    }
}
