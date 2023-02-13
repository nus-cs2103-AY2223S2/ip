package task;

import java.util.ArrayList;

/**
 * The ArrayList of tasks.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Prints the tasks in the list.
     *
     * @return All tasks stored in the list.
     */
    public String printCurrentTasks() {
        String res = "";
        if (this.size() == 0) {
            res = "Sorry this list is empty T^T";
            return res;
        }
        res += "Here are the tasks in your list:\n";
        for (int i = 0; i < this.size(); i++) {
            if (i == this.size() - 1) {
                res += (i + 1) + ". " + this.get(i);
            } else {
                res += (i + 1) + ". " + this.get(i) + "\n";
            }
        }
        return res;
    }

    public String getTasksWanted(String... keywords) {
        String result = "";
        int count = 1;
        for (int i = 0; i < this.size(); i++) {
            for (String keyword: keywords) {
                if (this.get(i).toString().contains(keyword) && i == this.size() - 1) {
                    result += count + ". " + this.get(i);
                } else if (this.get(i).toString().contains(keyword) && i != this.size() - 1) {
                    result += count + ". " + this.get(i) + "\n";
                }
                count += 1;
            }
        }
        return result;
    }
}
